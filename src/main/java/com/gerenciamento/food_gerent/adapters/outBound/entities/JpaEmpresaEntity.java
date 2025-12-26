package com.gerenciamento.food_gerent.adapters.outBound.entities;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gerenciamento.food_gerent.domain.empresas.Empresa;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empresas")
public class JpaEmpresaEntity {
  
  @Id
  @GeneratedValue
  private UUID id;

  @Enumerated(EnumType.STRING)
  private UsuarioEnumStatus status;
  
  @CreationTimestamp
  private LocalDate dataCriacao;
  
  @UpdateTimestamp
  private LocalDate dataAtualizacao;

  @ManyToOne
  @JoinColumn(name = "cliente_id", nullable = false)
  private JpaClienteEntity cliente;

  private String nome;
  private String cnpj;
  private String rua;
  private String bairro;
  private String cidade;
  private String estado;
  private String cep;
  private String telefone;
  private String email;

  @PrePersist
  public void prePersist() { 
    if (this.status == null) { 
      this.status = UsuarioEnumStatus.ATIVO; 
    }
  }

  public JpaEmpresaEntity(Empresa empresa, JpaClienteEntity clienteEntity) {
    this.id = empresa.getId();
    this.nome = empresa.getNome();
    this.cnpj = empresa.getCnpj();
    this.rua = empresa.getRua();
    this.bairro = empresa.getBairro();
    this.cidade = empresa.getCidade();
    this.estado = empresa.getEstado();
    this.cep = empresa.getCep();
    this.telefone = empresa.getTelefone();
    this.email = empresa.getEmail();
    this.status = empresa.getStatus();
    this.dataCriacao = empresa.getDataCriacao();
    this.dataAtualizacao = empresa.getDataAtualizacao();
    this.cliente = clienteEntity;
  }

  
}

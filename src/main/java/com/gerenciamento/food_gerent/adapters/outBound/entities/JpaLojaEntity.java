package com.gerenciamento.food_gerent.adapters.outBound.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gerenciamento.food_gerent.domain.loja.Loja;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lojas")
public class JpaLojaEntity {

  @Id
  @GeneratedValue
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "empresa_id", nullable = false)
  private JpaEmpresaEntity empresa;

  @OneToMany(mappedBy = "loja")
  private List<JpaFuncionarioEntity> funcionarios = new ArrayList<>();

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false, unique = true)
  private String cnpj;

  @Column(nullable = false)
  private String rua;

  @Column(nullable = false)
  private String bairro;

  @Column(nullable = false)
  private String cidade;

  @Column(nullable = false)
  private String estado;

  @Column(nullable = false)
  private String cep;

  private String telefone;

  @Enumerated(EnumType.STRING)
  private EnumStatus status;

  @CreationTimestamp
  private LocalDate dataCriacao;

  @UpdateTimestamp
  private LocalDate dataAtualizacao;

  @PrePersist
  public void prePersist() { 
    if (this.status == null) { 
      this.status = EnumStatus.ATIVO; 
    }
  }

  public JpaLojaEntity(Loja loja, JpaEmpresaEntity empresa) {
    this.id = loja.getId();
    this.empresa = empresa;
    this.nome = loja.getNome();
    this.cnpj = loja.getCnpj();
    this.rua = loja.getRua();
    this.bairro = loja.getBairro();
    this.cidade = loja.getCidade();
    this.estado = loja.getEstado();
    this.cep = loja.getCep();
    this.telefone = loja.getTelefone();
    this.status = loja.getStatus();
    this.dataCriacao = loja.getDataCriacao();
    this.dataAtualizacao = loja.getDataAtualizacao();
  }
}
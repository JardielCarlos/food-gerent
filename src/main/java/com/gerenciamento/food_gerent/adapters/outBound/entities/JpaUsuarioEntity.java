package com.gerenciamento.food_gerent.adapters.outBound.entities;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gerenciamento.food_gerent.domain.usuarios.Usuario;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public class JpaUsuarioEntity {
  
  @Id
  @GeneratedValue
  private UUID id;

  @Column(nullable = false)
  private String nome;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String senha;

  @Column(unique = true, nullable = false)
  private String cpf;

  @Enumerated(EnumType.STRING)
  private UsuarioEnumCargos cargo;
  
  @Enumerated(EnumType.STRING)
  private EnumStatus status;

  @CreationTimestamp
  private LocalDate dataCriacao;

  @UpdateTimestamp
  private LocalDate dataAtualizacao;

  private String telefone;

  @PrePersist
  public void prePersist() { 
    if (this.status == null) { 
      this.status = EnumStatus.ATIVO; 
    }
  }

  public JpaUsuarioEntity(Usuario usuario) {
    this.id = usuario.getId();
    this.nome = usuario.getNome();
    this.email = usuario.getEmail();
    this.telefone = usuario.getTelefone();
    this.senha = usuario.getSenha();
    this.cpf = usuario.getCpf();
    this.cargo = usuario.getCargo();
    this.status = EnumStatus.ATIVO;
    this.dataCriacao = usuario.getDataCriacao();
    this.dataAtualizacao = usuario.getDataAtualizacao();
  }
}

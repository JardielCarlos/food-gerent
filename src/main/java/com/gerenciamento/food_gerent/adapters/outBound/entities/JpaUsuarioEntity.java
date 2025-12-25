package com.gerenciamento.food_gerent.adapters.outBound.entities;

import java.util.UUID;

import com.gerenciamento.food_gerent.domain.usuarios.Usuario;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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

  private String nome;
  private String email;
  private String telefone;

  public JpaUsuarioEntity(Usuario usuario) {
    this.id = usuario.getId();
    this.nome = usuario.getNome();
    this.email = usuario.getEmail();
    this.telefone = usuario.getTelefone();
  }
}

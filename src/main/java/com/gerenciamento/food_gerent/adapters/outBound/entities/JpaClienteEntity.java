package com.gerenciamento.food_gerent.adapters.outBound.entities;

import com.gerenciamento.food_gerent.domain.usuarios.Usuario;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CLIENTE")
@Data
@NoArgsConstructor
// @AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JpaClienteEntity extends JpaUsuarioEntity{

  // Campos especificos de cliente podem ser adicionados aqui
  // Ex. private String endereco;

  public JpaClienteEntity(Usuario usuario) {
    super(
      usuario.getId(), 
      usuario.getNome(), 
      usuario.getEmail(), 
      usuario.getSenha(), 
      usuario.getCpf(), 
      UsuarioEnumCargos.DONO, 
      usuario.getStatus(), 
      usuario.getDataCriacao(),
      usuario.getDataAtualizacao(),
      usuario.getTelefone()
    ); 
  }
}

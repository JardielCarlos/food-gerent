package com.gerenciamento.food_gerent.domain.clientes;

import java.time.LocalDate;

import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumStatus;

public record ClientePatchDTO(
    String nome,
    String email,
    String telefone,
    String senha, 
    String cpf, 
    UsuarioEnumCargos cargo, 
    UsuarioEnumStatus status
) {
  
}

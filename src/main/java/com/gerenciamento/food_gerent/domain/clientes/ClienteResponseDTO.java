package com.gerenciamento.food_gerent.domain.clientes;

import java.time.LocalDate;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumStatus;

public record ClienteResponseDTO(
  UUID id, 
  String nome, 
  String email,
  String cpf, 
  UsuarioEnumCargos cargo, 
  UsuarioEnumStatus status, 
  LocalDate dataCriacao, 
  LocalDate dataAtualizacao, 
  String telefone
) {
  
}
  

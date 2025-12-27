package com.gerenciamento.food_gerent.domain.usuarios;

import java.time.LocalDate;
import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public record UsuarioResponseDTO(
  UUID id, 
  String nome, 
  String email,
  String cpf, 
  UsuarioEnumCargos cargo, 
  EnumStatus status, 
  LocalDate dataCriacao, 
  LocalDate dataAtualizacao, 
  String telefone
) {
}

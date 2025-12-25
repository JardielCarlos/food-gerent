package com.gerenciamento.food_gerent.domain.usuarios;

import java.time.LocalDate;
import java.util.UUID;

public record UsuarioResponseDTO(
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

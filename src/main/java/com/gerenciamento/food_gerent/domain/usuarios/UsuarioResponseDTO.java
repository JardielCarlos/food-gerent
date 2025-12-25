package com.gerenciamento.food_gerent.domain.usuarios;

import java.util.UUID;

public record UsuarioResponseDTO(
  UUID id,
  String nome,
  String email,
  String telefone
) {
}

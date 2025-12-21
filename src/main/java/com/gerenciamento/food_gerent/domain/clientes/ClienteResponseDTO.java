package com.gerenciamento.food_gerent.domain.clientes;

import java.util.UUID;

public record ClienteResponseDTO(
  UUID id,
  String nome,
  String email,
  String telefone
) {
  
}
  

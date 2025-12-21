package com.gerenciamento.food_gerent.domain.clientes;

public record ClientePatchDTO(
    String nome,
    String email,
    String telefone
) {
  
}

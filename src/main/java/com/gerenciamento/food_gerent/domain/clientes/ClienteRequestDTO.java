package com.gerenciamento.food_gerent.domain.clientes;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
  @NotBlank(message = "O nome do cliente é obrigatório") String nome,
  @NotBlank(message = "O email do cliente é obrigatório") String email,
  String telefone
) {
  
}

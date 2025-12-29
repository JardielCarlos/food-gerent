package com.gerenciamento.food_gerent.domain.auth;

public record LoginRequestDTO(
  String username,
  String password
) {
  
}

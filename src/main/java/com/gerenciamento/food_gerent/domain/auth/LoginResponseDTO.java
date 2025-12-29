package com.gerenciamento.food_gerent.domain.auth;

public record LoginResponseDTO(
  String acessToken,
  Long expiresIn
) {
  
}

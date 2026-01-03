package com.gerenciamento.food_gerent.application.usecases;

import java.util.UUID;

import com.gerenciamento.food_gerent.domain.refreshTokens.RefreshToken;
import com.gerenciamento.food_gerent.domain.usuarios.Usuario;

public interface RefreshTokenUseCases {
  String createRefreshToken(Usuario usuario);
  
  RefreshToken findByToken(String token);
  
  void deleteByUsuarioId(UUID usuarioId);
}

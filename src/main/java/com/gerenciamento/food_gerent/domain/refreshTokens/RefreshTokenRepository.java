package com.gerenciamento.food_gerent.domain.refreshTokens;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository {
  RefreshToken save(RefreshToken refreshToken);
  Optional<RefreshToken> findByToken(String token);
  Optional<RefreshToken> findByUsuarioId(UUID idCliente);
  void deleteByUsuarioId(UUID usuarioId);
}

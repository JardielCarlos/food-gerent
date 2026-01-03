package com.gerenciamento.food_gerent.adapters.outBound.repositories.refreshTokens;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaRefreshTokenEntity;

public interface JpaRefreshTokenRepository extends JpaRepository<JpaRefreshTokenEntity, Long>{
  Optional<JpaRefreshTokenEntity> findByToken(String token);

  @Modifying 
  @Query("DELETE FROM JpaRefreshTokenEntity j WHERE j.usuario.id = :usuarioId")
  void deleteByUsuario_Id(@Param("usuarioId") UUID usuarioId);

  Optional<JpaRefreshTokenEntity> findByUsuario_Id(UUID usuarioId);
}

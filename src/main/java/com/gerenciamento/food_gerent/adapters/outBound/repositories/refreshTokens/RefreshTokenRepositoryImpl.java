package com.gerenciamento.food_gerent.adapters.outBound.repositories.refreshTokens;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaRefreshTokenEntity;
import com.gerenciamento.food_gerent.domain.refreshTokens.RefreshToken;
import com.gerenciamento.food_gerent.domain.refreshTokens.RefreshTokenRepository;
import com.gerenciamento.food_gerent.utils.mappers.RefreshTokenMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

  private final JpaRefreshTokenRepository jpaRefreshTokenRepository;
  private final RefreshTokenMapper mapper;

  @Override
  public RefreshToken save(RefreshToken refreshToken) {
    JpaRefreshTokenEntity savedToken = mapper.toDomain(refreshToken);
    this.jpaRefreshTokenRepository.save(savedToken);

    return mapper.jpaToDomain(savedToken);
  }

  @Override
  public Optional<RefreshToken> findByToken(String token) {
    return 
      this.jpaRefreshTokenRepository.findByToken(token) 
        .map(mapper::jpaToDomain); 
  }

  @Override
  public Optional<RefreshToken> findByUsuarioId(UUID idCliente) {
    Optional<JpaRefreshTokenEntity> refreshTokenEntity = this.jpaRefreshTokenRepository.findByUsuario_Id(idCliente);
    
    return refreshTokenEntity.map(mapper::jpaToDomain);
  }

  @Override
  public void deleteByUsuarioId(UUID usuarioId) {
   this.jpaRefreshTokenRepository.deleteByUsuario_Id(usuarioId);
  }
}

package com.gerenciamento.food_gerent.application.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gerenciamento.food_gerent.application.usecases.RefreshTokenUseCases;
import com.gerenciamento.food_gerent.domain.refreshTokens.RefreshToken;
import com.gerenciamento.food_gerent.domain.refreshTokens.RefreshTokenRepository;
import com.gerenciamento.food_gerent.domain.usuarios.Usuario;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.RefreshTokenMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenUseCases {

  private final RefreshTokenRepository repository;  
  private final RefreshTokenMapper mapper;

  @Override
  @Transactional
  public String createRefreshToken(Usuario usuario) {
    Optional<RefreshToken> existingToken = repository.findByUsuarioId(usuario.getId());

    if (existingToken.isPresent()) {
      repository.deleteByUsuarioId(existingToken.get().getUsuario().getId());
    } 

    String tokenValue = UUID.randomUUID().toString();
    Instant expiryDate = Instant.now().plus(7, ChronoUnit.DAYS);
    
    RefreshToken refreshToken = mapper.toEntity(tokenValue, usuario, expiryDate);


    this.repository.save(refreshToken);

    return tokenValue;
  }

  @Override
  public RefreshToken findByToken(String token) {
    RefreshToken refreshToken = repository.findByToken(token).orElseThrow(() -> new EntityNotFoundException("Refresh Token não encontrado"));
    return refreshToken;
  }

  @Override
  @Transactional
  public void deleteByUsuarioId(UUID usuarioId) {
    repository.deleteByUsuarioId(usuarioId);
  }
}

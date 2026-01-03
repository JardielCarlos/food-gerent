package com.gerenciamento.food_gerent.application.usecases;

import org.springframework.security.oauth2.jwt.Jwt;

import com.gerenciamento.food_gerent.domain.auth.LoginRequestDTO;
import com.gerenciamento.food_gerent.domain.auth.LoginResponseDTO;
import com.gerenciamento.food_gerent.domain.refreshTokens.RefreshTokenRequestDTO;
import com.gerenciamento.food_gerent.domain.refreshTokens.RefreshTokenResponseDTO;

public interface LoginUseCase {

  LoginResponseDTO login(LoginRequestDTO login);

  RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO dto);

  void logout(Jwt jwt);
}


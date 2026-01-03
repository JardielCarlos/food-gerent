package com.gerenciamento.food_gerent.adapters.inBound.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamento.food_gerent.application.service.AuthServiceImpl;
import com.gerenciamento.food_gerent.domain.auth.LoginRequestDTO;
import com.gerenciamento.food_gerent.domain.auth.LoginResponseDTO;
import com.gerenciamento.food_gerent.domain.refreshTokens.RefreshTokenRequestDTO;
import com.gerenciamento.food_gerent.domain.refreshTokens.RefreshTokenResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthServiceImpl authService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest){
    LoginResponseDTO response = this.authService.login(loginRequest);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<RefreshTokenResponseDTO> refreshToken(@RequestBody RefreshTokenRequestDTO refreshToken){
    RefreshTokenResponseDTO newToken = this.authService.refreshToken(refreshToken);
    return ResponseEntity.ok(newToken);
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout(@AuthenticationPrincipal Jwt jwt){
    this.authService.logout(jwt);
    return ResponseEntity.noContent().build();
  }

}

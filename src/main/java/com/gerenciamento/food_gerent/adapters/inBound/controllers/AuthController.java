package com.gerenciamento.food_gerent.adapters.inBound.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamento.food_gerent.application.service.AuthServiceImpl;
import com.gerenciamento.food_gerent.domain.auth.LoginRequestDTO;
import com.gerenciamento.food_gerent.domain.auth.LoginResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

  private final AuthServiceImpl authService;

  @PostMapping
  public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest){
    LoginResponseDTO response = this.authService.login(loginRequest);
    return ResponseEntity.ok(response);

  }

}

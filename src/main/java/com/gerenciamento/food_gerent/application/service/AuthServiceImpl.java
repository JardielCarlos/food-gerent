package com.gerenciamento.food_gerent.application.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimAccessor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.gerenciamento.food_gerent.application.usecases.LoginUseCase;
import com.gerenciamento.food_gerent.domain.auth.LoginRequestDTO;
import com.gerenciamento.food_gerent.domain.auth.LoginResponseDTO;
import com.gerenciamento.food_gerent.domain.usuarios.Usuario;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements LoginUseCase {

  private final UsuarioRepository repository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final JwtEncoder jwtEncoder;

  @Override
  public LoginResponseDTO login(LoginRequestDTO login) {

    Optional<Usuario> usuario = repository.findByEmail(login.username());

    if(usuario.isEmpty() || !usuario.get().isLoginCorrect(login.password(), passwordEncoder)) {
      throw new BadCredentialsException("Usuário ou senha inválidas!");
    }

    Instant now = Instant.now();
    Long expiresIn = 300L;

    JwtClaimsSet claims = JwtClaimsSet.builder()
      .issuer("myBackend")
      .subject(usuario.get().getId().toString())
      .issuedAt(now)
      .expiresAt(now.plusSeconds(expiresIn))
      // .claim("scope", "Admin")
      .build();

    var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    return new LoginResponseDTO(jwtValue, expiresIn);
  }
  
}

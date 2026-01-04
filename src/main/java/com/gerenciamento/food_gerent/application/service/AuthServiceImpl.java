package com.gerenciamento.food_gerent.application.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.gerenciamento.food_gerent.application.usecases.LoginUseCase;
import com.gerenciamento.food_gerent.domain.auth.LoginRequestDTO;
import com.gerenciamento.food_gerent.domain.auth.LoginResponseDTO;
import com.gerenciamento.food_gerent.domain.permissoes.Permissao;
import com.gerenciamento.food_gerent.domain.refreshTokens.RefreshToken;
import com.gerenciamento.food_gerent.domain.refreshTokens.RefreshTokenRequestDTO;
import com.gerenciamento.food_gerent.domain.refreshTokens.RefreshTokenResponseDTO;
import com.gerenciamento.food_gerent.domain.usuarios.Usuario;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements LoginUseCase {

  private final UsuarioRepository repository;
  private final RefreshTokenServiceImpl serviceRefreshToken;
  private final BCryptPasswordEncoder passwordEncoder;
  private final JwtEncoder jwtEncoder;

  @Override
  // @Transactional
  public LoginResponseDTO login(LoginRequestDTO login) {

    Optional<Usuario> usuario = repository.findByEmail(login.username());

    if(usuario.isEmpty() || !usuario.get().isLoginCorrect(login.password(), passwordEncoder)) {
      throw new BadCredentialsException("Usuário ou senha inválidas!");
    }

    Instant now = Instant.now();
    Long expiresIn = 1800L;

    String scopes = usuario.get().getPermissoes()
      .stream()
      .map(Permissao::getNome)
      .collect(Collectors.joining(" "));

    JwtClaimsSet claims = JwtClaimsSet.builder()
      .issuer("myBackend")
      .subject(usuario.get().getId().toString())
      .issuedAt(now)
      .expiresAt(now.plusSeconds(expiresIn))
      .claim("scope", scopes)
      .build();

    var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    String refreshToken = this.serviceRefreshToken.createRefreshToken(usuario.get());

    return new LoginResponseDTO(jwtValue, expiresIn, refreshToken);
  }


  public RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO dto) {
    RefreshToken refreshTokenDB = serviceRefreshToken.findByToken(dto.refreshToken());


    if (refreshTokenDB.getExpiryDate().isBefore(Instant.now())) {
      serviceRefreshToken.deleteByUsuarioId(refreshTokenDB.getUsuario().getId());
      throw new BadCredentialsException("Refresh token inválido ou expirado");
    }

    // Só chega aqui se passou nas validações
    Usuario usuario = refreshTokenDB.getUsuario();
    Instant now = Instant.now();
    Long expiresIn = 1800L;

    String scopes = usuario.getPermissoes()
      .stream()
      .map(Permissao::getNome)
      .collect(Collectors.joining(" "));

    JwtClaimsSet claims = JwtClaimsSet.builder()
      .issuer("myBackend")
      .subject(usuario.getId().toString())
      .issuedAt(now)
      .expiresAt(now.plusSeconds(expiresIn))
      .claim("scope", scopes)
      .build();

    String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    refreshTokenDB.setToken(token);
    return new RefreshTokenResponseDTO(token);
  }

  public void logout(Jwt jwt) {
    String usuarioId = jwt.getSubject();
    this.serviceRefreshToken.deleteByUsuarioId(UUID.fromString(usuarioId));
  }

}

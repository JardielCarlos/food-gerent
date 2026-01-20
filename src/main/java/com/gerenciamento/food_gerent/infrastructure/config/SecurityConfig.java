package com.gerenciamento.food_gerent.infrastructure.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gerenciamento.food_gerent.infrastructure.config.exceptions.ProblemDetails;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

import tools.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
  
  @Value("${jwt.public.key}")
  private RSAPublicKey publicKey;

  @Value("${jwt.private.key}")
  private RSAPrivateKey privateKey;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
          .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
          .requestMatchers(HttpMethod.POST, "/auth/refresh-token").permitAll()
          .requestMatchers(HttpMethod.POST, "/clientes").permitAll()
          .anyRequest().authenticated()
      )
      .oauth2ResourceServer(oauth2 -> oauth2
        .jwt(Customizer.withDefaults())
        .authenticationEntryPoint((request, response, authException) -> {
          ProblemDetails problem = new ProblemDetails(
            "Não autenticado",
            HttpStatus.UNAUTHORIZED.value(),
            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
            "Token inválido ou ausente.",
            request.getRequestURI()
          );
          response.setStatus(HttpStatus.UNAUTHORIZED.value());
          response.setContentType("application/json");
          new ObjectMapper()
            .writeValue(response.getOutputStream(), problem);
        })
      )
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .exceptionHandling(ex -> ex
        .accessDeniedHandler((request, response, accessDeniedException) -> {
          ProblemDetails problem = new ProblemDetails(
            "Acesso negado",
            HttpStatus.FORBIDDEN.value(),
            HttpStatus.FORBIDDEN.getReasonPhrase(),
            "Você não tem permissão para acessar este recurso.",
            request.getRequestURI()
          );
          response.setStatus(HttpStatus.FORBIDDEN.value());
          response.setContentType("application/json");
          new ObjectMapper()
            .writeValue(response.getOutputStream(), problem);
        })
      );
    return http.build();
  }

  // @Bean
  // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  //     http
  //         .csrf(csrf -> csrf.disable())
  //         .authorizeHttpRequests(auth -> auth
  //             .anyRequest().permitAll()   // libera tudo
  //         )
  //         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
  //         .oauth2ResourceServer(oauth2 -> oauth2.disable()); // desliga o resource server
  //     return http.build();
  // }

  @Bean
  public JwtDecoder jwtDecoder(){
    return NimbusJwtDecoder.withPublicKey(publicKey).build();
  }

  @Bean
  public JwtEncoder jwtEncoder(){
    JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
    var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
    return new NimbusJwtEncoder(jwks);
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder(){
    return new BCryptPasswordEncoder();
  }
}

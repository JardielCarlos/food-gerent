package com.gerenciamento.food_gerent.domain.refreshTokens;

import java.time.Instant;

import com.gerenciamento.food_gerent.domain.usuarios.Usuario;

public class RefreshToken {
  
  private Long id;
  private String token;
  private Usuario usuario;
  private Instant expiryDate;
  
  public RefreshToken() {
  }

  public RefreshToken(Long id, String token, Usuario usuario, Instant expiryDate) {
    this.id = id;
    this.token = token;
    this.usuario = usuario;
    this.expiryDate = expiryDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Instant getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Instant expiryDate) {
    this.expiryDate = expiryDate;
  }
}

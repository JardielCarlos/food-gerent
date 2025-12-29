package com.gerenciamento.food_gerent.domain.permissoes;

public class Permissao {
  private Long permissaoId;
  private String nome;
  
  public Permissao() {
  }

  public Permissao(Long permissaoId, String nome) {
    this.permissaoId = permissaoId;
    this.nome = nome;
  }

  public Long getPermissaoId() {
    return permissaoId;
  }
  public void setPermissaoId(Long permissaoId) {
    this.permissaoId = permissaoId;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
}

package com.gerenciamento.food_gerent.domain.usuarios;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.gerenciamento.food_gerent.domain.permissoes.Permissao;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public class Usuario {

  private UUID id;
  private String nome;
  private String email;
  private String senha;
  private String cpf;
  private UsuarioEnumCargos cargo;
  private EnumStatus status;
  private LocalDate dataCriacao;
  private LocalDate dataAtualizacao;
  private String telefone;
  private Set<Permissao> permissoes = new HashSet<>();

  public Usuario() {
  }

  public Usuario(UUID id, String nome, String email, String senha, String cpf, UsuarioEnumCargos cargo, EnumStatus status, LocalDate dataCriacao, LocalDate dataAtualizacao, String telefone, Set<Permissao> permissoes) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    this.cpf = cpf;
    this.cargo = cargo;
    this.status = status;
    this.dataCriacao = dataCriacao;
    this.dataAtualizacao = dataAtualizacao;
    this.telefone = telefone;
    this.permissoes = permissoes != null ? permissoes : new HashSet<>();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public UsuarioEnumCargos getCargo() {
    return cargo;
  }

  public void setCargo(UsuarioEnumCargos cargo) {
    this.cargo = cargo;
  }

  public EnumStatus getStatus() {
    return status;
  }

  public void setStatus(EnumStatus status) {
    this.status = status;
  }

  public LocalDate getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(LocalDate dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public LocalDate getDataAtualizacao() {
    return dataAtualizacao;
  }

  public void setDataAtualizacao(LocalDate dataAtualizacao) {
    this.dataAtualizacao = dataAtualizacao;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public Set<Permissao> getPermissoes() {
    return permissoes;
  }

  public void setPermissoes(Set<Permissao> permissoes) {
    this.permissoes = permissoes != null ? permissoes : new HashSet<>();
  }

  public boolean isLoginCorrect(String senha, PasswordEncoder passwordEncoder) {
    return passwordEncoder.matches(senha, this.senha);
  }
}

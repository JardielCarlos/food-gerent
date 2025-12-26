package com.gerenciamento.food_gerent.domain.empresas;

import java.time.LocalDate;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumStatus;

public class Empresa {
  
  private UUID id;
  private UUID idCliente;
  private String nome;
  private String cnpj;
  private String rua;
  private String bairro;
  private String cidade;
  private String estado;
  private String cep;
  private String telefone;
  private String email;
  private UsuarioEnumStatus status;
  private LocalDate dataCriacao;
  private LocalDate dataAtualizacao;

  public Empresa() {}

  public Empresa(UUID id, UUID idCliente, String nome, String cnpj, String rua, String bairro, String cidade,
      String estado, String cep, String telefone, String email, UsuarioEnumStatus status, LocalDate dataCriacao,
      LocalDate dataAtualizacao) {
    this.id = id;
    this.idCliente = idCliente;
    this.nome = nome;
    this.cnpj = cnpj;
    this.rua = rua;
    this.bairro = bairro;
    this.cidade = cidade;
    this.estado = estado;
    this.cep = cep;
    this.telefone = telefone;
    this.email = email;
    this.status = status;
    this.dataCriacao = dataCriacao;
    this.dataAtualizacao = dataAtualizacao;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(UUID idCliente) {
    this.idCliente = idCliente;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getRua() {
    return rua;
  }

  public void setRua(String rua) {
    this.rua = rua;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UsuarioEnumStatus getStatus() {
    return status;
  }

  public void setStatus(UsuarioEnumStatus status) {
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
}

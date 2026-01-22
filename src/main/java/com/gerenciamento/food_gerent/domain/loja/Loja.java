package com.gerenciamento.food_gerent.domain.loja;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.funcionarios.Funcionario;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public class Loja {
  
  private UUID id;  
  private UUID idEmpresa;
  private String nome;
  private String  cnpj;
  private String rua;
  private String bairro;
  private String cidade;
  private String estado;
  private String cep;
  private String telefone;
  private EnumStatus status;
  private LocalDate dataCriacao;
  private LocalDate dataAtualizacao;
  private List<Funcionario> funcionarios = new ArrayList<>();
  
  public Loja() {
  }

  public  Loja(UUID id, UUID idEmpresa, String nome, String cnpj, String rua, String bairro, String cidade,
      String estado, String cep, String telefone, EnumStatus status, LocalDate dataCriacao, LocalDate dataAtualizacao, List<Funcionario> funcionarios) {
    this.id = id;
    this.idEmpresa = idEmpresa;
    this.funcionarios = funcionarios;
    this.nome = nome;
    this.cnpj = cnpj;
    this.rua = rua;
    this.bairro = bairro;
    this.cidade = cidade;
    this.estado = estado;
    this.cep = cep;
    this.telefone = telefone;
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

  public UUID getIdEmpresa() {
    return idEmpresa;
  }

  public void setIdEmpresa(UUID idEmpresa) {
    this.idEmpresa = idEmpresa;
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

  public List<Funcionario> getFuncionarios() {
    return funcionarios;
  }

  public void setFuncionarios(List<Funcionario> funcionarios) {
    this.funcionarios = funcionarios;
  }
}

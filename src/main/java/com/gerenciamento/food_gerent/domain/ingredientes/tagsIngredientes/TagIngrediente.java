package com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes;

import java.time.LocalDate;
import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public class TagIngrediente {
  private UUID id;
  private String nome;
  private EnumStatus status;
  private LocalDate dataCriacao;
  private LocalDate dataAtualizacao;

  public TagIngrediente() {
  }

  public TagIngrediente(UUID id, String nome, EnumStatus status, LocalDate dataCriacao, LocalDate dataAtualizacao) {
    this.id = id;
    this.nome = nome;
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

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
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
}

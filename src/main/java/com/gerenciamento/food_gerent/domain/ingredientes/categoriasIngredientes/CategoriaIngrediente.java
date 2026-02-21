package com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public class CategoriaIngrediente {
  private UUID id;
  private String nome;
  private UUID parentId; // ID da categoria pai, se houver
  private List<CategoriaIngrediente> subcategorias; // Lista de subcategorias
  private Integer nivel = 0; // Nível hierárquico da categoria
  private EnumStatus status;
  private LocalDate dataCriacao;
  private LocalDate dataAtualizacao;

  public CategoriaIngrediente() {
  }
  
  public CategoriaIngrediente(UUID id, String nome, UUID parentId, List<CategoriaIngrediente> subcategorias,
      Integer nivel, EnumStatus status, LocalDate dataCriacao, LocalDate dataAtualizacao) {
    this.id = id;
    this.nome = nome;
    this.parentId = parentId;
    this.subcategorias = subcategorias;
    this.nivel = nivel;
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

  public UUID getParentId() {
    return parentId;
  }

  public void setParentId(UUID parentId) {
    this.parentId = parentId;
  }

  public List<CategoriaIngrediente> getSubcategorias() {
    return subcategorias;
  }

  public void setSubcategorias(List<CategoriaIngrediente> subcategorias) {
    this.subcategorias = subcategorias;
  }

  public Integer getNivel() {
    return nivel;
  }

  public void setNivel(Integer nivel) {
    this.nivel = nivel;
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


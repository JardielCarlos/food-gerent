package com.gerenciamento.food_gerent.domain.ingredientes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumUnidadeMedida;

public class Ingrediente {
  private UUID id;
  private String nome;
  private EnumUnidadeMedida unidadeMedida;
  private BigDecimal custoUnitario;
  private UUID categoriaId;
  private Set<UUID> tagsIds;
  private EnumStatus status;
  private LocalDate dataCriacao;
  private LocalDate dataAtualizacao;

  public Ingrediente() {
  }
  
  public Ingrediente(UUID id, String nome, EnumUnidadeMedida unidadeMedida, BigDecimal custoUnitario, UUID categoriaId,
      Set<UUID> tagsIds, EnumStatus status, LocalDate dataCriacao, LocalDate dataAtualizacao) {
    this.id = id;
    this.nome = nome;
    this.unidadeMedida = unidadeMedida;
    this.custoUnitario = custoUnitario;
    this.categoriaId = categoriaId;
    this.tagsIds = tagsIds;
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

  public EnumUnidadeMedida getUnidadeMedida() {
    return unidadeMedida;
  }

  public void setUnidadeMedida(EnumUnidadeMedida unidadeMedida) {
    this.unidadeMedida = unidadeMedida;
  }

  public BigDecimal getCustoUnitario() {
    return custoUnitario;
  }

  public void setCustoUnitario(BigDecimal custoUnitario) {
    this.custoUnitario = custoUnitario;
  }

  public UUID getCategoriaId() {
    return categoriaId;
  }

  public void setCategoriaId(UUID categoriaId) {
    this.categoriaId = categoriaId;
  }

  public Set<UUID> getTagsIds() {
    return tagsIds;
  }

  public void setTagsIds(Set<UUID> tagsIds) {
    this.tagsIds = tagsIds;
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

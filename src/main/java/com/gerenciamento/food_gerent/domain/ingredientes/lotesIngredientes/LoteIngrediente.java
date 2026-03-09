package com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumUnidadeMedida;

public class LoteIngrediente {
  private UUID id;
  private UUID ingredienteId;
  private LocalDate dataFabricacao;
  private LocalDate dataValidade; 
  private BigDecimal quantidadeInicial;
  private BigDecimal quantidadeDisponivel;
  private EnumUnidadeMedida unidadeMedida;
  private BigDecimal precoTotal;
  private EnumStatus status;
  private LocalDate dataCriacao;
  private LocalDate dataAtualizacao;

  public LoteIngrediente() {
  }

  public LoteIngrediente(UUID id, UUID ingredienteId, LocalDate dataFabricacao, LocalDate dataValidade,
      BigDecimal quantidadeInicial, BigDecimal quantidadeDisponivel, EnumUnidadeMedida unidadeMedida,
      BigDecimal precoTotal, EnumStatus status, LocalDate dataCriacao, LocalDate dataAtualizacao) {
    this.id = id;
    this.ingredienteId = ingredienteId;
    this.dataFabricacao = dataFabricacao;
    this.dataValidade = dataValidade;
    this.quantidadeInicial = quantidadeInicial;
    this.quantidadeDisponivel = quantidadeDisponivel;
    this.unidadeMedida = unidadeMedida;
    this.precoTotal = precoTotal;
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

  public UUID getIngredienteId() {
    return ingredienteId;
  }

  public void setIngredienteId(UUID ingredienteId) {
    this.ingredienteId = ingredienteId;
  }

  public LocalDate getDataFabricacao() {
    return dataFabricacao;
  }

  public void setDataFabricacao(LocalDate dataFabricacao) {
    this.dataFabricacao = dataFabricacao;
  }

  public LocalDate getDataValidade() {
    return dataValidade;
  }

  public void setDataValidade(LocalDate dataValidade) {
    this.dataValidade = dataValidade;
  }

  public BigDecimal getQuantidadeInicial() {
    return quantidadeInicial;
  }

  public void setQuantidadeInicial(BigDecimal quantidadeInicial) {
    this.quantidadeInicial = quantidadeInicial;
  }

  public BigDecimal getQuantidadeDisponivel() {
    return quantidadeDisponivel;
  }

  public void setQuantidadeDisponivel(BigDecimal quantidadeDisponivel) {
    this.quantidadeDisponivel = quantidadeDisponivel;
  }

  public EnumUnidadeMedida getUnidadeMedida() {
    return unidadeMedida;
  }

  public void setUnidadeMedida(EnumUnidadeMedida unidadeMedida) {
    this.unidadeMedida = unidadeMedida;
  }
  
  public BigDecimal getPrecoTotal() {
    return precoTotal;
  }

  public void setPrecoTotal(BigDecimal precoTotal) {
    this.precoTotal = precoTotal;
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

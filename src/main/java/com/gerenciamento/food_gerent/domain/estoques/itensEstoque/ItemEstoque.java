package com.gerenciamento.food_gerent.domain.estoques.itensEstoque;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumUnidadeMedida;

public class ItemEstoque {
  private UUID id;
  private String nomeIngrediente;
  private UUID ingredienteID;
  private UUID loteID;
  private BigDecimal quantidade;
  private BigDecimal reservado;
  private EnumUnidadeMedida unidade;
  private LocalDate dataValidade;
  private EnumStatus status;
  private LocalDate dataCriacao;
  private LocalDate dataAtualizacao;

  public ItemEstoque() {
  }

  public ItemEstoque(UUID id, String nomeIngrediente, UUID ingredienteID, UUID loteID, BigDecimal quantidade,
      BigDecimal reservado, EnumUnidadeMedida unidade, LocalDate dataValidade, EnumStatus status, LocalDate dataCriacao,
      LocalDate dataAtualizacao) {
    this.id = id;
    this.nomeIngrediente = nomeIngrediente;
    this.ingredienteID = ingredienteID;
    this.loteID = loteID;
    this.quantidade = quantidade;
    this.reservado = reservado;
    this.unidade = unidade;
    this.dataValidade = dataValidade;
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

  public String getNomeIngrediente() {
    return nomeIngrediente;
  }

  public void setNomeIngrediente(String nomeIngrediente) {
    this.nomeIngrediente = nomeIngrediente;
  }

  public UUID getIngredienteID() {
    return ingredienteID;
  }

  public void setIngredienteID(UUID ingredienteID) {
    this.ingredienteID = ingredienteID;
  }

  public UUID getLoteID() {
    return loteID;
  }

  public void setLoteID(UUID loteID) {
    this.loteID = loteID;
  }

  public BigDecimal getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(BigDecimal quantidade) {
    this.quantidade = quantidade;
  }

  public BigDecimal getReservado() {
    return reservado;
  }

  public void setReservado(BigDecimal reservado) {
    this.reservado = reservado;
  }

  public EnumUnidadeMedida getUnidade() {
    return unidade;
  }

  public void setUnidade(EnumUnidadeMedida unidade) {
    this.unidade = unidade;
  }

  public LocalDate getDataValidade() {
    return dataValidade;
  }

  public void setDataValidade(LocalDate dataValidade) {
    this.dataValidade = dataValidade;
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

  
  public void incrementar(BigDecimal valor) { 
    this.quantidade = this.quantidade.add(valor); 
  }

  public void decrementar(BigDecimal valor) {
    if (valor.compareTo(quantidade) > 0) throw new IllegalArgumentException("Estoque insuficiente");
    this.quantidade = this.quantidade.subtract(valor);
  }

  public boolean isVencido(LocalDate hoje) { 
    return dataValidade != null && !dataValidade.isAfter(hoje); 
  }
}

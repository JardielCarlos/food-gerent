package com.gerenciamento.food_gerent.domain.estoques;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.estoques.itensEstoque.ItemEstoque;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumTipoEstoque;
import com.gerenciamento.food_gerent.utils.enumerated.EnumUnidadeMedida;

public class Estoque {
  private UUID id;
  private UUID lojaID;
  private String nomeEstoque;
  private EnumTipoEstoque tipoEstoque;
  private List<ItemEstoque> itens = new ArrayList<>();
  private EnumStatus status;
  private LocalDate dataCriacao; 
  private LocalDate dataAtualizacao;

  public Estoque() {
  }

  public Estoque(UUID id, UUID lojaID, String nomeLoja, String nomeEstoque, EnumTipoEstoque tipoEstoque,
      List<ItemEstoque> itens, EnumStatus status, LocalDate dataCriacao, LocalDate dataAtualizacao) {
    this.id = id;
    this.lojaID = lojaID;
    this.nomeEstoque = nomeEstoque;
    this.tipoEstoque = tipoEstoque;
    this.itens = itens;
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

  public UUID getLojaID() {
    return lojaID;
  }

  public void setLojaID(UUID lojaID) {
    this.lojaID = lojaID;
  }

  public String getNomeEstoque() {
    return nomeEstoque;
  }

  public void setNomeEstoque(String nomeEstoque) {
    this.nomeEstoque = nomeEstoque;
  }

  public EnumTipoEstoque getTipoEstoque() {
    return tipoEstoque;
  }

  public void setTipoEstoque(EnumTipoEstoque tipoEstoque) {
    this.tipoEstoque = tipoEstoque;
  }

  public List<ItemEstoque> getItens() {
    return itens;
  }

  public void setItens(List<ItemEstoque> itens) {
    this.itens = itens;
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

  public void registrarEntrada(UUID ingredienteId, UUID loteId, BigDecimal quantidade, EnumUnidadeMedida unidade, LocalDate validade) { 
  // localizar item existente ou criar novo 
  // // incrementar quantidade 
  } 
  public void registrarSaida(UUID ingredienteId, BigDecimal quantidade, EnumUnidadeMedida unidade) { 
    // aplicar FIFO por validade 
    // decrementar quantidades 
    // lançar exceção se insuficiente 
  } 
  public List<ItemEstoque> consultarDisponibilidade(UUID ingredienteId) { 
    return itens.stream() .filter(i -> i.getIngredienteID().equals(ingredienteId)) .sorted(Comparator.comparing(ItemEstoque::getDataValidade)) .toList(); 
  }
}

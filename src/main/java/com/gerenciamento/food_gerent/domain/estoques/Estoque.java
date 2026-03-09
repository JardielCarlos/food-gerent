package com.gerenciamento.food_gerent.domain.estoques;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.estoques.itensEstoque.ItemEstoque;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumTipoEstoque;

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

  // ── ENTRADA: adicionar item individual (com ou sem lote) ──

  public void adicionarItem(ItemEstoque novoItem) {
    if (novoItem == null) {
      throw new IllegalArgumentException("Item de estoque é obrigatório");
    }
    if (novoItem.getIngredienteID() == null) {
      throw new IllegalArgumentException("Ingrediente é obrigatório");
    }
    if (novoItem.getQuantidade() == null || novoItem.getQuantidade().compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Quantidade deve ser maior que zero");
    }
    if (novoItem.getUnidade() == null) {
      throw new IllegalArgumentException("Unidade de medida é obrigatória");
    }

    ItemEstoque itemExistente = buscarItemExistente(novoItem.getIngredienteID(), novoItem.getLoteID());

    if (itemExistente != null) {
      if (!Objects.equals(itemExistente.getUnidade(), novoItem.getUnidade())) {
        throw new IllegalArgumentException("Unidade de medida divergente para ingrediente/lote já existente");
      }
      itemExistente.incrementar(novoItem.getQuantidade());
      return;
    }

    if (novoItem.getReservado() == null) {
      novoItem.setReservado(BigDecimal.ZERO);
    }
    this.itens.add(novoItem);
  }

  // ── SAÍDA: retirar ingrediente com FIFO por validade ──

  public void registrarSaida(UUID ingredienteId, BigDecimal quantidade) {
    if (ingredienteId == null) {
      throw new IllegalArgumentException("Ingrediente é obrigatório");
    }
    if (quantidade == null || quantidade.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Quantidade deve ser maior que zero");
    }

    List<ItemEstoque> itensDisponiveis = this.itens.stream()
      .filter(i -> i.getIngredienteID().equals(ingredienteId))
      .filter(i -> i.getStatus() == EnumStatus.ATIVO)
      .filter(i -> i.getDisponivel().compareTo(BigDecimal.ZERO) > 0)
      .sorted(Comparator.comparing(ItemEstoque::getDataValidade, Comparator.nullsLast(Comparator.naturalOrder())))
      .toList();

    BigDecimal totalDisponivel = itensDisponiveis.stream()
      .map(ItemEstoque::getDisponivel)
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    if (totalDisponivel.compareTo(quantidade) < 0) {
      throw new IllegalStateException(
        "Estoque insuficiente para ingrediente. Disponível: " + totalDisponivel + ", Solicitado: " + quantidade
      );
    }

    BigDecimal restante = quantidade;
    for (ItemEstoque item : itensDisponiveis) {
      if (restante.compareTo(BigDecimal.ZERO) <= 0) break;

      BigDecimal disponivel = item.getDisponivel();
      BigDecimal aDecrementar = restante.min(disponivel);
      item.decrementar(aDecrementar);
      restante = restante.subtract(aDecrementar);
    }
  }

  // ── CONSULTAS DE VALIDADE ──

  public List<ItemEstoque> consultarVencidos(LocalDate hoje) {
    return this.itens.stream()
      .filter(i -> i.getStatus() == EnumStatus.ATIVO)
      .filter(i -> i.isVencido(hoje))
      .sorted(Comparator.comparing(ItemEstoque::getDataValidade))
      .toList();
  }

  public List<ItemEstoque> consultarProximosAVencer(LocalDate hoje, int diasLimite) {
    return this.itens.stream()
      .filter(i -> i.getStatus() == EnumStatus.ATIVO)
      .filter(i -> i.isProximoDeVencer(hoje, diasLimite))
      .sorted(Comparator.comparing(ItemEstoque::getDataValidade))
      .toList();
  }

  public List<ItemEstoque> consultarDisponibilidade(UUID ingredienteId) {
    return this.itens.stream()
      .filter(i -> i.getIngredienteID().equals(ingredienteId))
      .filter(i -> i.getStatus() == EnumStatus.ATIVO)
      .sorted(Comparator.comparing(ItemEstoque::getDataValidade, Comparator.nullsLast(Comparator.naturalOrder())))
      .toList();
  }

  public BigDecimal getQuantidadeTotalDisponivel(UUID ingredienteId) {
    return consultarDisponibilidade(ingredienteId).stream()
      .map(ItemEstoque::getDisponivel)
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  // ── HELPER PRIVADO ──

  private ItemEstoque buscarItemExistente(UUID ingredienteId, UUID loteId) {
    return this.itens.stream()
      .filter(item -> item.getIngredienteID().equals(ingredienteId)
        && Objects.equals(item.getLoteID(), loteId))
      .findFirst()
      .orElse(null);
  }
}

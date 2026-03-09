package com.gerenciamento.food_gerent.application.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gerenciamento.food_gerent.application.usecases.EstoqueUseCases;
import com.gerenciamento.food_gerent.domain.estoques.Estoque;
import com.gerenciamento.food_gerent.domain.estoques.EstoquePatchDTO;
import com.gerenciamento.food_gerent.domain.estoques.EstoqueRepository;
import com.gerenciamento.food_gerent.domain.estoques.EstoqueRequestDTO;
import com.gerenciamento.food_gerent.domain.estoques.EstoqueResponseDTO;
import com.gerenciamento.food_gerent.domain.estoques.itensEstoque.ItemEstoque;
import com.gerenciamento.food_gerent.domain.estoques.itensEstoque.ItemEstoqueRequestDTO;
import com.gerenciamento.food_gerent.domain.estoques.itensEstoque.ItemEstoqueResponseDTO;
import com.gerenciamento.food_gerent.domain.estoques.itensEstoque.RetiradaEstoqueRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.Ingrediente;
import com.gerenciamento.food_gerent.domain.ingredientes.IngredienteRepository;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngrediente;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngredienteRepository;
import com.gerenciamento.food_gerent.domain.loja.Loja;
import com.gerenciamento.food_gerent.domain.loja.LojaRepository;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.mappers.EstoqueMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstoqueServiceImpl implements EstoqueUseCases {
  
  private final EstoqueRepository estoqueRepository;
  private final LojaRepository lojaRepository;
  private final IngredienteRepository ingredienteRepository;
  private final LoteIngredienteRepository loteIngredienteRepository;
  private final EstoqueMapper estoqueMapper;

  @Override
  public List<EstoqueResponseDTO> findAll() {
    List<Estoque> estoques = this.estoqueRepository.findAll();

    Set<UUID> lojaIds = estoques.stream()
      .map(Estoque::getLojaID)
      .collect(Collectors.toSet());
    
    Map<UUID, String> lojaNomes = lojaRepository.findAllById(lojaIds)
      .stream()
      .collect(Collectors.toMap(Loja::getId,Loja::getNome));
    
    return estoques.stream()
      .map(e -> {
        EstoqueResponseDTO dto = estoqueMapper.domainToResponse(e);
        String nomeLoja = lojaNomes.get(e.getLojaID());
        return new EstoqueResponseDTO(
          dto.id(),
          dto.lojaID(),
          nomeLoja,
          dto.nomeEstoque(),
          dto.tipoEstoque(),
          dto.itens(),
          dto.status(),
          dto.dataCriacao(),
          dto.dataAtualizacao()
        );
      })
      .toList();
  }

  @Override
  public EstoqueResponseDTO findById(UUID id) {
    Estoque estoque = this.estoqueRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado!"));

    Loja loja = this.lojaRepository.findById(estoque.getLojaID())
      .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada!"));

    return toResponse(estoque, loja.getNome());
  }

  @Override
  public EstoqueResponseDTO save(EstoqueRequestDTO estoque) {
    Loja loja = this.lojaRepository.findById(estoque.lojaID())
      .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada!"));
    
    Estoque estoqueEntity = estoqueMapper.requestToDomain(estoque);
    Estoque savedEstoque = this.estoqueRepository.save(estoqueEntity);

    return toResponse(savedEstoque, loja.getNome());
  }

  @Override
  public EstoqueResponseDTO update(UUID id, EstoquePatchDTO estoque) {
    Estoque existingEstoque = this.estoqueRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado!"));

    Loja loja = this.lojaRepository.findById(existingEstoque.getLojaID())
      .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada!"));

    Estoque estoqueToUpdate = estoqueMapper.updateEstoqueFromPatchDTO(estoque, existingEstoque);
    Estoque updatedEstoque = this.estoqueRepository.save(estoqueToUpdate);
    return toResponse(updatedEstoque, loja.getNome());
  }

  @Override
  public void deleteById(UUID id) {
    this.estoqueRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado!"));
    this.estoqueRepository.deleteById(id);
  }

  @Override
  public EstoqueResponseDTO adicionarItem(UUID estoqueID, ItemEstoqueRequestDTO itemDTO) {
    Estoque estoque = this.estoqueRepository.findById(estoqueID)
      .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado!"));

    Loja loja = this.lojaRepository.findById(estoque.getLojaID())
      .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada!"));

    Ingrediente ingrediente = this.ingredienteRepository.findById(itemDTO.ingredienteID())
      .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado!"));

    ItemEstoque novoItem = new ItemEstoque();
    novoItem.setNomeIngrediente(ingrediente.getNome());
    novoItem.setIngredienteID(itemDTO.ingredienteID());
    novoItem.setLoteID(itemDTO.loteID());
    novoItem.setQuantidade(itemDTO.quantidade());
    novoItem.setReservado(itemDTO.reservado());
    novoItem.setUnidade(itemDTO.unidade());
    novoItem.setDataValidade(itemDTO.dataValidade());
    novoItem.setStatus(itemDTO.status() == null ? EnumStatus.ATIVO : itemDTO.status());

    estoque.adicionarItem(novoItem);
    Estoque estoqueAtualizado = this.estoqueRepository.save(estoque);

    return toResponse(estoqueAtualizado, loja.getNome());
  }

  @Override
  public EstoqueResponseDTO adicionarLote(UUID estoqueID, UUID loteID) {
    Estoque estoque = this.estoqueRepository.findById(estoqueID)
      .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado!"));

    Loja loja = this.lojaRepository.findById(estoque.getLojaID())
      .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada!"));

    LoteIngrediente lote = this.loteIngredienteRepository.findById(loteID)
      .orElseThrow(() -> new EntityNotFoundException("Lote não encontrado!"));

    Ingrediente ingrediente = this.ingredienteRepository.findById(lote.getIngredienteId())
      .orElseThrow(() -> new EntityNotFoundException("Ingrediente do lote não encontrado!"));

    ItemEstoque novoItem = new ItemEstoque();
    novoItem.setNomeIngrediente(ingrediente.getNome());
    novoItem.setIngredienteID(lote.getIngredienteId());
    novoItem.setLoteID(lote.getId());
    novoItem.setQuantidade(lote.getQuantidadeDisponivel());
    novoItem.setUnidade(lote.getUnidadeMedida());
    novoItem.setDataValidade(lote.getDataValidade());
    novoItem.setStatus(EnumStatus.ATIVO);

    estoque.adicionarItem(novoItem);
    Estoque estoqueAtualizado = this.estoqueRepository.save(estoque);

    return toResponse(estoqueAtualizado, loja.getNome());
  }

  @Override
  public EstoqueResponseDTO registrarSaida(UUID estoqueID, RetiradaEstoqueRequestDTO retirada) {
    Estoque estoque = this.estoqueRepository.findById(estoqueID)
      .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado!"));

    Loja loja = this.lojaRepository.findById(estoque.getLojaID())
      .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada!"));

    estoque.registrarSaida(retirada.ingredienteID(), retirada.quantidade());
    Estoque estoqueAtualizado = this.estoqueRepository.save(estoque);

    return toResponse(estoqueAtualizado, loja.getNome());
  }

  @Override
  public List<ItemEstoqueResponseDTO> consultarVencidos(UUID estoqueID) {
    Estoque estoque = this.estoqueRepository.findById(estoqueID)
      .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado!"));

    return estoque.consultarVencidos(java.time.LocalDate.now()).stream()
      .map(estoqueMapper::itemDomainToResponse)
      .toList();
  }

  @Override
  public List<ItemEstoqueResponseDTO> consultarProximosAVencer(UUID estoqueID, int dias) {
    Estoque estoque = this.estoqueRepository.findById(estoqueID)
      .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado!"));

    return estoque.consultarProximosAVencer(java.time.LocalDate.now(), dias).stream()
      .map(estoqueMapper::itemDomainToResponse)
      .toList();
  }

  private EstoqueResponseDTO toResponse(Estoque estoque, String nomeLoja) {
    return new EstoqueResponseDTO(
      estoque.getId(),
      estoque.getLojaID(),
      nomeLoja,
      estoque.getNomeEstoque(),
      estoque.getTipoEstoque(),
      estoque.getItens().stream()
        .map(estoqueMapper::itemDomainToResponse)
        .toList(),
      estoque.getStatus(),
      estoque.getDataCriacao(),
      estoque.getDataAtualizacao()
    );
  }

}

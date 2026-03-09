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
import com.gerenciamento.food_gerent.domain.loja.Loja;
import com.gerenciamento.food_gerent.domain.loja.LojaRepository;
import com.gerenciamento.food_gerent.utils.mappers.EstoqueMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstoqueServiceImpl implements EstoqueUseCases{
  
  private final EstoqueRepository estoqueRepository;
  private final LojaRepository lojaRepository;
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

    Loja loja = this.lojaRepository.findById(estoque.getLojaID()).get();
    return new EstoqueResponseDTO(
      estoque.getId(),
      estoque.getLojaID(),
      loja.getNome(),
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

  @Override
  public EstoqueResponseDTO save(EstoqueRequestDTO estoque) {
    Loja loja = this.lojaRepository.findById(estoque.lojaID())
      .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada!"));
    
    Estoque estoqueEntity = estoqueMapper.requestToDomain(estoque);
    Estoque savedEstoque = this.estoqueRepository.save(estoqueEntity);

    return new EstoqueResponseDTO(
      savedEstoque.getId(),
      savedEstoque.getLojaID(),
      loja.getNome(),
      savedEstoque.getNomeEstoque(),
      savedEstoque.getTipoEstoque(),
      savedEstoque.getItens().stream()
        .map(estoqueMapper::itemDomainToResponse)
        .toList(),
      savedEstoque.getStatus(),
      savedEstoque.getDataCriacao(),
      savedEstoque.getDataAtualizacao()
    );
  }

  @Override
  public EstoqueResponseDTO update(UUID id, EstoquePatchDTO estoque) {
    Estoque existingEstoque = this.estoqueRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado!"));

    Loja loja = this.lojaRepository.findById(existingEstoque.getLojaID())
      .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada!"));

    Estoque estoqueToUpdate = estoqueMapper.updateEstoqueFromPatchDTO(estoque, existingEstoque);
    Estoque updatedEstoque = this.estoqueRepository.save(estoqueToUpdate);
    return new EstoqueResponseDTO(
      updatedEstoque.getId(),
      updatedEstoque.getLojaID(),
      loja.getNome(),
      updatedEstoque.getNomeEstoque(),
      updatedEstoque.getTipoEstoque(),
      updatedEstoque.getItens().stream()
        .map(estoqueMapper::itemDomainToResponse)
        .toList(),
      updatedEstoque.getStatus(),
      updatedEstoque.getDataCriacao(),
      updatedEstoque.getDataAtualizacao()
    );
  }

  @Override
  public void deleteById(UUID id) {
    this.estoqueRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado!"));
    this.estoqueRepository.deleteById(id);
  }

}

package com.gerenciamento.food_gerent.application.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.gerenciamento.food_gerent.application.usecases.PermissaoUseCases;
import com.gerenciamento.food_gerent.domain.permissoes.Permissao;
import com.gerenciamento.food_gerent.domain.permissoes.PermissaoRepository;
import com.gerenciamento.food_gerent.domain.permissoes.PermissaoRequestDTO;
import com.gerenciamento.food_gerent.domain.permissoes.PermissaoResponseDTO;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.PermissaoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissaoServiceImpl implements PermissaoUseCases {

  private final PermissaoRepository repository;
  private final PermissaoMapper mapper;

  @Override
  public Set<PermissaoResponseDTO> getAllPermissoes() {
    Set<Permissao> permissoes = this.repository.findAll();
    return this.mapper.toResponseDTOSet(permissoes);
  }

  @Override
  public PermissaoResponseDTO getPermissaoById(Long permissaoId) {
    Permissao permissao = repository.findById(permissaoId)
      .orElseThrow(() -> new EntityNotFoundException("Permissão não encontrada com id: " + permissaoId));

    return mapper.toResponseDTO(permissao);
  }

  @Override
  public PermissaoResponseDTO createPermissao(PermissaoRequestDTO permissaoDTO) {
    Permissao permissao = mapper.toEntity(permissaoDTO);
    Permissao savedPermissao = repository.save(permissao);
    return mapper.toResponseDTO(savedPermissao);
  }

  @Override
  public PermissaoResponseDTO updatePermissao(Long permissaoId, PermissaoRequestDTO permissaoDTO) {
    Permissao permissao = mapper.toEntity(permissaoDTO);

    Permissao updatedPermissao = repository.update(permissaoId, permissao);
    return mapper.toResponseDTO(updatedPermissao);
  }

  @Override
  public void deletePermissao(Long permissaoId) {
    Permissao permissao = repository.findById(permissaoId)
      .orElseThrow(() -> new EntityNotFoundException("Permissão não encontrada com id: " + permissaoId));
      
    repository.deleteById(permissao.getPermissaoId());
  }
}

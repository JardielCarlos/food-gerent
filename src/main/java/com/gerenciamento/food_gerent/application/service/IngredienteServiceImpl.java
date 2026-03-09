package com.gerenciamento.food_gerent.application.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gerenciamento.food_gerent.application.usecases.IngredienteUseCases;
import com.gerenciamento.food_gerent.domain.ingredientes.Ingrediente;
import com.gerenciamento.food_gerent.domain.ingredientes.IngredientePatchtDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.IngredienteRepository;
import com.gerenciamento.food_gerent.domain.ingredientes.IngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.IngredienteResponseDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaRepository;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngrediente;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngredienteRepository;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.IngredienteMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredienteServiceImpl implements IngredienteUseCases {

  private final IngredienteRepository ingredienteRepository;
  private final CategoriaRepository categoriaRepository;
  private final TagIngredienteRepository tagIngredienteRepository;
  private final IngredienteMapper ingredienteMapper;

  @Override
  public List<IngredienteResponseDTO> getAllIngredientes() {
   List<Ingrediente> ingredientes = this.ingredienteRepository.findAll();
    return ingredienteMapper.domainToResponseList(ingredientes);
  }

  @Override
  public IngredienteResponseDTO getIngredienteById(UUID id) {
    Ingrediente ingrediente = this.ingredienteRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado"));
    return ingredienteMapper.domainToResponse(ingrediente);
  }

  @Override
  public IngredienteResponseDTO createIngrediente(IngredienteRequestDTO ingredienteDTO) {
    this.categoriaRepository.findById(ingredienteDTO.categoriaId())
      .orElseThrow(() -> new EntityNotFoundException("Categoria de ingrediente não encontrada com o ID: " + ingredienteDTO.categoriaId()));
      
    List<UUID> requestedTagIds = ingredienteDTO.tagsIds() == null
        ? Collections.emptyList()
        : Arrays.asList(ingredienteDTO.tagsIds());

    List<TagIngrediente> tags = this.tagIngredienteRepository.findAllById(requestedTagIds);

    if (!requestedTagIds.isEmpty()) {
      Set<UUID> foundIds = new HashSet<>();
      for (TagIngrediente tag : tags) {
        if (tag != null && tag.getId() != null) {
          foundIds.add(tag.getId());
        }
      }

      Set<UUID> tagsAusentes = new HashSet<>(requestedTagIds);
      tagsAusentes.removeAll(foundIds);

      if (!tagsAusentes.isEmpty()) {
        throw new EntityNotFoundException("Tags não encontradas: " + tagsAusentes);
      }
    }

    Set<UUID> tagsIdsSet = ingredienteDTO.tagsIds() == null
        ? new HashSet<>()
        : new HashSet<>(requestedTagIds);

    Ingrediente ingrediente = ingredienteMapper.toEntity(ingredienteDTO, tagsIdsSet);
    
    Ingrediente saved = this.ingredienteRepository.save(ingrediente);
    return ingredienteMapper.domainToResponse(saved);
  }

  @Override
  public IngredienteResponseDTO updateIngrediente(UUID id, IngredientePatchtDTO ingredienteDTO) {
    Ingrediente ingredienteAtual = this.ingredienteRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado com o ID: " + id));

    if (ingredienteDTO.categoriaId() != null) {
      this.categoriaRepository.findById(ingredienteDTO.categoriaId())
        .orElseThrow(() -> new EntityNotFoundException("Categoria de ingrediente não encontrada com o ID: " + ingredienteDTO.categoriaId()));
    }

    List<UUID> requestedTagIds = ingredienteDTO.tagsIds() == null
      ? Collections.emptyList()
      : Arrays.asList(ingredienteDTO.tagsIds());

    List<TagIngrediente> tags = this.tagIngredienteRepository.findAllById(requestedTagIds);

    Set<UUID> foundIds = new HashSet<>();
    for (TagIngrediente tag : tags) {
      if (tag != null && tag.getId() != null) {
        foundIds.add(tag.getId());
      }
    }

    if (!requestedTagIds.isEmpty()) {
      Set<UUID> tagsAusentes = new HashSet<>(requestedTagIds);
      tagsAusentes.removeAll(foundIds);

      if (!tagsAusentes.isEmpty()) {
        throw new EntityNotFoundException("Tags não encontradas: " + tagsAusentes);
      }
    }

    ingredienteMapper.updateIngredienteFromPatchDto(ingredienteDTO, ingredienteAtual);

    // Se forneceu tags, garanta que somente os IDs efetivamente existentes sejam atribuídos
    if (!requestedTagIds.isEmpty()) {
      ingredienteAtual.setTagsIds(foundIds);
    }

    Ingrediente saved = this.ingredienteRepository.save(ingredienteAtual);
    return ingredienteMapper.domainToResponse(saved);
  }

  @Override
  public void deleteIngrediente(UUID id) {
    Ingrediente ingrediente = this.ingredienteRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado com o ID: " + id));
    this.ingredienteRepository.delete(ingrediente);
  }
}

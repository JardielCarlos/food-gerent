package com.gerenciamento.food_gerent.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gerenciamento.food_gerent.application.usecases.TagIngredienteUseCases;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngrediente;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngredientePatchDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngredienteRepository;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngredienteResponseDTO;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.TagIngredienteMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagIngredienteServiceImpl implements TagIngredienteUseCases {
  
  private final TagIngredienteRepository tagIngredienteRepository;
  private final TagIngredienteMapper tagIngredienteMapper;

  @Override
  public List<TagIngredienteResponseDTO> getAllTags() {
    List<TagIngrediente> tags = this.tagIngredienteRepository.findAll();
    return tagIngredienteMapper.domainToResponseList(tags);
  }
  @Override
  public TagIngredienteResponseDTO getTagById(UUID id) {
    TagIngrediente tag = this.tagIngredienteRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Tag não encontrada"));
    return tagIngredienteMapper.domainToResponse(tag);
  }
  @Override
  public TagIngredienteResponseDTO createTag(TagIngredienteRequestDTO data) {
    TagIngrediente tag = tagIngredienteMapper.requestToDomain(data);
    TagIngrediente savedTag = this.tagIngredienteRepository.save(tag);
    return tagIngredienteMapper.domainToResponse(savedTag);
  }
  @Override
  public TagIngredienteResponseDTO updateTag(UUID id, TagIngredientePatchDTO data) {
    TagIngrediente existingTag = this.tagIngredienteRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Tag não encontrada"));

    TagIngrediente updatedTag = tagIngredienteMapper.updateTagIngredienteFromPatchDTO(data, existingTag);
   
    TagIngrediente savedTag = this.tagIngredienteRepository.save(updatedTag);
    return tagIngredienteMapper.domainToResponse(savedTag);
  }
  @Override
  public void deleteTag(UUID id) {
    this.tagIngredienteRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Tag não encontrada"));
    this.tagIngredienteRepository.deleteById(id);
  }

}

package com.gerenciamento.food_gerent.utils.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaTagIngredienteEntity;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngrediente;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngredientePatchDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngredienteResponseDTO;

@Mapper(componentModel = "spring")
public interface TagIngredienteMapper {

  JpaTagIngredienteEntity domainToJpa(TagIngrediente tag);
  TagIngrediente jpaToDomain(JpaTagIngredienteEntity jpaTagIngredienteEntity);
  List<TagIngrediente> jpaToDomainList(List<JpaTagIngredienteEntity> tagsIngredienteEntity);
  
  
  TagIngredienteResponseDTO domainToResponse(TagIngrediente tag);
  List<TagIngredienteResponseDTO> domainToResponseList(List<TagIngrediente> tags);

  @Mapping(target = "id", ignore = true) 
  @Mapping(target = "dataCriacao", ignore = true) 
  @Mapping(target = "dataAtualizacao", ignore = true) 
  @Mapping(target = "status", ignore = true)
  TagIngrediente requestToDomain(TagIngredienteRequestDTO data);

  @Mapping(target = "id", ignore = true) 
  @Mapping(target = "dataCriacao", ignore = true) 
  @Mapping(target = "dataAtualizacao", ignore = true) 
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) TagIngrediente updateTagIngredienteFromPatchDTO(TagIngredientePatchDTO data, @MappingTarget TagIngrediente existingTag);
  
}

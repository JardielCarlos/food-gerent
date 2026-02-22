package com.gerenciamento.food_gerent.utils.mappers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaCategoriaIngredienteEntity;
import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaIngredienteEntity;
import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaTagIngredienteEntity;
import com.gerenciamento.food_gerent.domain.ingredientes.Ingrediente;
import com.gerenciamento.food_gerent.domain.ingredientes.IngredientePatchtDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.IngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.IngredienteResponseDTO;

@Mapper(componentModel = "spring")
public interface IngredienteMapper {

  @Mapping(source = "categoria.id", target = "categoriaId")
  @Mapping(source = "categoria.nome", target = "categoriaNome")
  @Mapping(source = "tags", target = "tagsIds", qualifiedByName = "mapTagsToIds")
  @Mapping(source = "tags", target = "tagsNomes", qualifiedByName = "mapTagsToNames")
  Ingrediente jpaToDomain(JpaIngredienteEntity jpaIngredienteEntity);

  List<Ingrediente> jpaToDomainList(List<JpaIngredienteEntity> ingredienteEntity);

  
  @Mapping(source = "categoriaId", target = "categoria", qualifiedByName = "mapCategoriaIdToCategoria")
  @Mapping(source = "tagsIds", target = "tags", qualifiedByName = "mapIdsToTags")
  JpaIngredienteEntity domainToJpa(Ingrediente ingrediente);


  @Named("mapTagsToIds")
  default Set<UUID> mapTagsToIds(Set<JpaTagIngredienteEntity> tags) {
    if (tags == null) {
      return new HashSet<>();
    }
    return tags.stream()
      .map(JpaTagIngredienteEntity::getId)
      .collect(Collectors.toSet());
  }

  @Named("mapTagsToNames")
  default Set<String> mapTagsToNames(Set<JpaTagIngredienteEntity> tags) {
    if (tags == null) {
      return new HashSet<>();
    }
    return tags.stream()
      .map(JpaTagIngredienteEntity::getNome)
      .collect(Collectors.toSet());
  }

  @Named("mapIdsToTags")
  default Set<JpaTagIngredienteEntity> mapIdsToTags(Set<UUID> ids) {
    if (ids == null) {
      return new HashSet<>();
    }
    return ids.stream().map(id -> {
      JpaTagIngredienteEntity tag = new JpaTagIngredienteEntity();
      tag.setId(id);
      return tag;
    }).collect(Collectors.toSet());
  }

  @Named("mapCategoriaIdToCategoria")
  default JpaCategoriaIngredienteEntity mapCategoriaIdToCategoria(UUID id) {
    if (id == null) {
      return null;
    }
    JpaCategoriaIngredienteEntity cat = new JpaCategoriaIngredienteEntity();
    cat.setId(id);
    return cat;
  }

  @Mapping(source = "categoriaNome", target = "categoriaNome")
  @Mapping(source = "tagsNomes", target = "tagsNomes")
  IngredienteResponseDTO domainToResponse(Ingrediente ingrediente);
  List<IngredienteResponseDTO> domainToResponseList(List<Ingrediente> ingredientes);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "dataCriacao", ignore = true)
  @Mapping(target = "dataAtualizacao", ignore = true)
  @Mapping(target = "categoriaNome", ignore = true)
  @Mapping(target = "tagsNomes", ignore = true)
  Ingrediente toEntity(IngredienteRequestDTO ingredienteDTO, Set<UUID> tagsIdsSet);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "dataCriacao", ignore = true)
  @Mapping(target = "dataAtualizacao", ignore = true)
  @Mapping(target = "categoriaNome", ignore = true)
  @Mapping(target = "tagsNomes", ignore = true)
  Ingrediente toEntityPatch(IngredientePatchtDTO ingredienteDTO, Set<UUID> tagsIdsSet);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "dataCriacao", ignore = true)
  @Mapping(target = "dataAtualizacao", ignore = true)
  @Mapping(target = "categoriaNome", ignore = true)
  @Mapping(target = "tagsNomes", ignore = true)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateIngredienteFromPatchDto(IngredientePatchtDTO ingredienteDTO, @MappingTarget Ingrediente ingrediente);
}


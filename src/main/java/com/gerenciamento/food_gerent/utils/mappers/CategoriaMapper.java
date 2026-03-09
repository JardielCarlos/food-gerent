package com.gerenciamento.food_gerent.utils.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaCategoriaIngredienteEntity;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaIngrediente;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaIngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaIngredienteResponseDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.SubCategoriaResponseDTO;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

 @Mapping(source = "parent.id", target = "parentId") 
 @Mapping(target = "nivel", source = "parent", qualifiedByName = "calcNivelFromParent")
 @Mapping(target = "subcategorias", ignore = true)
  CategoriaIngrediente jpaToDomain(JpaCategoriaIngredienteEntity entity);
  List<CategoriaIngrediente> jpaToDomainList(List<JpaCategoriaIngredienteEntity> categoriaEntities);

  @Mapping(target = "parent", ignore = true)
  @Mapping(target = "subcategorias", ignore = true)
  JpaCategoriaIngredienteEntity domainToJpa(CategoriaIngrediente categoria);

  CategoriaIngredienteResponseDTO domainToResponse(CategoriaIngrediente categoria);
  List<CategoriaIngredienteResponseDTO> domainToResponseList(List<CategoriaIngrediente> categorias);

  @Mapping(target = "id", ignore = true) 
  @Mapping(target = "dataCriacao", ignore = true) 
  @Mapping(target = "dataAtualizacao", ignore = true) 
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "subcategorias", ignore = true)
  @Mapping(target = "nivel", ignore = true)
  CategoriaIngrediente requestToDomain(CategoriaIngredienteRequestDTO categoriaDTO);

  @Named("calcNivelFromParent") 
  default Integer calcNivelFromParent(JpaCategoriaIngredienteEntity parent) { 
    int level = 0; 
    JpaCategoriaIngredienteEntity p = parent; 
    while (p != null) { 
      level++; 
      p = p.getParent(); 
      if (level > 100) 
        break; 
    } 
    return level; 
  }

  /**
   * Conversão explícita CategoriaIngrediente → SubCategoriaResponseDTO.
   * Evita que o MapStruct auto-gere um construtor com parâmetros extras.
   */
  default SubCategoriaResponseDTO domainToSubResponse(CategoriaIngrediente c) {
    if (c == null) return null;
    List<SubCategoriaResponseDTO> subs = c.getSubcategorias() == null ? null :
        c.getSubcategorias().stream()
            .map(this::domainToSubResponse)
            .collect(Collectors.toList());
    return new SubCategoriaResponseDTO(
        c.getId(),
        c.getNome(),
        c.getParentId(),
        c.getNivel(),
        subs
    );
  }
}

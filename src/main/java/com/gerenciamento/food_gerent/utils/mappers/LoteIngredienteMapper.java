package com.gerenciamento.food_gerent.utils.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaLoteIngredienteEntity;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngrediente;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngredientePatchDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngredienteResponseDTO;

@Mapper(componentModel = "spring")
public interface LoteIngredienteMapper {

  JpaLoteIngredienteEntity domainToJpa(LoteIngrediente loteIngrediente);
  LoteIngrediente jpaToDomain(JpaLoteIngredienteEntity loteIngredienteEntity);
  List<LoteIngrediente> jpaToDomainList(List<JpaLoteIngredienteEntity> loteIngredienteEntity);
  
  LoteIngredienteResponseDTO toResponseDTO(LoteIngrediente loteIngrediente);
  LoteIngredienteResponseDTO toResponseDTO(LoteIngrediente loteIngrediente, String ingredienteNome);
  List<LoteIngredienteResponseDTO> toResponseDTOList(List<LoteIngrediente> loteIngredientes);
  
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "dataCriacao", ignore = true)
  @Mapping(target = "dataAtualizacao", ignore = true)
  LoteIngrediente toEntity(LoteIngredienteRequestDTO data);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "dataCriacao", ignore = true)
  @Mapping(target = "dataAtualizacao", ignore = true)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  LoteIngrediente updateLoteIngredienteFromPatchDTO(LoteIngredientePatchDTO data, @MappingTarget LoteIngrediente loteIngrediente);
  
}

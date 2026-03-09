package com.gerenciamento.food_gerent.utils.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaEstoqueEntity;
import com.gerenciamento.food_gerent.domain.estoques.Estoque;
import com.gerenciamento.food_gerent.domain.estoques.EstoquePatchDTO;
import com.gerenciamento.food_gerent.domain.estoques.EstoqueRequestDTO;
import com.gerenciamento.food_gerent.domain.estoques.EstoqueResponseDTO;
import com.gerenciamento.food_gerent.domain.estoques.itensEstoque.ItemEstoque;
import com.gerenciamento.food_gerent.domain.estoques.itensEstoque.ItemEstoqueResponseDTO;

@Mapper(componentModel = "spring", uses = { ItemEstoqueMapper.class })
public interface EstoqueMapper {

  Estoque jpaToDomain(JpaEstoqueEntity estoqueEntity);
  List<Estoque> jpaToDomainList(List<JpaEstoqueEntity> estoqueEntities);

  JpaEstoqueEntity domainToJpa(Estoque estoque);

  @Mapping(target = "nomeLoja", ignore = true)
  EstoqueResponseDTO domainToResponse(Estoque estoque);
  List<EstoqueResponseDTO> domainToResponseList(List<Estoque> estoques);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "dataCriacao", ignore = true)
  @Mapping(target = "dataAtualizacao", ignore = true)
  @Mapping(target = "itens", ignore = true)
  Estoque requestToDomain(EstoqueRequestDTO estoque);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "lojaID", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "dataCriacao", ignore = true)
  @Mapping(target = "dataAtualizacao", ignore = true)
  @Mapping(target = "itens", ignore = true)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Estoque updateEstoqueFromPatchDTO(EstoquePatchDTO estoque, @MappingTarget Estoque existingEstoque);

  ItemEstoqueResponseDTO itemDomainToResponse(ItemEstoque item);
  
}

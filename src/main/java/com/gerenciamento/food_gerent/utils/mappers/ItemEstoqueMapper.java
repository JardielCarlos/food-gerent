package com.gerenciamento.food_gerent.utils.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaItemEstoqueEntity;
import com.gerenciamento.food_gerent.domain.estoques.itensEstoque.ItemEstoque;

@Mapper(componentModel = "spring")
public interface ItemEstoqueMapper {

  // mapeia ItemEstoque -> JpaItemEstoqueEntity, mas ignora o campo 'estoque'
  @Mapping(target = "estoque", ignore = true)
  JpaItemEstoqueEntity domainToJpa(ItemEstoque item);

  ItemEstoque jpaToDomain(JpaItemEstoqueEntity entity);

  List<JpaItemEstoqueEntity> domainToJpaList(List<ItemEstoque> itens);
  List<ItemEstoque> jpaToDomainList(List<JpaItemEstoqueEntity> entities);
}



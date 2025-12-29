package com.gerenciamento.food_gerent.utils.mappers;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaPermissaoEntity;
import com.gerenciamento.food_gerent.domain.permissoes.Permissao;
import com.gerenciamento.food_gerent.domain.permissoes.PermissaoRequestDTO;
import com.gerenciamento.food_gerent.domain.permissoes.PermissaoResponseDTO;

@Mapper(componentModel = "spring")
public interface PermissaoMapper {
  
  @Mapping(target = "permissaoId", source = "permissaoId")
  @Mapping(target = "nome", source = "nome")
  Permissao jpaToDomain(JpaPermissaoEntity permissao);

  @Mapping(target = "permissaoId", source = "permissaoId")
  @Mapping(target = "nome", source = "nome")
  Set<Permissao> jpaToDomainSet(List<JpaPermissaoEntity> permissoes);

  @Mapping(target = "nome", source = "nome")
  JpaPermissaoEntity toDomain(Permissao permissao);

  @Mapping(target = "permissaoId", source = "permissaoId")
  @Mapping(target = "nome", source = "nome")
  Set<PermissaoResponseDTO> toResponseDTOSet(Set<Permissao> permissoes);

  PermissaoResponseDTO toResponseDTO(Permissao permissao);

  @Mapping(target = "permissaoId", ignore = true)
  @Mapping(target = "nome", source = "nome")
  Permissao toEntity(PermissaoRequestDTO permissaoDTO);
}

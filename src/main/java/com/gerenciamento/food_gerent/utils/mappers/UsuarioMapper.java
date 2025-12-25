package com.gerenciamento.food_gerent.utils.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaUsuarioEntity;
import com.gerenciamento.food_gerent.domain.usuarios.Usuario;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioResponseDTO;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
 
  @Mapping(source = "id", target = "id")
  @Mapping(source = "nome", target = "nome")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "telefone", target = "telefone")
  List<Usuario> jpaToDomainList(List<JpaUsuarioEntity> usuarios);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "nome", target = "nome")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "telefone", target = "telefone")
  List<UsuarioResponseDTO> toResponseDTOList(List<Usuario> usuarios);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "nome", target = "nome")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "telefone", target = "telefone")
  UsuarioResponseDTO toResponseDTO(Usuario usuario);
}

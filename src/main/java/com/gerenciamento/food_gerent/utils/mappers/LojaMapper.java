package com.gerenciamento.food_gerent.utils.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaLojaEntity;
import com.gerenciamento.food_gerent.domain.empresas.Empresa;
import com.gerenciamento.food_gerent.domain.loja.Loja;
import com.gerenciamento.food_gerent.domain.loja.LojaPatchDTO;
import com.gerenciamento.food_gerent.domain.loja.LojaRequestDTO;
import com.gerenciamento.food_gerent.domain.loja.LojaResponseDTO;

@Mapper(componentModel = "spring")
public interface LojaMapper {

  // JPA -> Domínio
  @Mapping(source = "id", target = "id")
  @Mapping(source = "empresa.id", target = "idEmpresa")
  Loja jpaToDomain(JpaLojaEntity entity);

  List<Loja> jpaToDomainList(List<JpaLojaEntity> lojaEntities);

  // Request DTO -> JPA
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "dataCriacao", ignore = true)
  @Mapping(target = "dataAtualizacao", ignore = true)
  @Mapping(source = "dto.idEmpresa", target = "idEmpresa")
  @Mapping(source = "dto.nome", target = "nome")
  @Mapping(source = "dto.cnpj", target = "cnpj")
  @Mapping(source = "dto.rua", target = "rua")
  @Mapping(source = "dto.bairro", target = "bairro")
  @Mapping(source = "dto.cidade", target = "cidade")
  @Mapping(source = "dto.estado", target = "estado")
  @Mapping(source = "dto.cep", target = "cep")
  @Mapping(source = "dto.telefone", target = "telefone")
  Loja requestToJpa(LojaRequestDTO dto, Empresa empresa);

  // Domínio -> JPA
  @Mapping(source = "idEmpresa", target = "empresa.id")
  JpaLojaEntity domainToJpa(Loja loja);

  // Domínio -> Response DTO
  LojaResponseDTO toResponseDTO(Loja loja);
  List<LojaResponseDTO> toResponseDTOList(List<Loja> lojas);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "dataCriacao", ignore = true)
  @Mapping(target = "dataAtualizacao", ignore = true)
  @Mapping(target = "idEmpresa", ignore = true)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Loja updateLojaEntityFromPatchDto(LojaPatchDTO data, @MappingTarget Loja loja);
}

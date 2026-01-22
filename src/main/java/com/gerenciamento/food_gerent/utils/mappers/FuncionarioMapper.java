package com.gerenciamento.food_gerent.utils.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaFuncionarioEntity;
import com.gerenciamento.food_gerent.domain.funcionarios.Funcionario;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioPatchDTO;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioRequestDTO;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioResponseDTO;

@Mapper(componentModel = "spring", uses = {LojaMapper.class})
public interface FuncionarioMapper {

  Funcionario jpaToDomain(JpaFuncionarioEntity funcionarioEntity);
  List<Funcionario> jpaToDomainList(List<JpaFuncionarioEntity> funcionarioEntities);

  @Mapping(target = "loja.empresa", ignore = true)
  JpaFuncionarioEntity domainToJpa(Funcionario funcionario);

  @Mapping(source = "loja", target = "loja")
  FuncionarioResponseDTO domainToResponse(Funcionario funcionario);
  List<FuncionarioResponseDTO> domainToResponseList(List<Funcionario> funcionarios);
  
  @Mapping(target = "id", ignore = true) 
  @Mapping(target = "dataCriacao", ignore = true) 
  @Mapping(target = "dataAtualizacao", ignore = true) 
  @Mapping(target = "permissoes", ignore = true)
  @Mapping(target = "cargo", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "loja", ignore = true)
  Funcionario requestToDomain(FuncionarioRequestDTO funcionarioDTO);

  @Mapping(target = "id", ignore = true) 
  @Mapping(target = "dataCriacao", ignore = true) 
  @Mapping(target = "dataAtualizacao", ignore = true) 
  @Mapping(target = "permissoes", ignore = true)
  @Mapping(target = "loja", ignore = true)
  @Mapping(source = "nome", target = "nome")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "cpf", target = "cpf")
  @Mapping(source = "salario", target = "salario")
  @Mapping(source = "telefone", target = "telefone")
  @Mapping(source = "senha", target = "senha")
  @Mapping(source = "cargo", target = "cargo")
  @Mapping(source = "status", target = "status")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Funcionario patchToDomain(FuncionarioPatchDTO funcionarioDTO, @MappingTarget Funcionario funcionario);
}
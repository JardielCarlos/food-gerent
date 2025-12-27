package com.gerenciamento.food_gerent.utils.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaEmpresaEntity;
import com.gerenciamento.food_gerent.domain.empresas.Empresa;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaPatchDTO;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaRequestDTO;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaResponseDTO;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "cliente.id", target = "idCliente")
  @Mapping(source = "nome", target = "nome")
  @Mapping(source = "cnpj", target = "cnpj")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "rua", target = "rua")
  @Mapping(source = "bairro", target = "bairro")
  @Mapping(source = "cidade", target = "cidade") 
  @Mapping(source = "estado", target = "estado")
  @Mapping(source = "cep", target = "cep")
  @Mapping(source = "telefone", target = "telefone")
  @Mapping(source = "status", target = "status")
  @Mapping(source = "dataCriacao", target = "dataCriacao")
  @Mapping(source = "dataAtualizacao", target = "dataAtualizacao")
  Empresa jpaToDomain(JpaEmpresaEntity entity);

  List<Empresa> jpaToDomainList(List<JpaEmpresaEntity> entities);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "idCliente", target = "idCliente")
  @Mapping(source = "nome", target = "nome")
  @Mapping(source = "cnpj", target = "cnpj")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "rua", target = "rua")
  @Mapping(source = "bairro", target = "bairro")
  @Mapping(source = "cidade", target = "cidade")
  @Mapping(source = "estado", target = "estado")
  @Mapping(source = "cep", target = "cep")
  @Mapping(source = "telefone", target = "telefone")
  @Mapping(source = "status", target = "status")
  @Mapping(source = "dataCriacao", target = "dataCriacao", qualifiedByName = "formatLocalDate")
  @Mapping(source = "dataAtualizacao", target = "dataAtualizacao", qualifiedByName = "formatLocalDate")
  EmpresaResponseDTO toResponseDTO(Empresa entity);

  List<EmpresaResponseDTO> toResponseDTOList(List<Empresa> empresas);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "dataCriacao", ignore = true)
  @Mapping(target = "dataAtualizacao", ignore = true)
  @Mapping(target = "idCliente", source = "idCliente")
  @Mapping(target = "nome", source = "nome")
  @Mapping(target = "cnpj", source = "cnpj")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "rua", source = "rua")
  @Mapping(target = "bairro", source = "bairro")
  @Mapping(target = "cidade", source = "cidade")
  @Mapping(target = "estado", source = "estado")
  @Mapping(target = "cep", source = "cep")
  @Mapping(target = "telefone", source = "telefone")
  Empresa toEntity(EmpresaRequestDTO dto);

  // @Mapping(target = "id", ignore = true) 
  // @Mapping(target = "idCliente", ignore = true) 
  // @Mapping(target = "dataCriacao", ignore = true) 
  // @Mapping(target = "dataAtualizacao", ignore = true) 
  // @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) 
  // void updateEmpresaFromPatchDto(EmpresaPatchDTO dto, @MappingTarget Empresa empresa); 
  @Mapping(target = "id", ignore = true) 
  @Mapping(target = "cliente", ignore = true) 
  @Mapping(target = "dataCriacao", ignore = true) 
  @Mapping(target = "dataAtualizacao", ignore = true) 
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateEmpresaEntityFromPatchDto(EmpresaPatchDTO dto, @MappingTarget JpaEmpresaEntity empresaEntity);

  @Named("formatLocalDate")
  static String formatLocalDate(LocalDate date){
    return date != null ? date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
  }
}

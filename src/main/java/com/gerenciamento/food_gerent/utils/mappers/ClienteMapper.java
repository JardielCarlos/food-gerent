package com.gerenciamento.food_gerent.utils.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaClienteEntity;
import com.gerenciamento.food_gerent.domain.clientes.Cliente;
import com.gerenciamento.food_gerent.domain.clientes.ClientePatchDTO;
import com.gerenciamento.food_gerent.domain.clientes.ClienteRequestDTO;
import com.gerenciamento.food_gerent.domain.clientes.ClienteResponseDTO;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "dto.nome", target = "nome")
    @Mapping(source = "dto.email", target = "email")
    @Mapping(source = "dto.senha", target = "senha")
    @Mapping(source = "dto.cpf", target = "cpf")
    @Mapping(source = "dto.telefone", target = "telefone")
    @Mapping(target = "cargo", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    Cliente toEntity(ClienteRequestDTO dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "cargo", target = "cargo")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    @Mapping(source = "dataAtualizacao", target = "dataAtualizacao")
    ClienteResponseDTO toResponseDTO(Cliente entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "cargo", target = "cargo")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    @Mapping(source = "dataAtualizacao", target = "dataAtualizacao")
    List<ClienteResponseDTO> toResponseDTOList(List<Cliente> clientes);

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "cargo", target = "cargo")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    @Mapping(source = "dataAtualizacao", target = "dataAtualizacao")
    Cliente jpaToDomain(JpaClienteEntity jpa);
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "cargo", target = "cargo")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    @Mapping(source = "dataAtualizacao", target = "dataAtualizacao")
    List<Cliente> jpaToDomainList(List<JpaClienteEntity> clientes); 

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClienteFromPatchDto(ClientePatchDTO dto, @MappingTarget Cliente cliente);
}

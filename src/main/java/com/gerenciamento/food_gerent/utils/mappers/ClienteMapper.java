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
    @Mapping(source = "dto.telefone", target = "telefone")
    Cliente toEntity(ClienteRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    Cliente dtoToEntity(ClienteRequestDTO dto);
    

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    ClienteRequestDTO toDto(Cliente entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    ClienteResponseDTO toResponseDTO(Cliente entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    List<ClienteResponseDTO> toResponseDTOList(List<Cliente> clientes);

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    Cliente jpaToDomain(JpaClienteEntity jpa);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    JpaClienteEntity domainToJpa(Cliente domain);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    List<Cliente> jpaToDomainList(List<JpaClienteEntity> clientes);

    @Mapping(target = "id", ignore = true) // Mantém o ID existente
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    void updateJpaEntityFromDomain(Cliente cliente, @MappingTarget JpaClienteEntity entity);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClienteFromPatchDto(ClientePatchDTO dto, @MappingTarget Cliente cliente);
}

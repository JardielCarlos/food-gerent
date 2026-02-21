package com.gerenciamento.food_gerent.utils.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaClienteEntity;
import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaFuncionarioEntity;
import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaLojaEntity;
import com.gerenciamento.food_gerent.domain.clientes.Cliente;
import com.gerenciamento.food_gerent.domain.clientes.ClientePatchDTO;
import com.gerenciamento.food_gerent.domain.clientes.ClienteRequestDTO;
import com.gerenciamento.food_gerent.domain.clientes.ClienteResponseDTO;
import com.gerenciamento.food_gerent.domain.funcionarios.Funcionario;
import com.gerenciamento.food_gerent.domain.loja.Loja;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
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
    @Mapping(source = "empresas", target = "empresas")
    ClienteResponseDTO toResponseDTO(Cliente entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "cargo", target = "cargo")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "dataCriacao", target = "dataCriacao", qualifiedByName = "formatLocalDate")
    @Mapping(source = "dataAtualizacao", target = "dataAtualizacao", qualifiedByName = "formatLocalDate")
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
    @Mapping(target = "permissoes", ignore = true)
    @Mapping(target = "empresas", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClienteFromPatchDto(ClientePatchDTO dto, @MappingTarget Cliente cliente);

    static String formatLocalDate(LocalDate date){
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
    }
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "empresa.id", target = "idEmpresa")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "cnpj", target = "cnpj")
    @Mapping(source = "rua", target = "rua")
    @Mapping(source = "bairro", target = "bairro")
    @Mapping(source = "cidade", target = "cidade")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "cep", target = "cep")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    @Mapping(source = "dataAtualizacao", target = "dataAtualizacao")
    @Mapping(target = "funcionarios", ignore = true)
    Loja jpaLojaEntityToLoja(JpaLojaEntity loja);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "senha", target = "senha")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "cargo", target = "cargo")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    @Mapping(source = "dataAtualizacao", target = "dataAtualizacao")
    @Mapping(source = "permissoes", target = "permissoes")
    @Mapping(source = "salario", target = "salario")
    @Mapping(source = "loja", target = "loja")
    Funcionario jpaFuncionarioEntityToFuncionario(JpaFuncionarioEntity funcionario);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "senha", target = "senha")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "cargo", target = "cargo")
    @Mapping(target = "status", defaultValue = "ATIVO")
    @Mapping(source = "permissoes", target = "permissoes")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    @Mapping(source = "dataAtualizacao", target = "dataAtualizacao")
    @Mapping(target = "empresas", ignore = true)
    JpaClienteEntity toDomain(Cliente cliente);
}

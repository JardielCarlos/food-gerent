package com.gerenciamento.food_gerent.domain.clientes;

import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.empresas.EmpresaClienteResponseDTO;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public record ClienteResponseDTO(
  UUID id, 
  String nome, 
  String email,
  String cpf, 
  UsuarioEnumCargos cargo, 
  EnumStatus status, 
  String dataCriacao, 
  String dataAtualizacao, 
  String telefone,
  List<EmpresaClienteResponseDTO> empresas
) {
  
}
  

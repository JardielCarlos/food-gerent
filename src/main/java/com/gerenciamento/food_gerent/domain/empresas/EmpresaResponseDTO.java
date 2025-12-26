package com.gerenciamento.food_gerent.domain.empresas;

import java.util.UUID;

import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumStatus;

public record EmpresaResponseDTO(
  UUID id,
  UUID idCliente,
  String nome,
  String cnpj,
  String email,
  String rua,
  String bairro,
  String cidade,
  String estado,
  String cep,
  String telefone,
  UsuarioEnumStatus status,
  String dataCriacao,
  String dataAtualizacao
) {
  
}

package com.gerenciamento.food_gerent.domain.empresas;

import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public record EmpresaClienteResponseDTO(
  UUID id,
  String nome,
  String cnpj,
  String email,
  EnumStatus status,
  String dataCriacao,
  String dataAtualizacao
) {
  
}

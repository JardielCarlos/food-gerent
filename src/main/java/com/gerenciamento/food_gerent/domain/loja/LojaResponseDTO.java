package com.gerenciamento.food_gerent.domain.loja;

import java.time.LocalDate;
import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public record LojaResponseDTO(
  UUID id, 
  UUID idEmpresa,
  String nome,
  String  cnpj,
  String rua,
  String bairro,
  String cidade,
  String estado,
  String cep,
  String telefone,
  EnumStatus status,
  LocalDate dataCriacao,
  LocalDate dataAtualizacao
) {
  
}

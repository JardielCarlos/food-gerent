package com.gerenciamento.food_gerent.domain.loja;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public record LojaDetailsResponseDTO(
  UUID id, 
  String nome,
  String cnpj,
  String telefone,
  EnumStatus status,
  @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataCriacao,
  @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataAtualizacao
) {

}

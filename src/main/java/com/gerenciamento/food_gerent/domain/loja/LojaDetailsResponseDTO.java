package com.gerenciamento.food_gerent.domain.loja;

import java.time.LocalDate;
import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public record LojaDetailsResponseDTO(
  UUID id, 
  String nome,
  String cnpj,
  String telefone,
  EnumStatus status,
  LocalDate dataCriacao,
  LocalDate dataAtualizacao
) {

}

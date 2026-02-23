package com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumUnidadeMedida;

public record LoteIngredienteResponseDTO(
	UUID id,
	UUID ingredienteId,
  String ingredienteNome,

	@JsonFormat(pattern = "dd/MM/yyyy")
	LocalDate dataFabricacao,

	@JsonFormat(pattern = "dd/MM/yyyy")
	LocalDate dataValidade,

	BigDecimal quantidadeInicial,
	BigDecimal quantidadeDisponivel,

	EnumUnidadeMedida unidadeMedida,
	BigDecimal precoTotal,
	EnumStatus status,

	@JsonFormat(pattern = "dd/MM/yyyy")
	LocalDate dataCriacao,

	@JsonFormat(pattern = "dd/MM/yyyy")
	LocalDate dataAtualizacao
) {

}

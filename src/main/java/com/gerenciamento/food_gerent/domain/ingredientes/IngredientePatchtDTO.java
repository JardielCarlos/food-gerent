package com.gerenciamento.food_gerent.domain.ingredientes;

import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

public record IngredientePatchtDTO(

    @Size(min = 3, message = "O nome do ingrediente deve ter no mínimo 3 caracteres")
    String nome,

    @DecimalMin(value = "0.1", inclusive = true, message = "O custo unitário deve ser maior que zero")
    String custoUnitario,

    String unidadeMedida,

    UUID categoriaId,

    UUID[] tagsIds,

    EnumStatus status
) {
  
}

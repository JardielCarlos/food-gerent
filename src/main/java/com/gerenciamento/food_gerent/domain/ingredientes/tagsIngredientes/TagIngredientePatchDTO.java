package com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TagIngredientePatchDTO(

  @NotBlank(message = "O nome da tag é obrigatório")
  @Size(min = 3, message = "O nome da tag deve ter no mínimo 3 caracteres")
  String nome,

  EnumStatus status
) {
  
}

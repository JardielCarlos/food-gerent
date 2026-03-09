package com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TagIngredienteRequestDTO(

  @NotBlank(message = "O nome da tag é obrigatório")
  @Size(min = 3, message = "O nome da tag deve ter no mínimo 3 caracteres")
  String nome
) {
  
}

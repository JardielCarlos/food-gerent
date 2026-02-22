package com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes;

import java.util.Optional;
import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaIngredientePatchDTO(

  @NotBlank(message = "O nome da categoria é obrigatório")
  @Size(min = 3, message = "O nome da categoria deve ter no mínimo 3 caracteres")
  Optional<String> nome,

  Optional<UUID> parentId,
  
  Optional<EnumStatus> status
) {
}

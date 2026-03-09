package com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaIngredienteRequestDTO(

  @NotBlank(message = "O nome da categoria é obrigatório")
  @Size(min = 3, message = "O nome da categoria deve ter no mínimo 3 caracteres")
  String nome,

  UUID parentId
) {}

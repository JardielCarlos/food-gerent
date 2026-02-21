package com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes;

import java.util.List;
import java.util.UUID;

public record SubCategoriaResponseDTO(
  UUID id,
  String nome,
  UUID parentId,
  Integer nivel,
  List<SubCategoriaResponseDTO> subcategorias
) {}

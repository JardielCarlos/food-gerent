package com.gerenciamento.food_gerent.application.usecases;

import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaIngredientePatchDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaIngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaIngredienteResponseDTO;

public interface CategoriaIngredienteUseCases {
  public List<CategoriaIngredienteResponseDTO> getAllCategorias();

  public CategoriaIngredienteResponseDTO getCategoriaById(UUID id);

  public CategoriaIngredienteResponseDTO createCategoria(CategoriaIngredienteRequestDTO categoriaDTO);

  public CategoriaIngredienteResponseDTO updateCategoria(UUID id, CategoriaIngredientePatchDTO categoriaPatch);

  public void deleteCategoria(UUID id);
}

package com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public record CategoriaIngredienteResponseDTO(
  UUID id,
  String nome,
  UUID parentId,
  List<SubCategoriaResponseDTO> subcategorias,
  Integer nivel,
  EnumStatus status,

  @JsonFormat(pattern = "dd/MM/yyyy") 
  LocalDate dataCriacao,
  
  @JsonFormat(pattern = "dd/MM/yyyy") 
  LocalDate dataAtualizacao
) {}

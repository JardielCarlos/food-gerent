package com.gerenciamento.food_gerent.adapters.outBound.repositories.ingredientes.categorias;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaCategoriaIngredienteEntity;

public interface JpaCategoriaRepository extends JpaRepository<JpaCategoriaIngredienteEntity, UUID>{
  
}

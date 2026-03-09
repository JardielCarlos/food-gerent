package com.gerenciamento.food_gerent.adapters.outBound.repositories.ingredientes.tagsIngrediente;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaTagIngredienteEntity;

public interface JpaTagIngredienteRepository  extends JpaRepository<JpaTagIngredienteEntity, UUID> {
  
}

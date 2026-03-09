package com.gerenciamento.food_gerent.adapters.outBound.repositories.ingredientes.lotesIngrediente;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaLoteIngredienteEntity;

public interface JpaLoteIngredienteRepository extends JpaRepository<JpaLoteIngredienteEntity, UUID> {
  
}

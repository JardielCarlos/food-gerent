package com.gerenciamento.food_gerent.adapters.outBound.repositories.estoques;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaEstoqueEntity;

public interface JpaEstoqueRespository extends JpaRepository<JpaEstoqueEntity, UUID> {
  
}

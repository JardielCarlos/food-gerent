package com.gerenciamento.food_gerent.adapters.outBound.repositories.funcionarios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaFuncionarioEntity;

public interface JpaFuncionarioRepository extends JpaRepository<JpaFuncionarioEntity, UUID>{
  
}

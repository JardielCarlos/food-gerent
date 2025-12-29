package com.gerenciamento.food_gerent.adapters.outBound.repositories.permissoes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaPermissaoEntity;

public interface JpaPermissaoRepository extends JpaRepository<JpaPermissaoEntity, Long> {
  Optional<JpaPermissaoEntity> findByNome(String nome);
} 

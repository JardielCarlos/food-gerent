package com.gerenciamento.food_gerent.adapters.outBound.repositories.usuarios;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaUsuarioEntity;

public interface JpaUsuarioRepository extends JpaRepository<JpaUsuarioEntity, UUID> {

  Optional<JpaUsuarioEntity> findByEmail(String email);

}

package com.gerenciamento.food_gerent.adapters.outBound.repositories.lojas;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaLojaEntity;

public interface JpaLojaRepository extends JpaRepository<JpaLojaEntity, UUID> {

  List<JpaLojaEntity> findByEmpresaId(UUID idEmpresa);
}

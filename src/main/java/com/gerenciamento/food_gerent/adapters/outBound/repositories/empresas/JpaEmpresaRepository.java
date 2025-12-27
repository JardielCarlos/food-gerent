package com.gerenciamento.food_gerent.adapters.outBound.repositories.empresas;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaEmpresaEntity;

public interface JpaEmpresaRepository extends JpaRepository<JpaEmpresaEntity, UUID> {

  Optional<JpaEmpresaEntity> findByCnpjAndCliente_Id(String cnpj, UUID idCliente);
}

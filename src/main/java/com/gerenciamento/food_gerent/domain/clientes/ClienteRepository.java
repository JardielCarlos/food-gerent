package com.gerenciamento.food_gerent.domain.clientes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaClienteEntity;
import com.gerenciamento.food_gerent.domain.empresas.Empresa;

public interface ClienteRepository {
  List<Cliente> findAll();
  
  Optional<Cliente> findById(UUID id);

  Optional<JpaClienteEntity> findByNome(String nome);

  Optional<Empresa> findEmpresaById(UUID idCliente);
  
  Cliente save(Cliente cliente);

  JpaClienteEntity save(JpaClienteEntity clienteEntity);
  
  void deleteById(UUID id);
}

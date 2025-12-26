package com.gerenciamento.food_gerent.domain.clientes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.empresas.Empresa;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaPatchDTO;

public interface ClienteRepository {
  List<Cliente> findAll();
  
  Optional<Cliente> findById(UUID id);

  Optional<Empresa> findEmpresaById(UUID idCliente);
  
  Cliente save(Cliente cliente);
  
  void deleteById(UUID id);
}

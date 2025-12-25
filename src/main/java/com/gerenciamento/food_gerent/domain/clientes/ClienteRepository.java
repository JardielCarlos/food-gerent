package com.gerenciamento.food_gerent.domain.clientes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository {
  List<Cliente> findAll();
  
  Optional<Cliente> findById(UUID id);
  
  Cliente save(Cliente cliente);
  
  void deleteById(UUID id);
}

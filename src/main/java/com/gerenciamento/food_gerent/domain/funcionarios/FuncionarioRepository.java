package com.gerenciamento.food_gerent.domain.funcionarios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FuncionarioRepository {
  List<Funcionario> findAll();
  
  Optional<Funcionario> findById(UUID id);
  
  Funcionario save(Funcionario funcionario);
  
  void deleteById(UUID id);
}

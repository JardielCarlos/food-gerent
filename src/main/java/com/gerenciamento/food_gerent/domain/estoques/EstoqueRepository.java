package com.gerenciamento.food_gerent.domain.estoques;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EstoqueRepository {
  public List<Estoque> findAll();

  public Optional<Estoque> findById(UUID id);

  public Estoque save(Estoque estoque);

  public void deleteById(UUID id);
  
}
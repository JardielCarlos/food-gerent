package com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoteIngredienteRepository {
  
  public List<LoteIngrediente> findAll();

  public Optional<LoteIngrediente> findById(UUID id);

  public LoteIngrediente save(LoteIngrediente loteIngrediente);

  public void deleteById(UUID id);
}

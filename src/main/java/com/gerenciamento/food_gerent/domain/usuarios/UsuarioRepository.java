package com.gerenciamento.food_gerent.domain.usuarios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {
  List<Usuario> findAll();
  
  Optional<Usuario> findById(UUID id);
}

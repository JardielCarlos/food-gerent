package com.gerenciamento.food_gerent.application.usecases;

import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.usuarios.UsuarioResponseDTO;

public interface UsuarioUseCases {
  public List<UsuarioResponseDTO> getAllUsuarios();
  public UsuarioResponseDTO getUsuarioById(UUID id);
}

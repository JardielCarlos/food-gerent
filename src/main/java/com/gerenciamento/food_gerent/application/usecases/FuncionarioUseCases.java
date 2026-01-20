package com.gerenciamento.food_gerent.application.usecases;

import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioPatchDTO;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioRequestDTO;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioResponseDTO;

public interface FuncionarioUseCases {
  public List<FuncionarioResponseDTO> getAllFuncionarios();

  public FuncionarioResponseDTO getFuncionarioById(UUID id);
  
  public FuncionarioResponseDTO createFuncionario(FuncionarioRequestDTO funcionarioDTO);

  public FuncionarioResponseDTO updateFuncionario(UUID id, FuncionarioPatchDTO funcionarioDTO);

  public void deleteFuncionario(UUID id);
}

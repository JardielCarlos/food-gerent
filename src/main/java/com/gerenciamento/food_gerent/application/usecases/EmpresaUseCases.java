package com.gerenciamento.food_gerent.application.usecases;

import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.empresas.EmpresaPatchDTO;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaRequestDTO;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaResponseDTO;

public interface EmpresaUseCases {
  List<EmpresaResponseDTO> getAllEmpresas();
  EmpresaResponseDTO getEmpresaById(UUID id);
  EmpresaResponseDTO createEmpresa(EmpresaRequestDTO data);
  EmpresaResponseDTO updateEmpresa(UUID id, EmpresaPatchDTO data);
  
}

package com.gerenciamento.food_gerent.application.usecases;

import java.util.List;
import java.util.UUID;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.gerenciamento.food_gerent.domain.empresas.EmpresaPatchDTO;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaRequestDTO;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaResponseDTO;

public interface EmpresaUseCases {
  List<EmpresaResponseDTO> getAllEmpresas();
  EmpresaResponseDTO getEmpresaById(UUID id);
  EmpresaResponseDTO createEmpresa(EmpresaRequestDTO data, JwtAuthenticationToken token);
  EmpresaResponseDTO updateEmpresa(UUID id, EmpresaPatchDTO data);
  
}

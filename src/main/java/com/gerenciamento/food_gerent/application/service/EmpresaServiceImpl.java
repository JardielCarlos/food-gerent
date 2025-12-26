package com.gerenciamento.food_gerent.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gerenciamento.food_gerent.application.usecases.EmpresaUseCases;
import com.gerenciamento.food_gerent.domain.empresas.Empresa;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaPatchDTO;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaRepository;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaRequestDTO;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaResponseDTO;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.EmpresaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaUseCases {

  private final EmpresaRepository repository;
  private final EmpresaMapper mapper;
  
  @Override
  public List<EmpresaResponseDTO> getAllEmpresas() {
    List<Empresa> empresas = this.repository.findAll();
    return mapper.toResponseDTOList(empresas);
  }

  @Override
  public EmpresaResponseDTO getEmpresaById(UUID id) {
    Empresa empresa = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada!"));
    return mapper.toResponseDTO(empresa);
  }

  @Override
  public EmpresaResponseDTO createEmpresa(EmpresaRequestDTO data) {
    Empresa empresa = mapper.toEntity(data);
    Empresa savedEmpresa = repository.save(empresa);
    return mapper.toResponseDTO(savedEmpresa);
  }

  @Override
  public EmpresaResponseDTO updateEmpresa(UUID id, EmpresaPatchDTO data) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateEmpresa'");
  }
}

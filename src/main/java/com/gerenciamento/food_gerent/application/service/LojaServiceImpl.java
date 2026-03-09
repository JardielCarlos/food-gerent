package com.gerenciamento.food_gerent.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gerenciamento.food_gerent.application.usecases.LojaUseCases;
import com.gerenciamento.food_gerent.domain.empresas.Empresa;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaRepository;
import com.gerenciamento.food_gerent.domain.loja.Loja;
import com.gerenciamento.food_gerent.domain.loja.LojaPatchDTO;
import com.gerenciamento.food_gerent.domain.loja.LojaRepository;
import com.gerenciamento.food_gerent.domain.loja.LojaRequestDTO;
import com.gerenciamento.food_gerent.domain.loja.LojaResponseDTO;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.LojaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LojaServiceImpl implements LojaUseCases {

  private final LojaRepository lojaRepository;
  private final EmpresaRepository empresaRepository;
  private final LojaMapper lojaMapper;

  @Override
  public List<LojaResponseDTO> getAllLojas() {
    List<Loja> lojas = this.lojaRepository.findAll();
    return lojaMapper.toResponseDTOList(lojas);
  }

  @Override
  public LojaResponseDTO getLojaById(UUID id) {
    Loja loja = this.lojaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Loja não encontrada com o id: " + id));
    return lojaMapper.toResponseDTO(loja);
  }

  @Override
  public LojaResponseDTO createLoja(LojaRequestDTO data) {
    Empresa empresa = 
      this.empresaRepository.findById(UUID.fromString(data.idEmpresa()))
        .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com o id: " + data.idEmpresa())); 
   
    Loja newLoja = lojaMapper.requestToJpa(data, empresa);

    Loja savedLoja = this.lojaRepository.save(newLoja);
    return lojaMapper.toResponseDTO(savedLoja);
  }

  @Override
  public LojaResponseDTO updateLoja(UUID idLoja, UUID idEmpresa, LojaPatchDTO data) {
    Empresa empresa = this.empresaRepository.findById(idEmpresa)
      .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com o id: " + idEmpresa));

    Loja lojaExistente = empresa.getLojas()
      .stream()
      .filter(l -> l.getId().equals(idLoja))
      .findFirst()
      .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada com o id: " + idLoja + " para a empresa de id: " + idEmpresa));


    Loja lojaAtualizada = lojaMapper.updateLojaEntityFromPatchDto(data, lojaExistente);
    lojaAtualizada.setIdEmpresa(empresa.getId());

    this.lojaRepository.save(lojaAtualizada);

    return lojaMapper.toResponseDTO(lojaAtualizada);
  }

  @Override
  public void deleteLoja(UUID idLoja, UUID idEmpresa) {
    Empresa empresa = this.empresaRepository.findById(idEmpresa)
      .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com o id: " + idEmpresa));

    Loja lojaExistente = empresa.getLojas()
      .stream()
      .filter(l -> l.getId().equals(idLoja))
      .findFirst()
      .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada com o id: " + idLoja + " para a empresa de id: " + idEmpresa));

    empresa.getLojas().remove(lojaExistente);
    this.empresaRepository.save(empresa);
  }
  
}

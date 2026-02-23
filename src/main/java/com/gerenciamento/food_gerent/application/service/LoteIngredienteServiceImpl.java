package com.gerenciamento.food_gerent.application.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gerenciamento.food_gerent.application.usecases.LoteIngredienteUseCases;
import com.gerenciamento.food_gerent.domain.ingredientes.IngredienteRepository;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngrediente;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngredientePatchDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngredienteRepository;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngredienteResponseDTO;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.LoteIngredienteMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoteIngredienteServiceImpl implements LoteIngredienteUseCases{
  
  private final LoteIngredienteRepository loteIngredienteRepository;
  private final IngredienteRepository ingredienteRepository;
  private final LoteIngredienteMapper loteIngredienteMapper;

  @Override
  public List<LoteIngredienteResponseDTO> findAllLotesIngredientes() {
    List<LoteIngrediente> loteIngredientes = this.loteIngredienteRepository.findAll();
    return loteIngredientes.stream()
      .map(lote -> {
        String ingredienteNome = this.ingredienteRepository.findById(lote.getIngredienteId())
          .map(ingrediente -> ingrediente.getNome())
          .orElse(null);
        return loteIngredienteMapper.toResponseDTO(lote, ingredienteNome);
      })
      .collect(Collectors.toList());
  }

  @Override
  public LoteIngredienteResponseDTO findLoteIngredienteById(UUID id) {
    LoteIngrediente loteIngrediente = this.loteIngredienteRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Lote não encontrado"));
    String ingredienteNome = this.ingredienteRepository.findById(loteIngrediente.getIngredienteId())
      .map(ingrediente -> ingrediente.getNome())
      .orElse(null);
    return loteIngredienteMapper.toResponseDTO(loteIngrediente, ingredienteNome);
  }

  @Override
  public LoteIngredienteResponseDTO createLoteIngrediente(LoteIngredienteRequestDTO data) {
    this.ingredienteRepository.findById(data.ingredienteId())
      .orElseThrow(() -> new EntityNotFoundException("Ingrediente de id: " + data.ingredienteId() + " não encontrado"));
    LoteIngrediente loteIngrediente = loteIngredienteMapper.toEntity(data);
    LoteIngrediente savedLoteIngrediente = this.loteIngredienteRepository.save(loteIngrediente);
    String ingredienteNome = this.ingredienteRepository.findById(savedLoteIngrediente.getIngredienteId())
      .map(ingrediente -> ingrediente.getNome())
      .orElse(null);
    return loteIngredienteMapper.toResponseDTO(savedLoteIngrediente, ingredienteNome);

  }

  @Override
  public LoteIngredienteResponseDTO updateLoteIngrediente(UUID id, LoteIngredientePatchDTO data) {
    LoteIngrediente loteIngrediente = this.loteIngredienteRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Lote de id: " + id + " não encontrado"));

    loteIngredienteMapper.updateLoteIngredienteFromPatchDTO(data, loteIngrediente);

    LoteIngrediente updatedLoteIngrediente = this.loteIngredienteRepository.save(loteIngrediente);
    String ingredienteNome = this.ingredienteRepository.findById(updatedLoteIngrediente.getIngredienteId())
      .map(ingrediente -> ingrediente.getNome())
      .orElse(null);
    return loteIngredienteMapper.toResponseDTO(updatedLoteIngrediente, ingredienteNome);
  }

  @Override
  public void deleteLoteIngrediente(UUID id) {
    this.loteIngredienteRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Lote de id: " + id + " não encontrado"));
    this.loteIngredienteRepository.deleteById(id);
  }
}

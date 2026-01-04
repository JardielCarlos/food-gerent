package com.gerenciamento.food_gerent.application.usecases;

import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.loja.LojaPatchDTO;
import com.gerenciamento.food_gerent.domain.loja.LojaRequestDTO;
import com.gerenciamento.food_gerent.domain.loja.LojaResponseDTO;

public interface LojaUseCases {

  List<LojaResponseDTO> getAllLojas();
  LojaResponseDTO getLojaById(UUID id);
  LojaResponseDTO createLoja(LojaRequestDTO data);
  LojaResponseDTO updateLoja(UUID id, UUID idEmpresa, LojaPatchDTO data);
  void deleteLoja(UUID idLoja, UUID idEmpresa);

}


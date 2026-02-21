package com.gerenciamento.food_gerent.adapters.inBound.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamento.food_gerent.application.usecases.LojaUseCases;
import com.gerenciamento.food_gerent.domain.loja.LojaPatchDTO;
import com.gerenciamento.food_gerent.domain.loja.LojaRequestDTO;
import com.gerenciamento.food_gerent.domain.loja.LojaResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lojas")
@RequiredArgsConstructor
public class LojaController {
  
  private final LojaUseCases service;

  @GetMapping
  public ResponseEntity<List<LojaResponseDTO>> getLojas(){
    List<LojaResponseDTO> allLojas = this.service.getAllLojas();
    return ResponseEntity.ok(allLojas);
  }

  @GetMapping("/{id}")
  public ResponseEntity<LojaResponseDTO> getLojaById(@PathVariable UUID id){
    LojaResponseDTO loja = this.service.getLojaById(id);
    return ResponseEntity.ok(loja);
  }

  @PostMapping
  public ResponseEntity<LojaResponseDTO> createLoja(@RequestBody @Valid LojaRequestDTO data){
    LojaResponseDTO createdLoja = this.service.createLoja(data);
    return ResponseEntity.ok(createdLoja);
  }

  @PatchMapping("/{idLoja}/empresa/{idEmpresa}")
  public ResponseEntity<LojaResponseDTO> updateLoja(
    @PathVariable UUID idLoja, 
    @PathVariable UUID idEmpresa, 
    @RequestBody @Valid LojaPatchDTO data
  ){
    LojaResponseDTO updatedLoja = this.service.updateLoja(idLoja, idEmpresa, data);
    return ResponseEntity.ok(updatedLoja);
  }

  @DeleteMapping("/{idLoja}/empresa/{idEmpresa}")
  public ResponseEntity<Void> deleteLoja(
    @PathVariable UUID idLoja,
    @PathVariable UUID idEmpresa
  ){
    this.service.deleteLoja(idLoja, idEmpresa);
    return ResponseEntity.noContent().build();
  }

}

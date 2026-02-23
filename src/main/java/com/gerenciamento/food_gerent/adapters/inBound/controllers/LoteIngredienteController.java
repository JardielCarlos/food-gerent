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

import com.gerenciamento.food_gerent.application.usecases.LoteIngredienteUseCases;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngredientePatchDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngredienteResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loteIngrediente")
public class LoteIngredienteController {
  
  private final LoteIngredienteUseCases loteIngredienteService;

  @GetMapping
  public ResponseEntity<List<LoteIngredienteResponseDTO>> findAllLoteIngredientes(){
    List<LoteIngredienteResponseDTO> loteIngredientes = loteIngredienteService.findAllLotesIngredientes();
    return ResponseEntity.ok(loteIngredientes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<LoteIngredienteResponseDTO> findLoteIngredienteById(@PathVariable UUID id){
    LoteIngredienteResponseDTO loteIngrediente = loteIngredienteService.findLoteIngredienteById(id);
    return ResponseEntity.ok(loteIngrediente);
  }

  @PostMapping
  public ResponseEntity<LoteIngredienteResponseDTO> createLoteIngrediente(@RequestBody @Valid LoteIngredienteRequestDTO data){
    LoteIngredienteResponseDTO createdLoteIngrediente = loteIngredienteService.createLoteIngrediente(data);
    return ResponseEntity.ok(createdLoteIngrediente);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<LoteIngredienteResponseDTO> updateLoteIngrediente(
    @PathVariable UUID id, 
    @RequestBody @Valid LoteIngredientePatchDTO data
  ){
    LoteIngredienteResponseDTO updatedLoteIngrediente = loteIngredienteService.updateLoteIngrediente(id, data);
    return ResponseEntity.ok(updatedLoteIngrediente);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteLoteIngrediente(@PathVariable UUID id){
    loteIngredienteService.deleteLoteIngrediente(id);
    return ResponseEntity.noContent().build();
  }
}

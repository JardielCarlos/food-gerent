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

import com.gerenciamento.food_gerent.application.usecases.IngredienteUseCases;
import com.gerenciamento.food_gerent.domain.ingredientes.IngredientePatchtDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.IngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.IngredienteResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredientes")
public class IngredienteController {
  
  private final IngredienteUseCases ingredienteService;

  @GetMapping
  public ResponseEntity<List<IngredienteResponseDTO>> getAllIngredientes(){
    List<IngredienteResponseDTO> allIngredientes = this.ingredienteService.getAllIngredientes();
    return ResponseEntity.ok(allIngredientes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<IngredienteResponseDTO> getIngredienteById(@PathVariable UUID id){
    IngredienteResponseDTO ingrediente = this.ingredienteService.getIngredienteById(id);
    return ResponseEntity.ok(ingrediente);
  }

  @PostMapping
  public ResponseEntity<IngredienteResponseDTO> createIngrediente(@RequestBody @Valid IngredienteRequestDTO ingredienteRequest){
    IngredienteResponseDTO createIngrediente = this.ingredienteService.createIngrediente(ingredienteRequest);
    return ResponseEntity.ok(createIngrediente);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<IngredienteResponseDTO> updateIngrediente(
    @PathVariable UUID id,
    @RequestBody @Valid IngredientePatchtDTO ingredienteRequest
  ){
    IngredienteResponseDTO updatedIngrediente = this.ingredienteService.updateIngrediente(id, ingredienteRequest);
    return ResponseEntity.ok(updatedIngrediente);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteIngrediente(@PathVariable UUID id){
    this.ingredienteService.deleteIngrediente(id);
    return ResponseEntity.noContent().build();
  }
  
}

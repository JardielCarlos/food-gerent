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

import com.gerenciamento.food_gerent.application.usecases.EstoqueUseCases;
import com.gerenciamento.food_gerent.domain.estoques.EstoquePatchDTO;
import com.gerenciamento.food_gerent.domain.estoques.EstoqueRequestDTO;
import com.gerenciamento.food_gerent.domain.estoques.EstoqueResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("estoques")
public class EstoqueController {
  
  private final EstoqueUseCases estoqueService;

  @GetMapping
  public ResponseEntity<List<EstoqueResponseDTO>> getAllEstoques() {
    List<EstoqueResponseDTO> estoques = this.estoqueService.findAll();
    return ResponseEntity.ok(estoques);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EstoqueResponseDTO> getEstoqueById(@PathVariable UUID id) {
    EstoqueResponseDTO estoque = this.estoqueService.findById(id);
    return ResponseEntity.ok(estoque);
  }

  @PostMapping
  public ResponseEntity<EstoqueResponseDTO> createEstoque(@RequestBody @Valid EstoqueRequestDTO data){
    EstoqueResponseDTO createdEstoque = this.estoqueService.save(data);
    return ResponseEntity.ok(createdEstoque);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<EstoqueResponseDTO> updateEstoque(
    @PathVariable UUID id, 
    @RequestBody @Valid EstoquePatchDTO data){
      EstoqueResponseDTO updateEstoque = this.estoqueService.update(id, data);
      return ResponseEntity.ok(updateEstoque);
  }
 
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEstoque(@PathVariable UUID id){
    this.estoqueService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}

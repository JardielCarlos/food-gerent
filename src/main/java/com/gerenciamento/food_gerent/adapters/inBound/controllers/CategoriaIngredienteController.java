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

import com.gerenciamento.food_gerent.application.usecases.CategoriaIngredienteUseCases;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaIngredientePatchDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaIngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaIngredienteResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
public class CategoriaIngredienteController {
  
  private final CategoriaIngredienteUseCases categoriaService;

  @GetMapping
  public ResponseEntity<List<CategoriaIngredienteResponseDTO>> getAllCategorias() {
    List<CategoriaIngredienteResponseDTO> allCategorias = this.categoriaService.getAllCategorias();
    return ResponseEntity.ok(allCategorias);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoriaIngredienteResponseDTO> categoriaById(@PathVariable UUID id) {
    CategoriaIngredienteResponseDTO categoria = this.categoriaService.getCategoriaById(id);
    return ResponseEntity.ok(categoria);
  }

  @PostMapping
  public ResponseEntity<CategoriaIngredienteResponseDTO> createCategoria(@Valid @RequestBody CategoriaIngredienteRequestDTO categoria){
    CategoriaIngredienteResponseDTO createdFuncionario = this.categoriaService.createCategoria(categoria);
    return ResponseEntity.ok(createdFuncionario);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CategoriaIngredienteResponseDTO> updateCategoria(
    @PathVariable UUID id,
    @Valid @RequestBody CategoriaIngredientePatchDTO categoriaPatch
  ){
    CategoriaIngredienteResponseDTO updateCategoria = this.categoriaService.updateCategoria(id, categoriaPatch);
    return ResponseEntity.ok(updateCategoria);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategoria(@PathVariable UUID id){
    this.categoriaService.deleteCategoria(id);
    return ResponseEntity.noContent().build();
  }
}

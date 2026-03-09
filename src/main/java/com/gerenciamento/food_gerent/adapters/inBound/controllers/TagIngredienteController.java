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

import com.gerenciamento.food_gerent.application.usecases.TagIngredienteUseCases;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngredientePatchDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngredienteResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagIngredienteController {
  
  private final TagIngredienteUseCases tagIngredienteService;

  @GetMapping
  public ResponseEntity<List<TagIngredienteResponseDTO>> getAllTags() {
    List<TagIngredienteResponseDTO> allTags = this.tagIngredienteService.getAllTags();
    return ResponseEntity.ok(allTags);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TagIngredienteResponseDTO> getTagById(@PathVariable UUID id){
    TagIngredienteResponseDTO tag = this.tagIngredienteService.getTagById(id);
    return ResponseEntity.ok(tag);
  }

  @PostMapping
  public ResponseEntity<TagIngredienteResponseDTO> createTag(@RequestBody @Valid TagIngredienteRequestDTO data){
    TagIngredienteResponseDTO createdTag = this.tagIngredienteService.createTag(data);
    return ResponseEntity.ok(createdTag);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<TagIngredienteResponseDTO> updateTag(
    @PathVariable UUID id,
    @RequestBody @Valid TagIngredientePatchDTO data
  ){
    TagIngredienteResponseDTO updatedTag = this.tagIngredienteService.updateTag(id, data);
    return ResponseEntity.ok(updatedTag);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
    this.tagIngredienteService.deleteTag(id);
    return ResponseEntity.noContent().build();
  }
}

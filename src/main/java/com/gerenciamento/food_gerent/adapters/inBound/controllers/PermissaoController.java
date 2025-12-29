package com.gerenciamento.food_gerent.adapters.inBound.controllers;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamento.food_gerent.application.service.PermissaoServiceImpl;
import com.gerenciamento.food_gerent.domain.permissoes.PermissaoRequestDTO;
import com.gerenciamento.food_gerent.domain.permissoes.PermissaoResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/permissoes")
@RequiredArgsConstructor
public class PermissaoController {
  
  private final PermissaoServiceImpl service;
  
  @GetMapping
  public ResponseEntity<Set<PermissaoResponseDTO>> getAllPermissoes() {
    Set<PermissaoResponseDTO> allPermissoes = this.service.getAllPermissoes();
    return ResponseEntity.ok(allPermissoes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PermissaoResponseDTO> getPermissaoById(@PathVariable Long id) {
    PermissaoResponseDTO permissao = this.service.getPermissaoById(id);
    return ResponseEntity.ok(permissao);
  }

  @PostMapping
  public ResponseEntity<PermissaoResponseDTO> createPermissao(@RequestBody PermissaoRequestDTO data) {
    PermissaoResponseDTO createdPermissao = this.service.createPermissao(data);
    return ResponseEntity.ok(createdPermissao);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PermissaoResponseDTO> updatePermissao(
    @PathVariable Long id, 
    @RequestBody PermissaoRequestDTO data
  ) {
    PermissaoResponseDTO updatedPermissao = this.service.updatePermissao(id, data);
    return ResponseEntity.ok(updatedPermissao);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePermissao(@PathVariable Long id) {
    this.service.deletePermissao(id);
    return ResponseEntity.noContent().build();
  }
}

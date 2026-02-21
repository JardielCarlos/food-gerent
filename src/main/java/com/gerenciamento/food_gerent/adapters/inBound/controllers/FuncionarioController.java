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

import com.gerenciamento.food_gerent.application.service.FuncionarioServiceImpl;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioPatchDTO;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioRequestDTO;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funcionarios")
public class FuncionarioController {
  
  private final FuncionarioServiceImpl funcionarioService;

  @GetMapping
  public ResponseEntity<List<FuncionarioResponseDTO>> getAllFuncionarios(){
    List<FuncionarioResponseDTO> allFuncionarios = this.funcionarioService.getAllFuncionarios();
    return ResponseEntity.ok(allFuncionarios);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FuncionarioResponseDTO> getFuncionarioById(@PathVariable UUID id){
    FuncionarioResponseDTO funcionario = this.funcionarioService.getFuncionarioById(id);
    return ResponseEntity.ok(funcionario);
  }

  @PostMapping
  public ResponseEntity<FuncionarioResponseDTO> createFuncionario(@Valid @RequestBody FuncionarioRequestDTO funcionarioDTO){
    FuncionarioResponseDTO createdFuncionario = this.funcionarioService.createFuncionario(funcionarioDTO);
    return ResponseEntity.ok(createdFuncionario);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<FuncionarioResponseDTO> updateFuncionario(
    @PathVariable UUID id, 
    @Valid @RequestBody FuncionarioPatchDTO funcionarioDTO
  ){
    FuncionarioResponseDTO updatedFuncionario = this.funcionarioService.updateFuncionario(id, funcionarioDTO);
    return ResponseEntity.ok(updatedFuncionario);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFuncionario(@PathVariable UUID id){
    this.funcionarioService.deleteFuncionario(id);
    return ResponseEntity.noContent().build();
  }
}

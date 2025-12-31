package com.gerenciamento.food_gerent.adapters.inBound.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamento.food_gerent.application.service.EmpresaServiceImpl;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaRequestDTO;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/empresas")
@RequiredArgsConstructor
public class EmpresaController {
  
  private final EmpresaServiceImpl service;

  @GetMapping
  public ResponseEntity<List<EmpresaResponseDTO>> getEmpresas(){
    List<EmpresaResponseDTO> allEmpresas = this.service.getAllEmpresas();
    return ResponseEntity.ok(allEmpresas);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmpresaResponseDTO> getEmpresaById(java.util.UUID id){
    EmpresaResponseDTO empresa = this.service.getEmpresaById(id);
    return ResponseEntity.ok(empresa);
  }

  @PostMapping
  public ResponseEntity<EmpresaResponseDTO> createEmpresa(@Valid @RequestBody EmpresaRequestDTO data, JwtAuthenticationToken token){
    EmpresaResponseDTO createdEmpresa = this.service.createEmpresa(data, token);
    return ResponseEntity.ok(createdEmpresa);
  }
}

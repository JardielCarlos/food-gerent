package com.gerenciamento.food_gerent.adapters.inBound.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamento.food_gerent.application.service.UsuarioServiceImpl;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

  private final UsuarioServiceImpl usuarioService;

  @GetMapping
  public ResponseEntity<List<UsuarioResponseDTO>> getUsuarios(){
    List<UsuarioResponseDTO> allUsuarios = this.usuarioService.getAllUsuarios();
    return ResponseEntity.ok(allUsuarios);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UsuarioResponseDTO> getUsuarioById(UUID id){
    UsuarioResponseDTO usuario = this.usuarioService.getUsuarioById(id);
    return ResponseEntity.ok(usuario);
  }
}
  
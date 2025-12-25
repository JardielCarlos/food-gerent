package com.gerenciamento.food_gerent.application.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamento.food_gerent.application.usecases.UsuarioUseCases;
import com.gerenciamento.food_gerent.domain.usuarios.Usuario;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioRepository;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioResponseDTO;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.UsuarioMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioUseCases {
  
  private final UsuarioRepository repository;

  @Autowired
  private UsuarioMapper mapper;
  
  @Override
  public List<UsuarioResponseDTO> getAllUsuarios() {
    List<Usuario> usuarios = this.repository.findAll();

    return mapper.toResponseDTOList(usuarios);
  }

  @Override
  public UsuarioResponseDTO getUsuarioById(UUID id) {
    Usuario usuario = this.repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));
      
    return mapper.toResponseDTO(usuario);
  }
  
}

package com.gerenciamento.food_gerent.adapters.outBound.repositories.usuarios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaUsuarioEntity;
import com.gerenciamento.food_gerent.domain.usuarios.Usuario;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioRepository;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.UsuarioMapper;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

  private final JpaUsuarioRepository jpaUsuarioRepository;
  private final UsuarioMapper mapper;

  public UsuarioRepositoryImpl(JpaUsuarioRepository jpaUsuarioRepository, UsuarioMapper mapper) {
    this.jpaUsuarioRepository = jpaUsuarioRepository;
    this.mapper = mapper;
  }

  @Override
  public List<Usuario> findAll() {
    List<JpaUsuarioEntity> usuarioEntities = this.jpaUsuarioRepository.findAll();
  
    return mapper.jpaToDomainList(usuarioEntities);
  }

  @Override
  public Optional<Usuario> findById(UUID id) {
    Usuario usuario = this.jpaUsuarioRepository.findById(id)
      .map(mapper::jpaToDomain)
      .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
      return Optional.of(usuario);
  }

  @Override
  public Optional<Usuario> findByEmail(String email) {
    Usuario usuario = this.jpaUsuarioRepository.findByEmail(email)
      .map(mapper::jpaToDomain)
      .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
      return Optional.of(usuario);
  }
  
}

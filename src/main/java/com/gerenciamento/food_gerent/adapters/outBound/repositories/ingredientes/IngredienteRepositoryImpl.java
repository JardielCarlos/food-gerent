package com.gerenciamento.food_gerent.adapters.outBound.repositories.ingredientes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaIngredienteEntity;
import com.gerenciamento.food_gerent.domain.ingredientes.Ingrediente;
import com.gerenciamento.food_gerent.domain.ingredientes.IngredienteRepository;
import com.gerenciamento.food_gerent.utils.mappers.IngredienteMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class IngredienteRepositoryImpl implements IngredienteRepository {
  
  private final JpaIngredienteRepository jpaIngredienteRepository;
  private final IngredienteMapper ingredienteMapper;

  @Override
  public List<Ingrediente> findAll() {
    List<JpaIngredienteEntity> ingredienteEntity = this.jpaIngredienteRepository.findAll();
    return ingredienteMapper.jpaToDomainList(ingredienteEntity);
  }

  @Override
  public Optional<Ingrediente> findById(UUID id) {
    Optional<JpaIngredienteEntity> ingredienteEntity = this.jpaIngredienteRepository.findById(id);
    return ingredienteEntity.map(ingredienteMapper::jpaToDomain);
  }
  
  @Override
  public Ingrediente save(Ingrediente ingrediente) {
    JpaIngredienteEntity ingredienteEntity = ingredienteMapper.domainToJpa(ingrediente);
    JpaIngredienteEntity savedEntity = this.jpaIngredienteRepository.save(ingredienteEntity);
    return ingredienteMapper.jpaToDomain(savedEntity);
  }

  @Override
  public void delete(Ingrediente entity) {
    this.jpaIngredienteRepository.delete(ingredienteMapper.domainToJpa(entity));
  }
}

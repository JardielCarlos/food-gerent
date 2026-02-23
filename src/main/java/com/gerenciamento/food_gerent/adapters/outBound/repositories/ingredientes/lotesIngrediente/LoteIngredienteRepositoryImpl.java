package com.gerenciamento.food_gerent.adapters.outBound.repositories.ingredientes.lotesIngrediente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaLoteIngredienteEntity;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngrediente;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngredienteRepository;
import com.gerenciamento.food_gerent.utils.mappers.LoteIngredienteMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LoteIngredienteRepositoryImpl implements LoteIngredienteRepository{

  private final JpaLoteIngredienteRepository jpaLoteIngredienteRepository;
  private final LoteIngredienteMapper loteIngredienteMapper;

  @Override
  public List<LoteIngrediente> findAll() {
    List<JpaLoteIngredienteEntity> loteIngredienteEntity = this.jpaLoteIngredienteRepository.findAll();
    return loteIngredienteMapper.jpaToDomainList(loteIngredienteEntity);
  }

  @Override
  public Optional<LoteIngrediente> findById(UUID id) {
    Optional<JpaLoteIngredienteEntity> loteIngredienteEntity = this.jpaLoteIngredienteRepository.findById(id);
    return loteIngredienteEntity.map(loteIngredienteMapper::jpaToDomain);
  }

  @Override
  public LoteIngrediente save(LoteIngrediente loteIngrediente) {
    JpaLoteIngredienteEntity loteIngredienteEntity = loteIngredienteMapper.domainToJpa(loteIngrediente);
    JpaLoteIngredienteEntity savedEntity = this.jpaLoteIngredienteRepository.save(loteIngredienteEntity);
    return loteIngredienteMapper.jpaToDomain(savedEntity);
  }

  @Override
  public void deleteById(UUID id) {
    this.jpaLoteIngredienteRepository.deleteById(id);
  }
}

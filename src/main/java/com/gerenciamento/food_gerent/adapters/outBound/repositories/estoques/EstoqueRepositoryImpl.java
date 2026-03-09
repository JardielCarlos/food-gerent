package com.gerenciamento.food_gerent.adapters.outBound.repositories.estoques;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaEstoqueEntity;
import com.gerenciamento.food_gerent.domain.estoques.Estoque;
import com.gerenciamento.food_gerent.domain.estoques.EstoqueRepository;
import com.gerenciamento.food_gerent.utils.mappers.EstoqueMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EstoqueRepositoryImpl implements EstoqueRepository {

  private final JpaEstoqueRespository jpaEstoqueRespository;
  private final EstoqueMapper estoqueMapper;

  @Override
  public List<Estoque> findAll() {
    List<JpaEstoqueEntity> estoqueEntities = this.jpaEstoqueRespository.findAll();
    return estoqueMapper.jpaToDomainList(estoqueEntities);
  }

  @Override
  public Optional<Estoque> findById(UUID id) {
    Optional<JpaEstoqueEntity> estoqueEntity = this.jpaEstoqueRespository.findById(id);
    return estoqueEntity.map(estoqueMapper::jpaToDomain);
  }

  @Override
  public Estoque save(Estoque estoque) {
    JpaEstoqueEntity estoqueEntity = estoqueMapper.domainToJpa(estoque);
    JpaEstoqueEntity savedEntity = this.jpaEstoqueRespository.save(estoqueEntity);
    return estoqueMapper.jpaToDomain(savedEntity);
  }

  @Override
  public void deleteById(UUID id) {
    this.jpaEstoqueRespository.deleteById(id);
  }
  
}

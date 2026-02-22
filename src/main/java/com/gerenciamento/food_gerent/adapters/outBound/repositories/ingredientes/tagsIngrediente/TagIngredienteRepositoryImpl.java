package com.gerenciamento.food_gerent.adapters.outBound.repositories.ingredientes.tagsIngrediente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaTagIngredienteEntity;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngrediente;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngredienteRepository;
import com.gerenciamento.food_gerent.utils.mappers.TagIngredienteMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TagIngredienteRepositoryImpl implements TagIngredienteRepository {

  private final JpaTagIngredienteRepository jpaTagIngredienteRepository;
  private final TagIngredienteMapper tagIngredienteMapper;
  @Override
  public List<TagIngrediente> findAll() {
    List<JpaTagIngredienteEntity> tagsIngredienteEntity = this.jpaTagIngredienteRepository.findAll();
    return tagIngredienteMapper.jpaToDomainList(tagsIngredienteEntity);
  }

  @Override
  public Optional<TagIngrediente> findById(UUID id) {
    Optional<JpaTagIngredienteEntity> tagIngredienteEntity = this.jpaTagIngredienteRepository.findById(id);
    return tagIngredienteEntity.map(tagIngredienteMapper::jpaToDomain); 
  }

  @Override
  public TagIngrediente save(TagIngrediente tag) {
    JpaTagIngredienteEntity tagEntity = tagIngredienteMapper.domainToJpa(tag);
    JpaTagIngredienteEntity saved = jpaTagIngredienteRepository.save(tagEntity);
    return tagIngredienteMapper.jpaToDomain(saved);
  }

  @Override
  public void deleteById(UUID id) {
    this.jpaTagIngredienteRepository.deleteById(id);
  }

  @Override
  public List<TagIngrediente> findAllById(List<UUID> tagsIds) {
    List<JpaTagIngredienteEntity> tagsEntity = this.jpaTagIngredienteRepository.findAllById(tagsIds);
    return tagIngredienteMapper.jpaToDomainList(tagsEntity);
  }
}

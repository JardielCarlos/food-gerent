package com.gerenciamento.food_gerent.adapters.outBound.repositories.ingredientes.categorias;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaCategoriaIngredienteEntity;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaIngrediente;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaRepository;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.CategoriaMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoriaRepositoryImpl implements CategoriaRepository{
  
  private final CategoriaMapper mapper;
  private final JpaCategoriaRepository jpaCategoriaRepository;

  @Override
  public List<CategoriaIngrediente> findAll() {
    List<JpaCategoriaIngredienteEntity> categoriaEntities = this.jpaCategoriaRepository.findAll();
    return mapper.jpaToDomainList(categoriaEntities);
  }

  @Override
  public Optional<CategoriaIngrediente> findById(UUID id) {
    Optional<JpaCategoriaIngredienteEntity> categoriaEntity = this.jpaCategoriaRepository.findById(id);
    return categoriaEntity.map(mapper::jpaToDomain);
  }

  @Override
  public CategoriaIngrediente save(CategoriaIngrediente categoria) {
    JpaCategoriaIngredienteEntity categoriaEntity = mapper.domainToJpa(categoria);

    if (categoria.getParentId() != null) {
      JpaCategoriaIngredienteEntity parentRef = jpaCategoriaRepository.getReferenceById(categoria.getParentId());

      categoriaEntity.setParent(parentRef);
    } else {
      categoriaEntity.setParent(null);
    }

    JpaCategoriaIngredienteEntity saved = jpaCategoriaRepository.save(categoriaEntity);
    return mapper.jpaToDomain(saved);
  }

  @Override
  public void deleteById(UUID id) {
    JpaCategoriaIngredienteEntity entity = this.jpaCategoriaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada: " + id));
    this.jpaCategoriaRepository.delete(entity);
  }
}

package com.gerenciamento.food_gerent.adapters.outBound.repositories.permissoes;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaPermissaoEntity;
import com.gerenciamento.food_gerent.domain.permissoes.Permissao;
import com.gerenciamento.food_gerent.domain.permissoes.PermissaoRepository;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.PermissaoMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PermissaoRepositoryImpl implements PermissaoRepository {

  private final PermissaoMapper mapper;
  private final JpaPermissaoRepository jpaPermissaoRepository;
  
  @Override
  public Set<Permissao> findAll() {
    List<JpaPermissaoEntity> permissoes = this.jpaPermissaoRepository.findAll();
    
    return mapper.jpaToDomainSet(permissoes);
  }
  
  @Override
  public Optional<Permissao> findById(Long permissaoId) {
    Optional<JpaPermissaoEntity> permissao = this.jpaPermissaoRepository.findById(permissaoId);  

    return permissao.map(mapper::jpaToDomain);
  }

  @Override
  public Permissao save(Permissao permissao) {
    JpaPermissaoEntity permissaoEntity = mapper.toDomain(permissao);
    this.jpaPermissaoRepository.save(permissaoEntity);

    return mapper.jpaToDomain(permissaoEntity);
  }

  @Override
  public void deleteById(Long permissaoId) {
    this.jpaPermissaoRepository.deleteById(permissaoId);
  }

  @Override
  public Permissao update(Long permissaoId, Permissao permissao) {
    JpaPermissaoEntity permissaoEntity = this.jpaPermissaoRepository.findById(permissaoId)
      .orElseThrow(() -> 
        new EntityNotFoundException("Permissão não encontrada com id: " + permissaoId)
      );
    
    permissaoEntity.setNome(permissao.getNome());

    this.jpaPermissaoRepository.save(permissaoEntity);

    return mapper.jpaToDomain(permissaoEntity);
  }

  @Override
  public Optional<JpaPermissaoEntity> findByNome(String nome) {
     Optional<JpaPermissaoEntity> permissao = this.jpaPermissaoRepository.findByNome(nome);  

    return permissao;
  }

  @Override
  public JpaPermissaoEntity save(JpaPermissaoEntity permissaoEntity) {
    return this.jpaPermissaoRepository.save(permissaoEntity);
  }
}

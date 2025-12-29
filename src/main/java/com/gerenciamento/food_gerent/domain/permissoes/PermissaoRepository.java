package com.gerenciamento.food_gerent.domain.permissoes;

import java.util.Optional;
import java.util.Set;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaPermissaoEntity;

public interface PermissaoRepository {
  Set<Permissao> findAll();
  Optional<Permissao> findById(Long permissaoId);
  Optional<JpaPermissaoEntity> findByNome(String nome);
  Permissao save(Permissao permissao);
  JpaPermissaoEntity save(JpaPermissaoEntity permissaoEntity);
  Permissao update(Long permissaoId, Permissao permissao);
  void deleteById(Long permissaoId);
}

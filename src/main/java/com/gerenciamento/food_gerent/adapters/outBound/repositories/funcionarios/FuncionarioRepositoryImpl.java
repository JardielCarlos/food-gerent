package com.gerenciamento.food_gerent.adapters.outBound.repositories.funcionarios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaFuncionarioEntity;
import com.gerenciamento.food_gerent.domain.funcionarios.Funcionario;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioRepository;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.FuncionarioMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FuncionarioRepositoryImpl implements FuncionarioRepository {
  
  private final JpaFuncionarioRepository jpaFuncionarioRepository;
  private final FuncionarioMapper mapper;


  @Override
  public List<Funcionario> findAll() {
    List<JpaFuncionarioEntity> funcionarioEntities = this.jpaFuncionarioRepository.findAll();
    return mapper.jpaToDomainList(funcionarioEntities);
  }

  @Override
  public Optional<Funcionario> findById(UUID id) {
    Optional<JpaFuncionarioEntity> funcionarioEntity = this.jpaFuncionarioRepository.findById(id);

    return funcionarioEntity.map(mapper::jpaToDomain);
  }

  @Override
  public Funcionario save(Funcionario funcionario) {
    JpaFuncionarioEntity funcionarioEntity = mapper.domainToJpa(funcionario);

    jpaFuncionarioRepository.save(funcionarioEntity);

    return mapper.jpaToDomain(funcionarioEntity);
  }

  @Override
  public void deleteById(UUID id) {
    this.jpaFuncionarioRepository.deleteById(id);
  }
}

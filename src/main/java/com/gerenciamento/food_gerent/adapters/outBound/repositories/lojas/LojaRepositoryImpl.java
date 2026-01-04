package com.gerenciamento.food_gerent.adapters.outBound.repositories.lojas;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaLojaEntity;
import com.gerenciamento.food_gerent.domain.loja.Loja;
import com.gerenciamento.food_gerent.domain.loja.LojaRepository;
import com.gerenciamento.food_gerent.utils.mappers.LojaMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LojaRepositoryImpl implements LojaRepository {

  private final JpaLojaRepository jpaLojaRepository;
  private final LojaMapper mapper;
  
  @Override
  public List<Loja> findAll() {
    List<JpaLojaEntity> lojaEntities = this.jpaLojaRepository.findAll();
    return mapper.jpaToDomainList(lojaEntities);
  }

  @Override
  public Optional<Loja> findById(UUID id) {
    Optional<JpaLojaEntity> lojaEntity = this.jpaLojaRepository.findById(id);

    return lojaEntity.map(mapper::jpaToDomain);
  }

  @Override
  public List<Loja> findByIdEmpresa(UUID idEmpresa) {
    List<JpaLojaEntity> lojaEntities = this.jpaLojaRepository.findByEmpresaId(idEmpresa);

    return mapper.jpaToDomainList(lojaEntities);
  }

  @Override
  public Loja save(Loja loja) {
    JpaLojaEntity lojaEntity = mapper.domainToJpa(loja); 
    jpaLojaRepository.save(lojaEntity); 
    return mapper.jpaToDomain(lojaEntity); 
  }

  @Override
  public void deleteById(UUID idLoja) {
    jpaLojaRepository.deleteById(idLoja);
  }
  
}

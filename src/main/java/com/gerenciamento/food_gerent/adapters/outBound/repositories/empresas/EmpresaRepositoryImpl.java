package com.gerenciamento.food_gerent.adapters.outBound.repositories.empresas;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaClienteEntity;
import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaEmpresaEntity;
import com.gerenciamento.food_gerent.adapters.outBound.repositories.clientes.JpaClienteRepository;
import com.gerenciamento.food_gerent.domain.clientes.Cliente;
import com.gerenciamento.food_gerent.domain.empresas.Empresa;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaPatchDTO;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaRepository;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.ClienteMapper;
import com.gerenciamento.food_gerent.utils.mappers.EmpresaMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpresaRepositoryImpl implements EmpresaRepository {

  private final JpaEmpresaRepository jpaEmpresaRepository;
  private final JpaClienteRepository jpaClienteRepository;
  private final EmpresaMapper mapper;
  private final ClienteMapper mapperCliente;
  
  @Override
  public List<Empresa> findAll() {
    List<JpaEmpresaEntity> empresaEntities = this.jpaEmpresaRepository.findAll();

    return mapper.jpaToDomainList(empresaEntities);
  }

  @Override
  public Optional<Empresa> findById(UUID id) {
    Optional<JpaEmpresaEntity> empresaEntity = this.jpaEmpresaRepository.findById(id);
    return empresaEntity.map(mapper::jpaToDomain);
  }

  @Override
  public Optional<Empresa> findByCnpj(String cnpj) {
    Optional<JpaEmpresaEntity> empresaEntity = this.jpaEmpresaRepository.findByCnpj(cnpj);
    return empresaEntity.map(mapper::jpaToDomain);
  }

  @Override
  public Empresa save(Empresa empresa) {
    JpaClienteEntity clienteEntity = this.jpaClienteRepository.findById(empresa.getIdCliente()) 
      .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com id: " + empresa.getIdCliente()));
    
    JpaEmpresaEntity empresaEntity = new JpaEmpresaEntity(empresa, clienteEntity);
    this.jpaEmpresaRepository.save(empresaEntity);
    
    return mapper.jpaToDomain(empresaEntity);
  }

  @Override
  public Cliente updateEmpresaFromCliente(UUID idCliente, UUID idEmpresa, EmpresaPatchDTO data) {
    JpaClienteEntity clienteEntity = this.jpaClienteRepository.findById(idCliente) .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + idCliente));

    JpaEmpresaEntity empresaEntity = clienteEntity.getEmpresas().stream() 
    .filter(e -> e.getId().equals(idEmpresa)) 
    .findFirst() 
    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com ID: " + idEmpresa + " para o cliente com ID: " + idCliente));

    mapper.updateEmpresaEntityFromPatchDto(data, empresaEntity);
    jpaClienteRepository.save(clienteEntity);
    
    return mapperCliente.jpaToDomain(clienteEntity);
  }

  @Override
  public void deleteById(UUID idCliente, UUID idEmpresa) {
    JpaClienteEntity clienteEntity = this.jpaClienteRepository.findById(idCliente) 
      .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com id: " + idCliente));

    JpaEmpresaEntity empresaEntity = clienteEntity.getEmpresas().stream()
      .filter(e -> e.getId().equals(idEmpresa))
      .findFirst()
      .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + idEmpresa + " para o cliente com id: " + idCliente));

    clienteEntity.getEmpresas().remove(empresaEntity);
    this.jpaClienteRepository.save(clienteEntity);
  }
}

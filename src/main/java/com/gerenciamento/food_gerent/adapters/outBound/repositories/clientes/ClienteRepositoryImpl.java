package com.gerenciamento.food_gerent.adapters.outBound.repositories.clientes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaClienteEntity;
import com.gerenciamento.food_gerent.domain.clientes.Cliente;
import com.gerenciamento.food_gerent.domain.clientes.ClienteRepository;
import com.gerenciamento.food_gerent.utils.mappers.ClienteMapper;


@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

  private final JpaClienteRepository jpaClienteRepository;
  private final ClienteMapper mapper;

  // @Autowired
  public ClienteRepositoryImpl(JpaClienteRepository jpaClienteRepository, ClienteMapper mapper) {
    this.jpaClienteRepository = jpaClienteRepository;
    this.mapper = mapper;
  }

  @Override
  public Cliente save(Cliente cliente) {
    JpaClienteEntity clienteEntity = new JpaClienteEntity(cliente);
    this.jpaClienteRepository.save(clienteEntity);
    return mapper.jpaToDomain(clienteEntity);
  }

  @Override
  public Optional<Cliente> findById(UUID id) {
    Optional<JpaClienteEntity> clienteEntity = this.jpaClienteRepository.findById(id);
    return clienteEntity.map(mapper::jpaToDomain);
  }

  @Override
  public List<Cliente> findAll() {
    List<JpaClienteEntity> clienteEntities = this.jpaClienteRepository.findAll();

    return mapper.jpaToDomainList(clienteEntities);
  }

  @Override
  public void deleteById(UUID id) {
    this.jpaClienteRepository.deleteById(id);
  }
}

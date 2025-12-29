package com.gerenciamento.food_gerent.adapters.outBound.repositories.clientes;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaClienteEntity;
import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaPermissaoEntity;
import com.gerenciamento.food_gerent.adapters.outBound.repositories.permissoes.JpaPermissaoRepository;
import com.gerenciamento.food_gerent.domain.clientes.Cliente;
import com.gerenciamento.food_gerent.domain.clientes.ClienteRepository;
import com.gerenciamento.food_gerent.domain.empresas.Empresa;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.ClienteMapper;


@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

  private final JpaClienteRepository jpaClienteRepository;
  private final JpaPermissaoRepository permissaoRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  private final ClienteMapper mapper;

  public ClienteRepositoryImpl(JpaClienteRepository jpaClienteRepository, JpaPermissaoRepository permissaoRepository, ClienteMapper mapper, BCryptPasswordEncoder passwordEncoder) {
    this.jpaClienteRepository = jpaClienteRepository;
    this.permissaoRepository = permissaoRepository;
    this.passwordEncoder = passwordEncoder;
    this.mapper = mapper;
  }

  @Override
  public Cliente save(Cliente cliente) {
    JpaClienteEntity clienteEntity = mapper.toDomain(cliente);
    clienteEntity.setSenha(passwordEncoder.encode(clienteEntity.getSenha()));

    JpaPermissaoEntity permissao = permissaoRepository.findByNome("Basic")
      .orElseThrow(() -> new EntityNotFoundException("Permissão base do usuário não encontrada"));
      
    Set<JpaPermissaoEntity> permissoes = new HashSet<>(); 

    permissoes.add(permissao);
    clienteEntity.setPermissoes(permissoes);
    clienteEntity.setCargo(UsuarioEnumCargos.DONO);
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

  @Override
  public Optional<Empresa> findEmpresaById(UUID idCliente) {
    throw new UnsupportedOperationException("Unimplemented method 'findEmpresaById'");
  }

  @Override
  public Optional<JpaClienteEntity> findByNome(String nome) {
    Optional<JpaClienteEntity> clienteEntity = this.jpaClienteRepository.findByNome(nome);
    return clienteEntity;
  }

  @Override
  public JpaClienteEntity save(JpaClienteEntity clienteEntity) {
    return this.jpaClienteRepository.save(clienteEntity);
  }
}

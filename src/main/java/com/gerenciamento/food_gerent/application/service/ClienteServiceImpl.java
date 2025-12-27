package com.gerenciamento.food_gerent.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamento.food_gerent.application.usecases.ClienteUseCases;
import com.gerenciamento.food_gerent.domain.clientes.Cliente;
import com.gerenciamento.food_gerent.domain.clientes.ClientePatchDTO;
import com.gerenciamento.food_gerent.domain.clientes.ClienteRepository;
import com.gerenciamento.food_gerent.domain.clientes.ClienteRequestDTO;
import com.gerenciamento.food_gerent.domain.clientes.ClienteResponseDTO;
import com.gerenciamento.food_gerent.domain.empresas.Empresa;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaPatchDTO;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaRepository;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaResponseDTO;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.ClienteMapper;
import com.gerenciamento.food_gerent.utils.mappers.EmpresaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteUseCases {

  private final ClienteRepository repository;
  private final EmpresaRepository empresaRepository;
  private final EmpresaMapper empresaMapper;

  @Autowired
  private ClienteMapper mapper;

  @Override
  public List<ClienteResponseDTO> getAllClientes() {
    List<Cliente> clientes = this.repository.findAll();
    
    return mapper.toResponseDTOList(clientes);
  }

  @Override
  public ClienteResponseDTO getClienteById(UUID id) {
    Cliente cliente = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado!"));

    return mapper.toResponseDTO(cliente);
  }

  @Override
  public Cliente createCliente(ClienteRequestDTO data) {
    Cliente newCliente = mapper.toEntity(data);
    Cliente savedCliente = repository.save(newCliente);
    return savedCliente;
  }

  @Override
  public void deleteCliente(UUID id) {
    repository.deleteById(repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado!")).getId());
  }

  @Override
  public ClienteResponseDTO updateCliente(UUID id, ClientePatchDTO data) {
    Cliente cliente = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));
    
    // Atualiza APENAS os campos que não são null
    mapper.updateClienteFromPatchDto(data, cliente);
    
    Cliente updatedCliente = repository.save(cliente);
    return mapper.toResponseDTO(updatedCliente);
  
  }
  @Override
  public EmpresaResponseDTO updateEmpresaFromCliente(UUID idCliente, UUID idEmpresa, EmpresaPatchDTO data) {
    Cliente cliente = empresaRepository.updateEmpresaFromCliente(idCliente, idEmpresa, data);

    Empresa empresaAtualizada = cliente.getEmpresas().stream()
      .filter(e -> e.getId().equals(idEmpresa))
      .findFirst()
      .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com ID: " + idEmpresa + " para o cliente com ID: " + idCliente));

    empresaAtualizada.setIdCliente(idCliente);
    
    return empresaMapper.toResponseDTO(empresaAtualizada);
  }

  @Override
  public void deleteEmpresaFromCliente(UUID idCliente, UUID idEmpresa) {
    empresaRepository.deleteById(idCliente, idEmpresa);
  }

  @Override
  public EmpresaResponseDTO getClienteByEmpresaCnpj(String cnpj, UUID idCliente) {
    Empresa empresa = empresaRepository.findByCnpj(cnpj, idCliente)
      .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com CNPJ: " + cnpj + " para o cliente com ID: " + idCliente));

    return empresaMapper.toResponseDTO(empresa);
  }

}

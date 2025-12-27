package com.gerenciamento.food_gerent.application.usecases;

import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.clientes.Cliente;
import com.gerenciamento.food_gerent.domain.clientes.ClientePatchDTO;
import com.gerenciamento.food_gerent.domain.clientes.ClienteRequestDTO;
import com.gerenciamento.food_gerent.domain.clientes.ClienteResponseDTO;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaPatchDTO;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaResponseDTO;

public interface ClienteUseCases {
  public List<ClienteResponseDTO> getAllClientes();
  public ClienteResponseDTO getClienteById(UUID id);
  public EmpresaResponseDTO getClienteByEmpresaCnpj(String cnpj, UUID idCliente);
  public Cliente createCliente(ClienteRequestDTO data);
  public ClienteResponseDTO updateCliente(UUID id, ClientePatchDTO data);
  public EmpresaResponseDTO updateEmpresaFromCliente(UUID idCliente, UUID idEmpresa, EmpresaPatchDTO data);
  public void deleteCliente(UUID id);
  public void deleteEmpresaFromCliente(UUID idCliente, UUID idEmpresa);
  
}

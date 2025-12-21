package com.gerenciamento.food_gerent.application.usecases;

import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.clientes.Cliente;
import com.gerenciamento.food_gerent.domain.clientes.ClientePatchDTO;
import com.gerenciamento.food_gerent.domain.clientes.ClienteRequestDTO;
import com.gerenciamento.food_gerent.domain.clientes.ClienteResponseDTO;

public interface ClienteUseCases {
  public List<ClienteResponseDTO> getAllClientes();
  public ClienteResponseDTO getClienteById(UUID id);
  public Cliente createCliente(ClienteRequestDTO data);
  public ClienteResponseDTO updateCliente(UUID id, ClientePatchDTO data);
  public void deleteCliente(UUID id);
  
}

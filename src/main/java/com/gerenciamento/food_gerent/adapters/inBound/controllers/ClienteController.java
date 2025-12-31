package com.gerenciamento.food_gerent.adapters.inBound.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamento.food_gerent.application.service.ClienteServiceImpl;
import com.gerenciamento.food_gerent.domain.clientes.Cliente;
import com.gerenciamento.food_gerent.domain.clientes.ClientePatchDTO;
import com.gerenciamento.food_gerent.domain.clientes.ClienteRequestDTO;
import com.gerenciamento.food_gerent.domain.clientes.ClienteResponseDTO;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaPatchDTO;
import com.gerenciamento.food_gerent.domain.empresas.EmpresaResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

  private final ClienteServiceImpl clienteService;

  @GetMapping
  public ResponseEntity<List<ClienteResponseDTO>> getClientes(){
    List<ClienteResponseDTO> allClientes = this.clienteService.getAllClientes();
    return ResponseEntity.ok(allClientes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClienteResponseDTO> getClienteById(@PathVariable UUID id){
    ClienteResponseDTO cliente = this.clienteService.getClienteById(id);
    return ResponseEntity.ok(cliente);
  }

  @GetMapping("/{idCliente}/empresa")
  public ResponseEntity<EmpresaResponseDTO> getClienteByEmpresaCnpj(
    @PathVariable UUID idCliente,
    @RequestParam  String cnpj
  ){
    EmpresaResponseDTO empresa = this.clienteService.getClienteByEmpresaCnpj(cnpj, idCliente);
    return ResponseEntity.ok(empresa);
  }

  @PostMapping
  public ResponseEntity<Cliente> createCliente(@Valid @RequestBody ClienteRequestDTO clienteRequetDTO){
    Cliente newCliente = this.clienteService.createCliente(clienteRequetDTO);
    return ResponseEntity.ok(newCliente);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ClienteResponseDTO> patchCliente(@PathVariable UUID id, @Valid@RequestBody ClientePatchDTO dto
  ) {
    ClienteResponseDTO updated = clienteService.updateCliente(id, dto);
    return ResponseEntity.ok(updated);
  }

  @PatchMapping("/{idCliente}/empresa/{idEmpresa}")
  public ResponseEntity<EmpresaResponseDTO> patchEmpresaFromCliente(
    @PathVariable UUID idCliente,
    @PathVariable UUID idEmpresa,
    @Valid @RequestBody EmpresaPatchDTO dto
  ) {
    EmpresaResponseDTO updatedEmpresa = clienteService.updateEmpresaFromCliente(idCliente, idEmpresa, dto);
    return ResponseEntity.ok(updatedEmpresa);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCliente(@PathVariable UUID id){
    this.clienteService.deleteCliente(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/empresa/{idEmpresa}")
  public ResponseEntity<Void> deleteEmpresaFromCliente(@PathVariable UUID idEmpresa, JwtAuthenticationToken token){
    this.clienteService.deleteEmpresaFromCliente(idEmpresa, token);
    return ResponseEntity.noContent().build();
  }

}

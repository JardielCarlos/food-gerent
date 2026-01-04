package com.gerenciamento.food_gerent.domain.empresas;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.clientes.Cliente;

public interface EmpresaRepository {
  List<Empresa> findAll();

  Optional<Empresa> findById(UUID id);

  Optional<Empresa> findByCnpj(String cnpj, UUID idCliente);

  Empresa save(Empresa empresa);

  Cliente updateEmpresaFromCliente(UUID idCliente, UUID idEmpresa, EmpresaPatchDTO data);

  void deleteById(UUID idCliente, UUID idEmpresa);
}

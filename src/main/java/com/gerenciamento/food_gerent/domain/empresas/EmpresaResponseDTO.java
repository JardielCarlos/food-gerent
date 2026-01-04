package com.gerenciamento.food_gerent.domain.empresas;

import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.loja.LojaDetailsResponseDTO;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;


public record EmpresaResponseDTO(
  UUID id,
  UUID idCliente,
  String nome,
  String cnpj,
  String email,
  String rua,
  String bairro,
  String cidade,
  String estado,
  String cep,
  String telefone,
  EnumStatus status,
  String dataCriacao,
  String dataAtualizacao,
  List<LojaDetailsResponseDTO> lojas
) {
  
}

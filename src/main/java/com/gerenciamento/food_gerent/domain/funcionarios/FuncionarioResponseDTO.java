package com.gerenciamento.food_gerent.domain.funcionarios;

import java.math.BigDecimal;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.loja.LojaDetailsResponseDTO;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public record FuncionarioResponseDTO(
  UUID id, 
  String nome, 
  String email,
  String cpf, 
  UsuarioEnumCargos cargo, 
  BigDecimal salario,
  EnumStatus status, 
  String dataCriacao, 
  String dataAtualizacao, 
  String telefone,
  LojaDetailsResponseDTO loja
) {
  
}

package com.gerenciamento.food_gerent.domain.funcionarios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
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
  @JsonFormat(pattern = "dd/MM/yyyy") 
  LocalDate dataCriacao, 
  @JsonFormat(pattern = "dd/MM/yyyy") 
  LocalDate dataAtualizacao, 
  String telefone,
  LojaDetailsResponseDTO loja
) {
  
}

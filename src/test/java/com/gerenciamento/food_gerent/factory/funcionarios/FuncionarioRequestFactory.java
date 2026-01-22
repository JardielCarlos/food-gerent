package com.gerenciamento.food_gerent.factory.funcionarios;

import java.math.BigDecimal;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioPatchDTO;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioRequestDTO;

public class FuncionarioRequestFactory {
  public static FuncionarioRequestDTO build(){
    return new FuncionarioRequestDTO(
      "Funcionario João",
      "joao@gmail.com",
      "senha123",
      "123.456.789-00",
      "83-99999-9999",
      BigDecimal.valueOf(1800),
      UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac")
    );
  }

  public static FuncionarioPatchDTO buildPatch(){
    return new FuncionarioPatchDTO(
      "Funcionario João Atualizado",
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
}

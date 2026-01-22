package com.gerenciamento.food_gerent.factory.funcionarios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioResponseDTO;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.factory.loja.LojaResponseFactory;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public class FuncionarioResponseFactory {
  public static final UUID FUNCIONARIO_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");

  public static FuncionarioResponseDTO build(){
    return new FuncionarioResponseDTO(
      FUNCIONARIO_ID,
      "Funcionario João",
      "joao@gmail.com",
      "123.456.789-00",
      UsuarioEnumCargos.EMPREGADO,
      BigDecimal.valueOf(1800),
      EnumStatus.ATIVO,
      LocalDate.parse("2023-05-15"),
      LocalDate.parse("2024-05-15"),
      "83-99999-9999",
      LojaResponseFactory.buildDetailsResponse()
    );
  }

  public static List<FuncionarioResponseDTO> buildList(){
    return List.of(
      new FuncionarioResponseDTO(
        FUNCIONARIO_ID,
        "Funcionario João",
        "joao@gmail.com",
        "123.456.789-00",
        UsuarioEnumCargos.EMPREGADO,
        BigDecimal.valueOf(1800),
        EnumStatus.ATIVO,
        LocalDate.parse("2023-05-15"),
        LocalDate.parse("2024-05-15"),
        "83-99999-9999",
        LojaResponseFactory.buildDetailsResponse()
      ),
      new FuncionarioResponseDTO(
        UUID.fromString("22222222-2222-2222-2222-222222222222"),
        "Funcionario Maria",
        "maria@gmail.com",
        "987.654.321-00",
        UsuarioEnumCargos.COZINHEIRO,
        BigDecimal.valueOf(1500),
        EnumStatus.ATIVO,
        LocalDate.parse("2022-03-10"),
        LocalDate.parse("2024-03-10"),
        "83-88888-8888",
        LojaResponseFactory.buildDetailsResponse()
      )
    );
  }

  public static FuncionarioResponseDTO buildUpdated(){
    return new FuncionarioResponseDTO(
      FUNCIONARIO_ID, 
      "Funcionario João Atualizado", 
      "joao@gmail.com",
      "123.456.789-00",
      UsuarioEnumCargos.EMPREGADO,
      BigDecimal.valueOf(1800),
      EnumStatus.ATIVO,
      LocalDate.parse("2023-05-15"),
      LocalDate.parse("2024-05-15"),
      "83-99999-9999",
      LojaResponseFactory.buildDetailsResponse()
    );
  }
}

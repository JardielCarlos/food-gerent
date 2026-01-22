package com.gerenciamento.food_gerent.factory.funcionarios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.funcionarios.Funcionario;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.factory.loja.LojaFactory;
import com.gerenciamento.food_gerent.factory.permissao.PermissaoFactory;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public class FuncionarioFactory {
  public static Funcionario build(){
    return new Funcionario(
      UUID.fromString("11111111-1111-1111-1111-111111111111"),
      "Funcionario João",
      "joao@gmail.com",
      "senha123",
      "123.456.789-00",
      UsuarioEnumCargos.EMPREGADO,
      EnumStatus.ATIVO,
      LocalDate.parse("2023-05-15"),
      LocalDate.parse("2024-05-15"),
      "83-99999-9999",
      Set.of(PermissaoFactory.build()),
      BigDecimal.valueOf(1800),
      LojaFactory.build()
    );
  }
  public static List<Funcionario> buildList(){
    return List.of(
      new Funcionario(
        UUID.fromString("11111111-1111-1111-1111-111111111111"),
        "Funcionario João",
        "joao@gmail.com",
        "senha123",
        "123.456.789-00",
        UsuarioEnumCargos.EMPREGADO,
        EnumStatus.ATIVO,
        LocalDate.parse("2023-05-15"),
        LocalDate.parse("2024-05-15"),
        "83-99999-9999",
        Set.of(PermissaoFactory.build()),
        BigDecimal.valueOf(1800),
        LojaFactory.build()
      ),
      new Funcionario(
        UUID.fromString("22222222-2222-2222-2222-222222222222"),
        "Funcionario Maria",
        "maria@gmail.com",
        "senha123",
        "987.654.321-00",
        UsuarioEnumCargos.COZINHEIRO,
        EnumStatus.ATIVO,
        LocalDate.parse("2022-03-10"),
        LocalDate.parse("2024-03-10"),
        "83-88888-8888",
        Set.of(PermissaoFactory.build()),
        BigDecimal.valueOf(1500),
        LojaFactory.build()
      )
    );
  }
}

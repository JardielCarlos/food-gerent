package com.gerenciamento.food_gerent.factory.loja;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.loja.Loja;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public class LojaFactory {
  public static Loja build(){
    return new Loja(
      UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac"),
      UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c"), 
      "Loja A",
      "12.345.678/0001-90",
      "Rua a",
      "Bairro A",
      "Cidade A", 
      "PB", 
      "58000-000", 
      "(83) 99999-9999",
      EnumStatus.ATIVO,
      LocalDate.parse("2024-01-01"),
      LocalDate.parse("2024-06-01")
    );
  }

  public static List<Loja> buildList() {
    return List.of(
      new Loja(
        UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac"), 
        UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c"),  
        "Loja B",
        "98.765.432/0001-09",
        "Rua b",
        "Bairro B",
        "Cidade B", 
        "SP", 
        "01000-000", 
        "(11) 98888-8888",
        EnumStatus.INATIVO,
        LocalDate.parse("2023-05-15"),
        LocalDate.parse("2024-05-15")
      ),
      new Loja(
        UUID.fromString("f1e2d3c4-b5a6-78b9-0c1d-2e3f4a5b6c78"),
        UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c"), 
        "Loja C",
        "11.222.333/0001-44",
        "Rua c",
        "Bairro C",
        "Cidade C", 
        "RJ", 
        "20000-000", 
        "(21) 97777-7777",
        EnumStatus.ATIVO,
        LocalDate.parse("2022-11-20"),
        LocalDate.parse("2024-04-20")
      )
    );
  }
}

package com.gerenciamento.food_gerent.factory;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.loja.LojaResponseDTO;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public class LojaResponseFactory {
  
  public static List<LojaResponseDTO> createLojaResponseList(){
    LojaResponseDTO loja1 = new LojaResponseDTO(
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

    LojaResponseDTO loja2 = new LojaResponseDTO(
      UUID.fromString("e2f3c1b4-3c4b-4d5a-9f7e-2b6c8d9e0f1a"), 
      UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c"), 
      "Loja A",
      "12.345.678/0001-80",
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

    return List.of(loja1, loja2);
  }

  public static LojaResponseDTO createLojaResponse(){
    return new LojaResponseDTO(
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

}

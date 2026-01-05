package com.gerenciamento.food_gerent.factory;

import com.gerenciamento.food_gerent.domain.loja.LojaRequestDTO;

public class LojaRequestFactory {
  public static LojaRequestDTO createLojaRequest(){
    return new LojaRequestDTO(
      "Loja A",
      "12.345.678/0001-90",
      "Rua a",
      "Bairro A",
      "Cidade A", 
      "PB", 
      "58000-000", 
      "(83) 99999-9999", 
      "6fe8dc87-623e-4b8e-b713-a754bc2ee14c"
    );
  }
}

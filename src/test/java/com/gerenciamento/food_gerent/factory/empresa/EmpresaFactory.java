package com.gerenciamento.food_gerent.factory.empresa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.empresas.Empresa;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public class EmpresaFactory {
  public static Empresa build(){
    UUID idEmpresa = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");
    UUID idCliente = UUID.fromString("d290f1ee-6c54-4b01-90e6-d701748f0851");

    return new Empresa(
      idEmpresa,  
      idCliente,
      "Empresa A",
      "12.345.678/0001-90",
      "Rua das Flores", 
      "Centro",         
      "Cidade Exemplo", 
      "PB",             
      "58000-000",      
      "(83) 99999-9999",
      "empresaA@email.com",
      EnumStatus.ATIVO, 
      LocalDate.parse("2024-01-01"),
      LocalDate.parse("2024-06-01"),  
      new ArrayList<>() 
    );
  }
}
   
package com.gerenciamento.food_gerent.domain.empresas;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmpresaPatchDTO(
  
    @Size(min = 3, message = "O nome da empresa deve ter no mínimo 3 caracteres")
    String nome,

    @Pattern(
      regexp = "(\\d{14})|(\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})",
      message = "O CNPJ deve ter 14 dígitos ou estar no formato XX.XXX.XXX/XXXX-XX"
    )
    String cnpj,

    @Email(message = "Email inválido")
    String email,

    @Size(min = 3, message = "O nome da rua deve ter no mínimo 3 caracteres")
    String rua,

    @Size(min = 3, message = "O nome do bairro deve ter no mínimo 3 caracteres")
    String bairro,

    @Size(min = 3, message = "O nome da cidade deve ter no mínimo 3 caracteres")
    String cidade,

    @Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres")
    String estado,

    @Pattern(
    regexp = "^\\d{5}-?\\d{3}$",
    message = "O CEP deve ter 8 dígitos ou estar no formato 00000-000"
  )
    String cep,

    @Pattern(
      regexp = "^(\\+\\d{1,3}\\s?)?(\\(\\d{2,3}\\)\\s?)?\\d{4,5}[-\\s]?\\d{4}$",
      message = "Número de telefone inválido"
    )
    String telefone,
    
    EnumStatus status
) {
  
}

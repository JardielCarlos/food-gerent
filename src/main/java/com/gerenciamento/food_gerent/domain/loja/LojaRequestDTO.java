package com.gerenciamento.food_gerent.domain.loja;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LojaRequestDTO(

  @NotBlank(message = "O nome da loja é obrigatório")
  @Size(min = 3, message = "O nome da loja deve ter no mínimo 3 caracteres")
  String nome,

  @NotBlank(message = "O CNPJ da empresa é obrigatório")
  @Pattern(
    regexp = "(\\d{14})|(\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})",
    message = "O CNPJ deve ter 14 dígitos ou estar no formato XX.XXX.XXX/XXXX-XX"
  )
  String cnpj,

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

  String telefone,

  @NotBlank(message = "id da empresa é obrigatório")
  String idEmpresa
) {
  
}

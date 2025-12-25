package com.gerenciamento.food_gerent.domain.clientes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(
  @NotBlank(message = "O nome do cliente é obrigatório")
  @Size(min = 3, message = "O nome do cliente deve ter no mínimo 3 caracteres")
  String nome,

  @NotBlank(message = "O email do cliente é obrigatório")
  @Email(message = "Email inválido")
  String email,

  @NotBlank(message = "A senha do cliente é obrigatória")
  String senha,

  @NotBlank(message = "O CPF do cliente é obrigatório")
  @Pattern(
    regexp = "^(?:\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$",
    message = "CPF deve ser 11 dígitos ou no formato 000.000.000-00"
  )
  String cpf,

  String telefone
) {
  
}

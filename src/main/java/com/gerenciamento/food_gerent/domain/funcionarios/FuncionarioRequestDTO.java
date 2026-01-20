package com.gerenciamento.food_gerent.domain.funcionarios;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FuncionarioRequestDTO(
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

  String telefone,

  @NotNull(message = "O salário do funcionário é obrigatório")
  @DecimalMin(value = "0.0", inclusive = false, message = "O salário deve ser maior que zero") // -> Inclusive false significa que o valor 0.0 não é aceito apenas maiores que 0.0
  BigDecimal salario,

  @NotNull(message = "O ID da loja é obrigatório")
  UUID lojaId
) {
  
}

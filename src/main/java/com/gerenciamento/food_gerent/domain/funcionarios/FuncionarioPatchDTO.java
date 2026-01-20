package com.gerenciamento.food_gerent.domain.funcionarios;

import java.math.BigDecimal;

import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FuncionarioPatchDTO(
  @Size(min = 3, message = "O nome do cliente deve ter no mínimo 3 caracteres")
  String nome,

  @Email(message = "Email inválido")
  String email,

  @Pattern(
      regexp = "^(?:\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$",
      message = "CPF deve ser 11 dígitos ou no formato 000.000.000-00"
  )
  String cpf, 
  
  @DecimalMin(value = "0.0", inclusive = false, message = "O salário deve ser maior que zero") // -> Inclusive false significa que o valor 0.0 não é aceito apenas maiores que 0.0
  BigDecimal salario,

  String telefone,
  String senha, 
  UsuarioEnumCargos cargo, 
  EnumStatus status
) {
  
}

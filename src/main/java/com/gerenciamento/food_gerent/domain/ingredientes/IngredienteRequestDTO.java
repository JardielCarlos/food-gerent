package com.gerenciamento.food_gerent.domain.ingredientes;

import java.math.BigDecimal;
import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumUnidadeMedida;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record IngredienteRequestDTO(

    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(min = 3, message = "O nome da categoria deve ter no mínimo 3 caracteres")
    String nome,

    @NotNull(message = "O custo unitário é obrigatório")
    @DecimalMin(value = "0.1", inclusive = true, message = "O custo unitário deve ser maior que zero")
    BigDecimal custoUnitario,
    
    @NotNull(message = "A unidade de medida é obrigatória")
    EnumUnidadeMedida unidadeMedida,

    @NotNull(message = "O ID da categoria é obrigatório")
    UUID categoriaId,
    
    UUID[] tagsIds
) {
  
}

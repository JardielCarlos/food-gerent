package com.gerenciamento.food_gerent.adapters.outBound.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumUnidadeMedida;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lotes_ingredientes")
public class JpaLoteIngredienteEntity {

  @Id
  @GeneratedValue
  private UUID id;

  private UUID ingredienteId;

  private BigDecimal quantidadeInicial;

  private BigDecimal quantidadeDisponivel;

  @Enumerated(EnumType.STRING) 
  private EnumUnidadeMedida unidadeMedida;

  private LocalDate dataFabricacao;

  private LocalDate dataValidade;

  private BigDecimal precoTotal;

  @Enumerated(EnumType.STRING) 
  private EnumStatus status;

  @CreationTimestamp 
  private LocalDate dataCriacao; 

  @UpdateTimestamp 
  private LocalDate dataAtualizacao;
}

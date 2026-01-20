package com.gerenciamento.food_gerent.adapters.outBound.entities;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("FUNCIONARIO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JpaFuncionarioEntity extends JpaUsuarioEntity {
  @ManyToOne
  @JoinColumn(name = "loja_id", nullable = true)
  private JpaLojaEntity loja;

  private BigDecimal salario;

}


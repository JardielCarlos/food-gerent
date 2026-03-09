package com.gerenciamento.food_gerent.adapters.outBound.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumUnidadeMedida;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "itens_estoque")
public class JpaItemEstoqueEntity {
  
  @Id
  @GeneratedValue
  private UUID id;
  private String nomeIngrediente;
  private UUID ingredienteID;
  private UUID loteID;
  private BigDecimal quantidade;
  private BigDecimal reservado;
  private LocalDate dataValidade; 

  @Enumerated(EnumType.STRING)
  private EnumUnidadeMedida unidade;

  @Enumerated(EnumType.STRING)
  private EnumStatus status;

  @ManyToOne(fetch = FetchType.LAZY) 
  @JoinColumn(name = "estoque_id") 
  private JpaEstoqueEntity estoque;

  @CreationTimestamp
  private LocalDate dataCriacao;

  @UpdateTimestamp
  private LocalDate dataAtualizacao;

  @PrePersist
  public void prePersist() { 
    if (this.status == null) { 
      this.status = EnumStatus.ATIVO; 
    }
  }
}

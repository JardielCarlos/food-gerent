package com.gerenciamento.food_gerent.adapters.outBound.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumTipoEstoque;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "estoques")
public class JpaEstoqueEntity {
  
  @Id
  @GeneratedValue
  private UUID id;

  private UUID lojaID;

  private String nomeEstoque;

  @Enumerated(EnumType.STRING) 
  private EnumTipoEstoque tipoEstoque;

  @OneToMany(mappedBy = "estoque", cascade = CascadeType.ALL, orphanRemoval = true) 
  private List<JpaItemEstoqueEntity> itens;

  @Enumerated(EnumType.STRING)
  private EnumStatus status;

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

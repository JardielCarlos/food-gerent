package com.gerenciamento.food_gerent.adapters.outBound.entities;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tags_ingredientes",
  indexes = {@Index(name = "idx_tag_nome", columnList = "nome")}
)
public class JpaTagIngredienteEntity {
  
  @Id
  @GeneratedValue
  private UUID id;

  @Column(nullable = false, unique = true)
  private String nome;

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

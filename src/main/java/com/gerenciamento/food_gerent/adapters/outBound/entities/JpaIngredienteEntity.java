package com.gerenciamento.food_gerent.adapters.outBound.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumUnidadeMedida;
import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaTagIngredienteEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "ingredientes",
  indexes = {
    @Index(name = "idx_ingrediente_nome", columnList = "nome"),
    @Index(name = "idx_ingrediente_categoria", columnList = "categoria_id")
  }
)
public class JpaIngredienteEntity {
  
  @Id
  @GeneratedValue
  private UUID id;

  @Column(nullable = false)
  private String nome;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private EnumUnidadeMedida unidadeMedida;

  @Column(nullable = false)
  private BigDecimal custoUnitario;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "categoria_id")
  private JpaCategoriaIngredienteEntity categoria;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "ingredientes_tags",
    joinColumns = @JoinColumn(name = "ingrediente_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"),
    indexes = {
      @Index(name = "idx_ingrediente_tag_ingrediente", columnList = "ingrediente_id"),
      @Index(name = "idx_ingrediente_tag_tag", columnList = "tag_id")
    }
  )
  private Set<JpaTagIngredienteEntity> tags = new HashSet<>();

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

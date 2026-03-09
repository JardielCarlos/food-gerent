package com.gerenciamento.food_gerent.adapters.outBound.entities;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"parent", "subcategorias"})
@Entity
@Table(name = "categoria_ingredientes",
  indexes = {
    @Index(name = "idx_categoria_ingrediente_nome", columnList = "nome") 
  }
)
public class JpaCategoriaIngredienteEntity {
  @Id
  @GeneratedValue
  @EqualsAndHashCode.Include
  private UUID id;
  
  @Column(nullable = false)
  private String nome;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private JpaCategoriaIngredienteEntity parent;

  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<JpaCategoriaIngredienteEntity> subcategorias = new LinkedHashSet<>();

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

package com.gerenciamento.food_gerent.adapters.outBound.entities;

import java.util.UUID;

import com.gerenciamento.food_gerent.domain.clientes.Cliente;

import jakarta.persistence.Entity;
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
@Table(name = "clientes")
public class JpaClienteEntity {

  @Id
  @GeneratedValue
  private UUID id;
  
  private String nome;
  private String email;
  private String telefone;

  public JpaClienteEntity(Cliente cliente) {
    this.id = cliente.getId();
    this.nome = cliente.getNome();
    this.email = cliente.getEmail();
    this.telefone = cliente.getTelefone();
  }
}

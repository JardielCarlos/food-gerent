package com.gerenciamento.food_gerent.domain.clientes;

import java.util.UUID;

import com.gerenciamento.food_gerent.domain.usuarios.Usuario;

public class Cliente extends Usuario{
  // Campos especifico de cliente
  // Ex. private String endereco;
  public Cliente(){ super(); }

  public Cliente(UUID id, String nome, String email, String telefone) {
    super(id, nome, email, telefone);
  }
}

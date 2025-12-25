package com.gerenciamento.food_gerent.domain.clientes;

import java.time.LocalDate;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.usuarios.Usuario;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumStatus;

public class Cliente extends Usuario {
  // Campos especifico de cliente
  // Ex. private String endereco;
  public Cliente(){ super(); }

  public Cliente(UUID id, String nome, String email, String senha, String cpf, UsuarioEnumCargos cargo, UsuarioEnumStatus status, LocalDate dataCriacao, LocalDate dataAtualizacao, String telefone) {
    super(id, nome, email, senha, cpf, cargo, status, dataCriacao, dataAtualizacao, telefone);
  }
}

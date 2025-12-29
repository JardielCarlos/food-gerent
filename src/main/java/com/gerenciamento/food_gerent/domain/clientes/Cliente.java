package com.gerenciamento.food_gerent.domain.clientes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.empresas.Empresa;
import com.gerenciamento.food_gerent.domain.permissoes.Permissao;
import com.gerenciamento.food_gerent.domain.usuarios.Usuario;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public class Cliente extends Usuario {
  // Campos especifico de cliente
  
  private List<Empresa> empresas = new ArrayList<>();

  public Cliente(){ super(); }

  public Cliente(UUID id, String nome, String email, String senha, String cpf, UsuarioEnumCargos cargo, EnumStatus status, LocalDate dataCriacao, LocalDate dataAtualizacao, String telefone, Set<Permissao> permissoes) {
    super(id, nome, email, senha, cpf, cargo, status, dataCriacao, dataAtualizacao, telefone, permissoes);
    this.empresas = new ArrayList<>();
  }

  public List<Empresa> getEmpresas() {
    return empresas;
  }

  public void setEmpresas(List<Empresa> empresas) {
    this.empresas.addAll(empresas);
  }

  public void adicionarEmpresa(Empresa empresa) { 
    this.empresas.add(empresa); 
  }
}

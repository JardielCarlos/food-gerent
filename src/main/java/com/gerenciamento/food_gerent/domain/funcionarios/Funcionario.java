package com.gerenciamento.food_gerent.domain.funcionarios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.loja.Loja;
import com.gerenciamento.food_gerent.domain.permissoes.Permissao;
import com.gerenciamento.food_gerent.domain.usuarios.Usuario;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public class Funcionario extends Usuario {
  
  private BigDecimal salario;
  private Loja loja;

  public Funcionario() { super(); }

  public Funcionario(UUID id, String nome, String email, String senha, String cpf, UsuarioEnumCargos cargo, EnumStatus status, LocalDate dataCriacao, LocalDate dataAtualizacao, String telefone, Set<Permissao> permissoes, BigDecimal salario, Loja loja) {
     super(id, nome, email, senha, cpf, cargo, status, dataCriacao, dataAtualizacao, telefone, permissoes);
     this.salario = salario;
     this.loja = loja;
  }

  public BigDecimal getSalario() {
    return salario;
  }

  public void setSalario(BigDecimal salario) {
    this.salario = salario;
  }

  public Loja getLoja() {
    return loja;
  }

  public void setLoja(Loja loja) { 
    this.loja = loja; 
  }
}

package com.gerenciamento.food_gerent.factory.permissao;

import com.gerenciamento.food_gerent.domain.permissoes.Permissao;

public class PermissaoFactory {
  public static Permissao build(){
    return new Permissao(
      1L,
      "Basic"
    );
  }
}

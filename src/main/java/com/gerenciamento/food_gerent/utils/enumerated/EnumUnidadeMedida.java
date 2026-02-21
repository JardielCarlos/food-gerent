package com.gerenciamento.food_gerent.utils.enumerated;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum EnumUnidadeMedida {
  GRAMA("g", BigDecimal.valueOf(1)),
  KILOGRAMA("kg", BigDecimal.valueOf(1000)),
  LITRO("l", BigDecimal.valueOf(1)),
  MILILITRO("ml", BigDecimal.valueOf(0.001)),
  UNIDADE("un", BigDecimal.ONE);

  private final String sigla;
  private final BigDecimal fatorConversao;

  EnumUnidadeMedida(String sigla, BigDecimal fatorConversao) {
    this.sigla = sigla;
    this.fatorConversao = fatorConversao;
  }

  public BigDecimal converterPara(EnumUnidadeMedida unidadeDestino, BigDecimal quantidade) {
    BigDecimal quantidadeEmBase = quantidade.multiply(this.fatorConversao);
    return quantidadeEmBase.divide(unidadeDestino.fatorConversao, 6, RoundingMode.HALF_UP);
  }

  public String getSigla() {
    return sigla;
  }
}

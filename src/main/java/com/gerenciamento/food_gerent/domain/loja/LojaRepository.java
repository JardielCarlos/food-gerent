package com.gerenciamento.food_gerent.domain.loja;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LojaRepository {
  List<Loja> findAll();
  Optional<Loja> findById(UUID id);
  List<Loja> findByIdEmpresa(UUID idEmpresa);
  Loja save(Loja loja);
  void deleteById(UUID idLoja);

}

package com.gerenciamento.food_gerent.adapters.outBound.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CLIENTE")
@Data
@NoArgsConstructor
// @AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JpaClienteEntity extends JpaUsuarioEntity{

  // Campos especificos de cliente podem ser adicionados aqui

  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<JpaEmpresaEntity> empresas = new ArrayList<>();

  public void addEmpresa(JpaEmpresaEntity empresa) {
    empresas.add(empresa);
    empresa.setCliente(this);
  }
}

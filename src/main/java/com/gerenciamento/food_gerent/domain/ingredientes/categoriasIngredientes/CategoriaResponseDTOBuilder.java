package com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public class CategoriaResponseDTOBuilder {

  private UUID id;
  private String nome;
  private UUID parentId;
  private Integer nivel;
  private EnumStatus status;
  private LocalDate dataCriacao;
  private LocalDate dataAtualizacao;

  private final List<CategoriaResponseDTOBuilder> children = new ArrayList<>();

  private CategoriaResponseDTOBuilder() {}

  public static CategoriaResponseDTOBuilder fromDomain(CategoriaIngrediente c) {
    CategoriaResponseDTOBuilder b = new CategoriaResponseDTOBuilder();
    b.id = c.getId();
    b.nome = c.getNome();
    b.parentId = c.getParentId();
    b.nivel = c.getNivel();
    b.status = c.getStatus();
    b.dataCriacao = c.getDataCriacao();
    b.dataAtualizacao = c.getDataAtualizacao();
    return b;
  }

  public void addChild(CategoriaResponseDTOBuilder child) {
    this.children.add(child);
  }

  public CategoriaIngredienteResponseDTO buildDto() {
    List<SubCategoriaResponseDTO> subDtos = children.stream()
        .map(CategoriaResponseDTOBuilder::buildSubDto)
        .collect(Collectors.toList());

    return new CategoriaIngredienteResponseDTO(
        id,
        nome,
        parentId,
        subDtos,     
        nivel,
        status,
        dataCriacao,
        dataAtualizacao
    );
  }

  // Constrói um SubCategoriaResponseDTO (recursivo)
  public SubCategoriaResponseDTO buildSubDto() {
    List<SubCategoriaResponseDTO> childSubDtos = children.stream()
        .map(CategoriaResponseDTOBuilder::buildSubDto)
        .collect(Collectors.toList());

    return new SubCategoriaResponseDTO(
        id,
        nome,
        parentId,
        nivel,
        childSubDtos
    );
  }
}

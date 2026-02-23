package com.gerenciamento.food_gerent.application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerenciamento.food_gerent.application.usecases.CategoriaIngredienteUseCases;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaIngrediente;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaIngredientePatchDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaIngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaIngredienteResponseDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaRepository;
import com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes.CategoriaResponseDTOBuilder;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.CategoriaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaIngredienteServiceImpl implements CategoriaIngredienteUseCases{

  private final CategoriaRepository categoriaRepository;
  private final CategoriaMapper categoriaMapper;
  
  @Override
  @Transactional(readOnly = true)
  public List<CategoriaIngredienteResponseDTO> getAllCategorias() {
    List<CategoriaIngrediente> all = this.categoriaRepository.findAll();
    Map<UUID, CategoriaResponseDTOBuilder> builders = buildTreeMap(all);

    // retornar apenas raízes (parentId == null)
    List<CategoriaResponseDTOBuilder> roots = new ArrayList<>();
    for (CategoriaIngrediente c : all) {
      CategoriaResponseDTOBuilder node = builders.get(c.getId());
      if (node == null) continue;
      if (c.getParentId() == null) {
        roots.add(node);
      } else if (!builders.containsKey(c.getParentId())) {
        roots.add(node);
      }
    }

    return roots.stream()
      .map(CategoriaResponseDTOBuilder::buildDto)
      .collect(Collectors.toList());
  }


  @Override
  @Transactional(readOnly = true)
  public CategoriaIngredienteResponseDTO getCategoriaById(UUID id) {
    // buscar tudo para montar a sub-árvore a partir do id solicitado
    List<CategoriaIngrediente> all = this.categoriaRepository.findAll();
    Map<UUID, CategoriaResponseDTOBuilder> builders = buildTreeMap(all);

    CategoriaResponseDTOBuilder node = builders.get(id);
    if (node == null) {
      throw new EntityNotFoundException("Categoria não encontrada com o ID: " + id);
    }

    return node.buildDto();
  }


  private Map<UUID, CategoriaResponseDTOBuilder> buildTreeMap(List<CategoriaIngrediente> all) {
    Map<UUID, CategoriaResponseDTOBuilder> builders = new HashMap<>();
    for (CategoriaIngrediente c : all) {
      if (c.getId() != null) {
        builders.put(c.getId(), CategoriaResponseDTOBuilder.fromDomain(c));
      }
    }
    for (CategoriaIngrediente c : all) {
      if (c.getParentId() != null) {
        CategoriaResponseDTOBuilder parent = builders.get(c.getParentId());
        CategoriaResponseDTOBuilder child = builders.get(c.getId());
        if (parent != null && child != null) {
          parent.addChild(child);
        }
      }
    }
    return builders;
  }

  @Override
  public CategoriaIngredienteResponseDTO createCategoria(CategoriaIngredienteRequestDTO categoriaDTO) {
      CategoriaIngrediente categoria = categoriaMapper.requestToDomain(categoriaDTO);

      if (categoria.getParentId() != null && categoria.getId() != null &&
          categoria.getParentId().equals(categoria.getId())) {
          throw new IllegalArgumentException("Categoria não pode ser pai de si mesma");
      }

      // calcular nivel corretamente
      if (categoria.getParentId() == null) {
          // raiz
          categoria.setNivel(0);
      } else {
          // parentId informado -> buscar o pai para obter o nivel correto e garantir que existe
          CategoriaIngrediente parent = categoriaRepository.findById(categoria.getParentId())
              .orElseThrow(() -> new EntityNotFoundException("Categoria pai não encontrada: " + categoria.getParentId()));
              
          Integer parentNivel = parent.getNivel() == null ? 0 : parent.getNivel();
          categoria.setNivel(parentNivel + 1);

          if (parent.getSubcategorias() != null) {
            parent.getSubcategorias().add(categoria);
          }
      }

      CategoriaIngrediente saved = categoriaRepository.save(categoria);
      return categoriaMapper.domainToResponse(saved);
  }


  @Override
  @Transactional
  public CategoriaIngredienteResponseDTO updateCategoria(UUID id, CategoriaIngredientePatchDTO categoriaPatch) {
    CategoriaIngrediente categoria = categoriaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + id));

    // Atualizar nome se presente
    categoriaPatch.nome().ifPresent(nome -> {
      if (nome.isBlank() || nome.length() < 3) {
        throw new IllegalArgumentException("O nome da categoria deve ter no mínimo 3 caracteres");
      }
      categoria.setNome(nome);
    });

    // Atualizar status se presente
    categoriaPatch.status().ifPresent(categoria::setStatus);

    if (categoriaPatch.parentId() != null) {
      UUID newParentId = categoriaPatch.parentId().orElse(null);
      if (newParentId != null && newParentId.equals(id)) {
        throw new IllegalArgumentException("Categoria não pode ser pai de si mesma");
      }
      if (newParentId != null) {
        CategoriaIngrediente parent = categoriaRepository.findById(newParentId)
            .orElseThrow(() -> new EntityNotFoundException("Categoria pai não encontrada: " + newParentId));
        Integer parentNivel = parent.getNivel() == null ? 0 : parent.getNivel();
        categoria.setNivel(parentNivel + 1);
      } else {
        // parentId é null explícito → voltar para raiz
        categoria.setNivel(0);
      }
      categoria.setParentId(newParentId);
    }

    CategoriaIngrediente saved = categoriaRepository.save(categoria);
    return categoriaMapper.domainToResponse(saved);
  }

  @Override
  public void deleteCategoria(UUID id) {
    this.categoriaRepository.findById(id).orElseThrow(() -> 
      new EntityNotFoundException("Categoria não encontrado com o ID: " + id));
    this.categoriaRepository.deleteById(id);
  }
  
}

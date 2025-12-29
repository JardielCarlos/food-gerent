package com.gerenciamento.food_gerent.application.usecases;

import java.util.Set;

import com.gerenciamento.food_gerent.domain.permissoes.PermissaoRequestDTO;
import com.gerenciamento.food_gerent.domain.permissoes.PermissaoResponseDTO;

public interface PermissaoUseCases {
    public Set<PermissaoResponseDTO> getAllPermissoes();
    public PermissaoResponseDTO getPermissaoById(Long permissaoId);
    public PermissaoResponseDTO createPermissao(PermissaoRequestDTO permissaoDTO);
    public PermissaoResponseDTO updatePermissao(Long permissaoId, PermissaoRequestDTO permissaoDTO);
    public void deletePermissao(Long permissaoId);

}

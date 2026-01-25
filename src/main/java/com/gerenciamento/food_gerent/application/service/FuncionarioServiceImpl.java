package com.gerenciamento.food_gerent.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaPermissaoEntity;
import com.gerenciamento.food_gerent.application.usecases.FuncionarioUseCases;
import com.gerenciamento.food_gerent.domain.funcionarios.Funcionario;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioPatchDTO;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioRepository;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioRequestDTO;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioResponseDTO;
import com.gerenciamento.food_gerent.domain.loja.Loja;
import com.gerenciamento.food_gerent.domain.loja.LojaRepository;
import com.gerenciamento.food_gerent.domain.permissoes.Permissao;
import com.gerenciamento.food_gerent.domain.permissoes.PermissaoRepository;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.FuncionarioMapper;
import com.gerenciamento.food_gerent.utils.mappers.PermissaoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioUseCases{

  private final FuncionarioRepository funcionarioRepository;
  private final LojaRepository lojaRepository;
  private final PermissaoRepository permissaoRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final FuncionarioMapper mapper; 
  private final PermissaoMapper permissaoMapper;

  @Override
  public List<FuncionarioResponseDTO> getAllFuncionarios() {
    List<Funcionario> funcionarios = this.funcionarioRepository.findAll();
    return mapper.domainToResponseList(funcionarios);
  }

  @Override
  public FuncionarioResponseDTO getFuncionarioById(UUID id) {
    Funcionario funcionario = this.funcionarioRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com o ID: " + id));
    return mapper.domainToResponse(funcionario);
  }

  @Override
  public FuncionarioResponseDTO createFuncionario(FuncionarioRequestDTO funcionarioDTO) {
    Funcionario funcionario = mapper.requestToDomain(funcionarioDTO);

    Loja loja = lojaRepository.findById(funcionarioDTO.lojaId()) 
      .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada com o ID: " + funcionarioDTO.lojaId()));

    funcionario.setLoja(loja);
    funcionario.setSenha(passwordEncoder.encode(funcionario.getSenha()));
    funcionario.setCargo(UsuarioEnumCargos.EMPREGADO);

    JpaPermissaoEntity permissaoBase = this.permissaoRepository.findByNome("Basic") 
      .orElseThrow(() -> new EntityNotFoundException("Permissão base do funcionário não encontrada"));

    Permissao permissaoBasica = permissaoMapper.jpaToDomain(permissaoBase);
    
    funcionario.getPermissoes().add(permissaoBasica);
    
    Funcionario savedFuncionario = this.funcionarioRepository.save(funcionario);
    return mapper.domainToResponse(savedFuncionario);
  }

  @Override
  public FuncionarioResponseDTO updateFuncionario(UUID id, FuncionarioPatchDTO funcionarioDTO) {
    Funcionario funcionario = this.funcionarioRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com o ID: " + id));
    Funcionario funcionarioAtualizado = mapper.patchToDomain(funcionarioDTO, funcionario);
    
    Funcionario savedFuncionario = this.funcionarioRepository.save(funcionarioAtualizado);
    return mapper.domainToResponse(savedFuncionario);
  }

  @Override
  public void deleteFuncionario(UUID id) {
    this.funcionarioRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com o ID: " + id));
    this.funcionarioRepository.deleteById(id);
  }
  
}

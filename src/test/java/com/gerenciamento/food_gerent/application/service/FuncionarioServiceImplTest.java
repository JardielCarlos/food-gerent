package com.gerenciamento.food_gerent.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaPermissaoEntity;
import com.gerenciamento.food_gerent.adapters.outBound.repositories.funcionarios.FuncionarioRepositoryImpl;
import com.gerenciamento.food_gerent.adapters.outBound.repositories.lojas.LojaRepositoryImpl;
import com.gerenciamento.food_gerent.domain.funcionarios.Funcionario;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioPatchDTO;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioRequestDTO;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioResponseDTO;
import com.gerenciamento.food_gerent.domain.loja.Loja;
import com.gerenciamento.food_gerent.domain.permissoes.Permissao;
import com.gerenciamento.food_gerent.domain.permissoes.PermissaoRepository;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.factory.funcionarios.FuncionarioFactory;
import com.gerenciamento.food_gerent.factory.funcionarios.FuncionarioRequestFactory;
import com.gerenciamento.food_gerent.factory.funcionarios.FuncionarioResponseFactory;
import com.gerenciamento.food_gerent.factory.loja.LojaFactory;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.mappers.FuncionarioMapper;
import com.gerenciamento.food_gerent.utils.mappers.PermissaoMapper;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceImplTest {
  @Mock
  FuncionarioRepositoryImpl funcionarioRepository;

  @Mock
  PermissaoRepository permissaoRepository;

  @Mock
  LojaRepositoryImpl lojaRepository;

  @Mock
  FuncionarioMapper funcionarioMapper;

  @Mock
  PermissaoMapper permissaoMapper;

  @Mock 
  BCryptPasswordEncoder passwordEncoder;

  @InjectMocks
  FuncionarioServiceImpl funcionarioService;

  @Nested
  class getAllFuncionarios {
    @Test
    void deveRetornarFuncionarioComSucesso(){
      // Arrange
      List<FuncionarioResponseDTO> funcionarios = FuncionarioResponseFactory.buildList();
      List<Funcionario> funcionariosDomain = FuncionarioFactory.buildList();

      when(funcionarioRepository.findAll())
        .thenReturn(funcionariosDomain);

      when(funcionarioMapper.domainToResponseList(funcionariosDomain))
        .thenReturn(funcionarios);

      // Act
      List<FuncionarioResponseDTO> resultado = funcionarioService.getAllFuncionarios();

      // Assert
      verify(funcionarioRepository, times(1)).findAll();
      verify(funcionarioMapper, times(1)).domainToResponseList(funcionariosDomain);

      assertEquals(funcionarios.size(), resultado.size());
      assertEquals(FuncionarioResponseFactory.FUNCIONARIO_ID, resultado.get(0).id());
      assertNotNull(resultado.get(1).id());
      assertEquals(funcionarios.get(0).nome(), resultado.get(0).nome());
      assertEquals(funcionarios.get(1).nome(), resultado.get(1).nome()); 
    }

    @Test
    void deveRetornaListaVaziaSemResultado() {
      // Arrange
      when(funcionarioRepository.findAll())
        .thenReturn(List.of());

      when(funcionarioMapper.domainToResponseList(List.of()))
        .thenReturn(List.of());

      // Act
      List<FuncionarioResponseDTO> resultado = funcionarioService.getAllFuncionarios();

      // Assert
      verify(funcionarioRepository, times(1)).findAll();
      verify(funcionarioMapper, times(1)).domainToResponseList(List.of());

      assertTrue(resultado.isEmpty());
      assertEquals(0, resultado.size());
    }
  }
  @Nested
  class getFuncionarioById {
    @Test
    void deveRetornaBuscaPorIdComSucesso(){
      // Arrange
      FuncionarioResponseDTO funcionarioResponse = FuncionarioResponseFactory.build();

      Optional<Funcionario> funcionarioDomain = FuncionarioFactory.buildOptional();

      UUID funcionarioId = FuncionarioResponseFactory.FUNCIONARIO_ID;

      when(funcionarioRepository.findById(funcionarioId))
        .thenReturn(funcionarioDomain);

        when(funcionarioMapper.domainToResponse(funcionarioDomain.get()))
          .thenReturn(funcionarioResponse);

      // Act
      FuncionarioResponseDTO resultado = funcionarioService.getFuncionarioById(funcionarioId);

      // Assert

      verify(funcionarioRepository, times(1)).findById(funcionarioId);
      verify(funcionarioMapper, times(1)).domainToResponse(funcionarioDomain.get());

      assertEquals(FuncionarioResponseFactory.FUNCIONARIO_ID, resultado.id());
      assertEquals(funcionarioResponse.nome(), resultado.nome());
      assertNotNull(resultado);
    }

    @Test
    void deveRetornarErroQuandoNaoEncontrar(){
      // Arrange
      UUID funcionarioId = UUID.randomUUID();

      when(funcionarioRepository.findById(funcionarioId))
        .thenReturn(Optional.empty());

      // Act & Assert
      EntityNotFoundException exception = assertThrows(
        EntityNotFoundException.class, 
        () -> funcionarioService.getFuncionarioById(funcionarioId)
      );

      assertEquals("Funcionário não encontrado com o ID: " + funcionarioId, exception.getMessage());

      verify(funcionarioRepository, times(1)).findById(funcionarioId);
    }

    @Test
    void deveLancarExcecaoIdNull(){
      UUID funcionarioId = null;

      EntityNotFoundException exception = assertThrows(
        EntityNotFoundException.class, 
        () -> funcionarioService.getFuncionarioById(funcionarioId)
      );

      assertEquals("Funcionário não encontrado com o ID: " + funcionarioId, exception.getMessage());
      verify(funcionarioRepository, times(1)).findById(funcionarioId);
    }
  }

  @Nested
  class createFuncionario {
    @Test
    void deveCriarFuncionarioComSucesso() {
      // Arrange
      FuncionarioRequestDTO requestDTO = FuncionarioRequestFactory.build();
      Funcionario funcionarioDomain = FuncionarioFactory.build();
      Loja loja = LojaFactory.build();
      JpaPermissaoEntity permissaoEntity = new JpaPermissaoEntity(1L, "Basic");
      Permissao permissaoDomain = new Permissao(1L, "Basic");
      Funcionario savedFuncionarioDomain = FuncionarioFactory.build();
      FuncionarioResponseDTO responseDTO = FuncionarioResponseFactory.build();

      when(funcionarioMapper.requestToDomain(requestDTO))
        .thenReturn(funcionarioDomain);

      when(lojaRepository.findById(requestDTO.lojaId()))
        .thenReturn(Optional.of(loja));

      when(passwordEncoder.encode(anyString()))
        .thenReturn("encodedPassword");

      when(permissaoRepository.findByNome("Basic"))
        .thenReturn(Optional.of(permissaoEntity));

      when(permissaoMapper.jpaToDomain(permissaoEntity))
        .thenReturn(permissaoDomain);

      when(funcionarioRepository.save(funcionarioDomain))
        .thenReturn(savedFuncionarioDomain);

      when(funcionarioMapper.domainToResponse(savedFuncionarioDomain))
        .thenReturn(responseDTO);

      // Act
      FuncionarioResponseDTO resultado = funcionarioService.createFuncionario(requestDTO);

      // Assert
      assertNotNull(resultado);
      assertEquals(responseDTO.id(), resultado.id());
      assertEquals("Funcionario João", resultado.nome());
      assertEquals(UsuarioEnumCargos.EMPREGADO, funcionarioDomain.getCargo());
      assertEquals(EnumStatus.ATIVO, funcionarioDomain.getStatus());
      assertEquals(1, funcionarioDomain.getPermissoes().size());

      assertEquals("Loja A", funcionarioDomain.getLoja().getNome());
      assertTrue(funcionarioDomain.getPermissoes().contains(permissaoDomain));

      verify(funcionarioRepository, times(1)).save(funcionarioDomain);
      verify(passwordEncoder, times(1)).encode(anyString());
      verify(funcionarioMapper, times(1)).requestToDomain(requestDTO);
      verify(lojaRepository, times(1)).findById(requestDTO.lojaId());
      verify(permissaoMapper, times(1)).jpaToDomain(permissaoEntity);
      verify(permissaoRepository, times(1)).findByNome("Basic");
      verify(funcionarioMapper, times(1)).domainToResponse(savedFuncionarioDomain);
    }

    @Test
    void deveLancarExcecaoQuandoLojaNaoEncontrada(){
      // Arrange
      FuncionarioRequestDTO requestDTO = FuncionarioRequestFactory.build();
      Funcionario funcionarioDomain = FuncionarioFactory.build();

      when(funcionarioMapper.requestToDomain(requestDTO))
        .thenReturn(funcionarioDomain);

      when(lojaRepository.findById(requestDTO.lojaId()))
        .thenReturn(Optional.empty());

      // Act & Assert
      EntityNotFoundException exception = assertThrows(
        EntityNotFoundException.class,
        () -> funcionarioService.createFuncionario(requestDTO)
      );

      assertEquals("Loja não encontrada com o ID: " + requestDTO.lojaId(), exception.getMessage());

      verify(funcionarioMapper, times(1)).requestToDomain(requestDTO);
      verify(lojaRepository, times(1)).findById(requestDTO.lojaId());
      verify(passwordEncoder, times(0)).encode(anyString());
      verify(permissaoRepository, times(0)).findByNome(anyString());
      verify(funcionarioRepository, times(0)).save(any(Funcionario.class));
      verify(funcionarioMapper, times(0)).domainToResponse(any(Funcionario.class));
    }

    @Test
    void deveLancarExcecaoQuandoPermissaoBasicaNaoEncontrada(){
      // Arrange 
      FuncionarioRequestDTO requestDTO = FuncionarioRequestFactory.build();
      Funcionario funcionarioDomain = FuncionarioFactory.build();

      when(funcionarioMapper.requestToDomain(requestDTO))
        .thenReturn(funcionarioDomain);

      when(lojaRepository.findById(requestDTO.lojaId()))
        .thenReturn(Optional.of(LojaFactory.build()));

      when(passwordEncoder.encode(anyString()))
        .thenReturn("encodedPassword");

      when(permissaoRepository.findByNome("Basic"))
        .thenReturn(Optional.empty());

      // Act & Assert
      EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, 
        () -> funcionarioService.createFuncionario(requestDTO)
      );

      assertEquals("Permissão base do funcionário não encontrada", exception.getMessage());

      verify(funcionarioMapper, times(1)).requestToDomain(requestDTO);
      verify(lojaRepository, times(1)).findById(requestDTO.lojaId());
      verify(passwordEncoder, times(1)).encode(anyString());
      verify(permissaoRepository, times(1)).findByNome("Basic");
      verify(funcionarioRepository, times(0)).save(any(Funcionario.class));
      verify(funcionarioMapper, times(0)).domainToResponse(any(Funcionario.class));
    }
  }

  @Nested
  class updateFuncionario {
    @Test
    void deveAtualizarFuncionarioComSucesso() {
      UUID funcionarioId = FuncionarioResponseFactory.FUNCIONARIO_ID;
      Funcionario funcionarioDomain = FuncionarioFactory.build();  
      FuncionarioPatchDTO patchDTO = FuncionarioRequestFactory.buildPatch();
      Funcionario funcionarioAtualizadoDomain = FuncionarioFactory.buildUpdated();
      FuncionarioResponseDTO responseDTO = FuncionarioResponseFactory.buildUpdated();


      when(funcionarioRepository.findById(funcionarioId))
        .thenReturn(Optional.of(funcionarioDomain));

      when(funcionarioMapper.patchToDomain(patchDTO, funcionarioDomain))
        .thenReturn(funcionarioAtualizadoDomain);

      when(funcionarioRepository.save(funcionarioAtualizadoDomain))
        .thenReturn(funcionarioAtualizadoDomain);
        
      when(funcionarioMapper.domainToResponse(funcionarioAtualizadoDomain))
          .thenReturn(responseDTO);

      // Act
      FuncionarioResponseDTO resultado = funcionarioService.updateFuncionario(funcionarioId, patchDTO);

      // Assert
      assertNotNull(resultado);
      assertEquals(responseDTO.id(), resultado.id());
      assertEquals(patchDTO.nome(), funcionarioAtualizadoDomain.getNome());

      verify(funcionarioRepository, times(1)).findById(funcionarioId);
      verify(funcionarioMapper, times(1)).patchToDomain(patchDTO, funcionarioDomain);
      verify(funcionarioRepository, times(1)).save(funcionarioAtualizadoDomain);
      verify(funcionarioMapper, times(1)).domainToResponse(funcionarioAtualizadoDomain);
    }

    @Test
    void deveLancarExcecaoQuandoFuncionarioNaoEncontrado(){
      UUID funcionarioId = UUID.randomUUID();
      FuncionarioPatchDTO patchDTO = FuncionarioRequestFactory.buildPatch();

      when(funcionarioRepository.findById(funcionarioId))
        .thenReturn(Optional.empty());

      // Act & Assert
      EntityNotFoundException exception = assertThrows(
        EntityNotFoundException.class,
        () -> funcionarioService.updateFuncionario(funcionarioId, patchDTO)
      );

      assertEquals("Funcionário não encontrado com o ID: " + funcionarioId, exception.getMessage());

      verify(funcionarioRepository, times(1)).findById(funcionarioId);
      verify(funcionarioMapper, times(0)).patchToDomain(any(FuncionarioPatchDTO.class), any(Funcionario.class));
      verify(funcionarioRepository, times(0)).save(any(Funcionario.class));
      verify(funcionarioMapper, times(0)).domainToResponse(any(Funcionario.class));
    } 
  }

  @Nested
  class deleteFuncionario {
    @Test
    void deveDeletarFuncionarioComSucesso(){
      UUID funcionarioId = FuncionarioResponseFactory.FUNCIONARIO_ID;

      when(funcionarioRepository.findById(funcionarioId))
        .thenReturn(FuncionarioFactory.buildOptional());
      
      // Act & Assert 
      assertDoesNotThrow(() -> funcionarioService.deleteFuncionario(funcionarioId));

      verify(funcionarioRepository, times(1)).findById(funcionarioId);
      verify(funcionarioRepository, times(1)).deleteById(funcionarioId);
    }

    @Test
    void deveLancarExcecaoNaoEncontrarFuncionario(){
      UUID funcionarioId = UUID.randomUUID();

      when(funcionarioRepository.findById(funcionarioId))
        .thenReturn(Optional.empty());

      // Act & Assert
      EntityNotFoundException exception = assertThrows(
        EntityNotFoundException.class,
        () -> funcionarioService.deleteFuncionario(funcionarioId)
      );

      assertEquals("Funcionário não encontrado com o ID: " + funcionarioId, exception.getMessage());

      verify(funcionarioRepository, times(1)).findById(funcionarioId);
      verify(funcionarioRepository, times(0)).deleteById(funcionarioId);
    }
  }
}

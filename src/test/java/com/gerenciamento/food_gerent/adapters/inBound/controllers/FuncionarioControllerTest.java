package com.gerenciamento.food_gerent.adapters.inBound.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;

import com.gerenciamento.food_gerent.application.service.FuncionarioServiceImpl;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioPatchDTO;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioRequestDTO;
import com.gerenciamento.food_gerent.domain.funcionarios.FuncionarioResponseDTO;
import com.gerenciamento.food_gerent.factory.funcionarios.FuncionarioRequestFactory;
import com.gerenciamento.food_gerent.factory.funcionarios.FuncionarioResponseFactory;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.ApiExceptionHandler;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.ProblemDetails;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
public class FuncionarioControllerTest {

  @Mock
  FuncionarioServiceImpl funcionarioService;

  @Mock 
  HttpServletRequest request;
  
  @InjectMocks
  FuncionarioController funcionarioController;

  @InjectMocks
  ApiExceptionHandler apiExceptionHandler;
  
  @Nested
  class GetAllFuncionarios {
    @Test
    void deveRetornarListaDeFuncionariosComStatus200() {
      // Arrange
      List<FuncionarioResponseDTO> funcionarios = FuncionarioResponseFactory.buildList();

     when(funcionarioService.getAllFuncionarios()).thenReturn(funcionarios);
     
      // Act
     ResponseEntity<List<FuncionarioResponseDTO>> response = funcionarioController.getAllFuncionarios();

      // Assert
     assertEquals(200, response.getStatusCode().value());
     assertNotNull(response.getBody());
     assertEquals(2, response.getBody().size());
     assertEquals("Funcionario João", response.getBody().get(0).nome());
    }

    @Test
    void deveRetornarListaVaziaStatus200NaoHouverFuncionarios() {
      when(funcionarioService.getAllFuncionarios())
        .thenReturn(List.of());

      ResponseEntity<List<FuncionarioResponseDTO>> response = funcionarioController.getAllFuncionarios();

      assertEquals(200, response.getStatusCode().value());
      assertNotNull(response.getBody());
      assertTrue(response.getBody().isEmpty());
    }
  }

  @Nested 
  class getFuncionarioById {
    @Test
    void deveRetornaFuncionarioEncontrado() {
      // Arrange
      UUID funcionarioId = FuncionarioResponseFactory.FUNCIONARIO_ID;
      FuncionarioResponseDTO funcionarioResponse = FuncionarioResponseFactory.build();

      when(funcionarioService.getFuncionarioById(funcionarioId))
        .thenReturn(funcionarioResponse);

      // Act
      ResponseEntity<FuncionarioResponseDTO> response = funcionarioController.getFuncionarioById(funcionarioId);

      // Assert
      assertEquals(200, response.getStatusCode().value());
      assertNotNull(response.getBody());
      assertEquals("Funcionario João", response.getBody().nome());
    }

    @Test
    void deveLancarExcecaoFuncionarioNaoEncontrado(){
      // Arrange
      UUID funcionarioId = UUID.fromString("11111111-1111-1111-1111-111111111112");

      when(funcionarioService.getFuncionarioById(funcionarioId))
        .thenThrow(new EntityNotFoundException("Funcionário não encontrado com o ID: " + funcionarioId));

      // Act
      EntityNotFoundException exception = assertThrows(
        EntityNotFoundException.class,
        () -> funcionarioController.getFuncionarioById(funcionarioId)
      );

      // Assert
      assertEquals("Funcionário não encontrado com o ID: " + funcionarioId, exception.getMessage());
    }

    @Test
    void deveLancarExcecaoIdInvalido(){
      // Arrange
      UUID invalidId = UUID.fromString("00000000-0000-0000-0000-000000000000");
      
      when(funcionarioService.getFuncionarioById(invalidId))
        .thenThrow(new IllegalArgumentException("ID inválido: " + invalidId));

      // Act
      IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> funcionarioController.getFuncionarioById(invalidId)
      );

      // Assert
      assertEquals("ID inválido: " + invalidId, exception.getMessage());
    }
  }

  @Nested
  class createFuncionario {
    @Test
    void deveCriarFuncionarioComSucesso(){
      //Arrange
      FuncionarioRequestDTO funcionario = FuncionarioRequestFactory.build();
      FuncionarioResponseDTO funcionarioResponse = FuncionarioResponseFactory.build();

      when(funcionarioService.createFuncionario(funcionario))
        .thenReturn(funcionarioResponse);
      
      // Act
      ResponseEntity<FuncionarioResponseDTO> response = funcionarioController.createFuncionario(funcionario);

      // Assert
      verify(funcionarioService, times(1)).createFuncionario(funcionario);
      assertEquals(200, response.getStatusCode().value());
      assertNotNull(response.getBody());
      assertEquals("Funcionario João", response.getBody().nome());
      assertEquals("Loja A", response.getBody().loja().nome());
    }

    @Test
    void deveLancarExcecaoAoCriarFuncionarioComDadosInvalidos(){
      // Arrange
      FuncionarioRequestDTO invalidFuncionario = new FuncionarioRequestDTO(
        "", // nome inválido
        "invalid-email", // email inválido
        "123", // senha muito curta
        "invalid-cpf", // cpf inválido
        "invalid-phone", // telefone inválido
        null, // salario nulo
        null // lojaId nulo
      );

      when(funcionarioService.createFuncionario(invalidFuncionario)) 
        .thenThrow(new DataIntegrityViolationException("Violação de integridade detectada no banco de dados."));

      // Act
      DataIntegrityViolationException exception = assertThrows( 
        DataIntegrityViolationException.class, () -> funcionarioController.createFuncionario(invalidFuncionario) 
      );

      // Assert
     assertEquals("Violação de integridade detectada no banco de dados.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoAoCriarFuncionarioComLojaInexistente() {
      // Arrange
      FuncionarioRequestDTO funcionarioRequest = FuncionarioRequestFactory.build();

      // Simula que o service lança EntityNotFoundException
      when(funcionarioService.createFuncionario(funcionarioRequest))
        .thenThrow(new EntityNotFoundException("Loja não encontrada"));

      when(request.getRequestURI()).thenReturn("/funcionarios");

      // Act: chama o controller e captura a exceção
      EntityNotFoundException exception = assertThrows(
        EntityNotFoundException.class,
        () -> funcionarioController.createFuncionario(funcionarioRequest)
      );

      // Handler: transforma a exceção em resposta HTTP
      ResponseEntity<ProblemDetails> response = apiExceptionHandler.handleEntityNotFound(exception, request);

      // Assert: valida o ProblemDetails retornado
      assertEquals(404, response.getStatusCode().value());
      assertNotNull(response.getBody());
      assertEquals("Entidade não encontrada", response.getBody().getTitle());
      assertEquals("Loja não encontrada", response.getBody().getDetail());
      assertEquals("/funcionarios", response.getBody().getInstance());
    }

    @Test
    void deveLancarExcecaoAoCriarFuncionarioComCamposUnicosJaCadastrado(){
      // Arrange
      FuncionarioRequestDTO funcionarioRequest = FuncionarioRequestFactory.build();

      when(funcionarioService.createFuncionario(funcionarioRequest))
        .thenThrow(new DataIntegrityViolationException("Email já cadastrado"));

      when(request.getRequestURI()).thenReturn("/funcionarios");

      // Act
      DataIntegrityViolationException exception = assertThrows(
        DataIntegrityViolationException.class,
        () -> funcionarioController.createFuncionario(funcionarioRequest)
      );

      ResponseEntity<ProblemDetails> response = apiExceptionHandler.handleException(exception, request);

      // Assert
      assertEquals(400, response.getStatusCode().value());
      assertNotNull(response.getBody());
      assertEquals("Violação de integridade de campo", response.getBody().getTitle());
      assertEquals("Violação de integridade detectada no banco de dados.", response.getBody().getDetail());
      assertEquals("/funcionarios", response.getBody().getInstance());
    }
  }

  @Nested
  class updateFuncionario {
    @Test
    void deveAtualizarFuncionarioComSucesso(){
      // Arrange
      UUID funcionarioId = FuncionarioResponseFactory.FUNCIONARIO_ID;
      FuncionarioPatchDTO funcionario = FuncionarioRequestFactory.buildPatch();

      FuncionarioResponseDTO funcionarioResponse = FuncionarioResponseFactory.buildUpdated();

      when(funcionarioService.updateFuncionario(funcionarioId, funcionario))
        .thenReturn(funcionarioResponse);
      
      // Act
      ResponseEntity<FuncionarioResponseDTO> response = funcionarioController.updateFuncionario(funcionarioId, funcionario);

      // Assert
      verify(funcionarioService, times(1)).updateFuncionario(funcionarioId, funcionario);
      assertEquals(funcionarioResponse, response.getBody());
      assertEquals(200, response.getStatusCode().value());
      assertNotNull(response.getBody());
      assertEquals("Funcionario João Atualizado", response.getBody().nome());
    }

    @Test
    void deveLancarExcecaoAoAtualizarFuncionarioInexistente(){
      // Arrange
      UUID funcionarioId = UUID.fromString("11111111-1111-1111-1111-111111111112");
      FuncionarioPatchDTO funcionario = FuncionarioRequestFactory.buildPatch();

      when(funcionarioService.updateFuncionario(funcionarioId, funcionario))
        .thenThrow(new EntityNotFoundException("Funcionário não encontrado com o ID: " + funcionarioId));

      // Act
      when(request.getRequestURI()).thenReturn("/funcionarios");

      EntityNotFoundException exception = assertThrows(
        EntityNotFoundException.class,
        () -> funcionarioController.updateFuncionario(funcionarioId, funcionario)
      );

      ResponseEntity<ProblemDetails> response = apiExceptionHandler.handleEntityNotFound(exception, request);

      // Assert
      assertEquals(404, response.getStatusCode().value());
      assertNotNull(response.getBody());
      assertEquals("Entidade não encontrada", response.getBody().getTitle());
      assertEquals("Funcionário não encontrado com o ID: " + funcionarioId, response.getBody().getDetail());
      assertEquals("/funcionarios", response.getBody().getInstance());
    }

    @Test
    void deveLancarExcecaoAoAtualizarFuncionarioComDadosInvalidos(){
      // Arrange
      UUID funcionarioId = FuncionarioResponseFactory.FUNCIONARIO_ID;
      FuncionarioPatchDTO invalidFuncionario = new FuncionarioPatchDTO(
        "", // nome inválido
        "invalid-email", // email inválido
        null,
        null,
        "invalid-phone", // telefone inválido
        null,
        null,
        null
      );  
      
      when(funcionarioService.updateFuncionario(funcionarioId, invalidFuncionario))
        .thenThrow(new DataIntegrityViolationException("Violação de integridade detectada no banco de dados."));

      // Act
      DataIntegrityViolationException exception = assertThrows(
        DataIntegrityViolationException.class,
        () -> funcionarioController.updateFuncionario(funcionarioId, invalidFuncionario)
      );

      // Assert
      assertEquals("Violação de integridade detectada no banco de dados.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoAoAtualizarFuncionarioComCamposUnicosJaCadastrado(){
      // Arrange
      UUID funcionarioId = FuncionarioResponseFactory.FUNCIONARIO_ID;
      FuncionarioPatchDTO funcionario = FuncionarioRequestFactory.buildPatch();
      when(funcionarioService.updateFuncionario(funcionarioId, funcionario))
        .thenThrow(new DataIntegrityViolationException("Email já cadastrado"));

      when(request.getRequestURI()).thenReturn("/funcionarios");
      // Act
      DataIntegrityViolationException exception = assertThrows(
        DataIntegrityViolationException.class,
        () -> funcionarioController.updateFuncionario(funcionarioId, funcionario)
      );
      ResponseEntity<ProblemDetails> response = apiExceptionHandler.handleException(exception, request);

      // Assert
      assertEquals(400, response.getStatusCode().value());
      assertNotNull(response.getBody());
      assertEquals("Violação de integridade de campo", response.getBody().getTitle());
      assertEquals("Violação de integridade detectada no banco de dados.", response.getBody().getDetail());
      assertEquals("/funcionarios", response.getBody().getInstance());
    } 
  }

  @Nested
  class deleteFuncionario {
    @Test
    void deveDeletarFuncionarioComSucesso(){
      // Arrange
      UUID funcionarioId = FuncionarioResponseFactory.FUNCIONARIO_ID;

      doNothing().when(funcionarioService).deleteFuncionario(funcionarioId);

      // Act
      ResponseEntity<Void> response = funcionarioController.deleteFuncionario(funcionarioId);

      // Assert
      verify(funcionarioService, times(1)).deleteFuncionario(funcionarioId);
      assertEquals(204, response.getStatusCode().value());
      assertNull(response.getBody());
    }

    @Test
    void deveLancarExcecaoAoDeletarFuncionarioInexistente(){
      // Arrange
      UUID funcionarioId = UUID.fromString("11111111-1111-1111-1111-111111111112");

      doThrow(new EntityNotFoundException("Funcionário não encontrado com o ID: " + funcionarioId))
        .when(funcionarioService).deleteFuncionario(funcionarioId);

      // Act
      when(request.getRequestURI()).thenReturn("/funcionarios");

      EntityNotFoundException exception = assertThrows(
        EntityNotFoundException.class,
        () -> funcionarioController.deleteFuncionario(funcionarioId)
      );

      ResponseEntity<ProblemDetails> response = apiExceptionHandler.handleEntityNotFound(exception, request);

      // Assert
      assertEquals(404, response.getStatusCode().value());
      assertNotNull(response.getBody());
      assertEquals("Entidade não encontrada", response.getBody().getTitle());
      assertEquals("Funcionário não encontrado com o ID: " + funcionarioId, response.getBody().getDetail());
      assertEquals("/funcionarios", response.getBody().getInstance());
    }
  }
}

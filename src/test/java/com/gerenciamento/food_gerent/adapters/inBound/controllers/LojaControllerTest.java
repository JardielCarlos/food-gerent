package com.gerenciamento.food_gerent.adapters.inBound.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

import com.gerenciamento.food_gerent.application.service.LojaServiceImpl;
import com.gerenciamento.food_gerent.factory.LojaRequestFactory;
import com.gerenciamento.food_gerent.factory.LojaResponseFactory;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

@ExtendWith(MockitoExtension.class)
public class LojaControllerTest {
  
  @Mock
  LojaServiceImpl lojaService;
  
  @InjectMocks
  LojaController lojaController;
  
  @Captor
  ArgumentCaptor<UUID> uuidCaptor;
  
  @Nested
  class GetLojas {
    @Test
    void isReturnHttpOK() {
      // Arrange -> Prepara todos os mocks para a execução
      doReturn(LojaResponseFactory.createLojaResponseList())
        .when(lojaService)
        .getAllLojas();
      
      // Act -> Chama o método que está sendo testado
      var response = lojaController.getLojas();

      // Assert -> Verifica se o resultado está conforme o esperado
      assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    void isReturnResponseBodyCorrect() {
      // Arrange -> Prepara todos os mocks para a execução
      var expected = LojaResponseFactory.createLojaResponseList();
      doReturn(expected)
        .when(lojaService)
        .getAllLojas();

      // Act -> Chama o método que está sendo testado
      var response = lojaController.getLojas();

      // Assert -> Verifica se o resultado está conforme o esperado
      assertEquals(expected, response.getBody());
    }
  }

  @Nested
  class getLojaById {
    @Test
    void isReturnHttpOK() {
      // Arrange -> Prepara todos os mocks para a execução
      UUID lojaId = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");

      doReturn(LojaResponseFactory.createLojaResponse())
        .when(lojaService)
        .getLojaById(lojaId);
      
      // Act -> Chama o método que está sendo testado
      var response = lojaController.getLojaById(lojaId);

      // Assert -> Verifica se o resultado está conforme o esperado
      assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    void isPassCorrectParametersToService() {
      // Arrange -> Prepara todos os mocks para a execução
      UUID lojaId = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");

      doReturn(LojaResponseFactory.createLojaResponse())
        .when(lojaService)
        .getLojaById(uuidCaptor.capture());
      
      // Act -> Chama o método que está sendo testado
      var response = lojaController.getLojaById(lojaId);

      // Assert -> Verifica se o resultado está conforme o esperado
      assertEquals(uuidCaptor.getValue(), lojaId);
    }

     @Test
    void isReturnResponseBodyCorrect() {
      // Arrange -> Prepara todos os mocks para a execução
      UUID lojaId = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");
      
      UUID expectedId = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");
      UUID expectedIdEmpresa = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");
      String expectedNome = "Loja A";
      String expectedCnpj = "12.345.678/0001-90";
      String expectedRua = "Rua a";
      String expectedBairro = "Bairro A";
      String expectedCidade = "Cidade A";
      String expectedEstado = "PB";
      String expectedCep = "58000-000";
      String expectedTelefone = "(83) 99999-9999";
      EnumStatus expectedStatus = EnumStatus.ATIVO;
      LocalDate expectedDataCriacao = LocalDate.parse("2024-01-01");
      LocalDate expectedDataAtualizacao = LocalDate.parse("2024-06-01");


      doReturn(LojaResponseFactory.createLojaResponse())
        .when(lojaService)
        .getLojaById(lojaId);
      
      // Act -> Chama o método que está sendo testado
      var response = lojaController.getLojaById(lojaId);

      // Assert -> Verifica se o resultado está conforme o esperado
      assertNotNull(response);
      assertNotNull(response.getBody());

      assertEquals(expectedId, response.getBody().id());
      assertEquals(expectedIdEmpresa, response.getBody().idEmpresa());
      assertEquals(expectedNome, response.getBody().nome());
      assertEquals(expectedCnpj, response.getBody().cnpj());
      assertEquals(expectedRua, response.getBody().rua());
      assertEquals(expectedBairro, response.getBody().bairro());
      assertEquals(expectedCidade, response.getBody().cidade());
      assertEquals(expectedEstado, response.getBody().estado());
      assertEquals(expectedCep, response.getBody().cep());
      assertEquals(expectedTelefone, response.getBody().telefone());
      assertEquals(expectedStatus, response.getBody().status());
      assertEquals(expectedDataCriacao, response.getBody().dataCriacao());
      assertEquals(expectedDataAtualizacao, response.getBody().dataAtualizacao());
    }
  }

  @Nested
  class CreateLoja {
    @Test
    void isReturnHttpOK() {
      // Arrange -> Prepara todos os mocks para a execução
      var request = LojaRequestFactory.createLojaRequest();
      var expectedResponse = LojaResponseFactory.createLojaResponse();
      doReturn(expectedResponse)
        .when(lojaService)
        .createLoja(request);

      // Act -> Chama o método que está sendo testado
      var response = lojaController.createLoja(request);

      // Assert -> Verifica se o resultado está conforme o esperado
      assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    void isPassCorrectParametersToService() {
      // Arrange -> Prepara todos os mocks para a execução
      var request = LojaRequestFactory.createLojaRequest();
      var expectedResponse = LojaResponseFactory.createLojaResponse();

      doReturn(expectedResponse)
        .when(lojaService)
        .createLoja(request); 

      // Act -> Chama o método que está sendo testado
      var response = lojaController.createLoja(request);

      // Assert -> Verifica se o resultado está conforme o esperado
      verify(lojaService).createLoja(request);
      assertEquals(expectedResponse, response.getBody());
    }
  }

  // @Test
  // void testCreateLoja() {

  // }

  // @Test
  // void testDeleteLoja() {

  // }

  // @Test
  // void testGetLojaById() {

  // }


  // @Test
  // void testGetLojas() {
    
  // }

  // @Test
  // void testUpdateLoja() {

  // }
}

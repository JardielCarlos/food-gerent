package com.gerenciamento.food_gerent.adapters.inBound.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
import com.gerenciamento.food_gerent.domain.loja.LojaPatchDTO;
import com.gerenciamento.food_gerent.domain.loja.LojaRequestDTO;
import com.gerenciamento.food_gerent.domain.loja.LojaResponseDTO;
import com.gerenciamento.food_gerent.factory.loja.LojaRequestFactory;
import com.gerenciamento.food_gerent.factory.loja.LojaResponseFactory;
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
      lojaController.getLojaById(lojaId);

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

      ArgumentCaptor<LojaRequestDTO> captor = ArgumentCaptor.forClass(LojaRequestDTO.class);

      doReturn(expectedResponse)
        .when(lojaService)
        .createLoja(request); 

      // Act -> Chama o método que está sendo testado
      lojaController.createLoja(request);

      // Assert -> Verifica se o resultado está conforme o esperado
      verify(lojaService, times(1)).createLoja(captor.capture());
      var capturedValue = captor.getValue();

      assertEquals(request.nome(), capturedValue.nome());
      assertEquals(request.cnpj(), capturedValue.cnpj());
      assertEquals(request.rua(), capturedValue.rua());
      assertEquals(request.bairro(), capturedValue.bairro());
      assertEquals(request.cidade(), capturedValue.cidade());
      assertEquals(request.estado(), capturedValue.estado());
      assertEquals(request.cep(), capturedValue.cep());
      assertEquals(request.telefone(), capturedValue.telefone());
      assertEquals(request.idEmpresa(), capturedValue.idEmpresa());
    }

    @Test
    void isReturnResponseBodyCorrect() {
      // Arrange -> Prepara todos os mocks para a execução
      var request = LojaRequestFactory.createLojaRequest();
      var expectedResponse = LojaResponseFactory.createLojaResponse();

      doReturn(expectedResponse)
        .when(lojaService)
        .createLoja(request); 

      // Act -> Chama o método que está sendo testado
      var response = lojaController.createLoja(request);

      // Assert -> Verifica se o resultado está conforme o esperado
      assertEquals(expectedResponse, response.getBody());
      assertEquals(expectedResponse.id(), response.getBody().id());
      assertEquals(expectedResponse.idEmpresa(), response.getBody().idEmpresa());
      assertEquals(expectedResponse.cnpj(), response.getBody().cnpj());
      assertEquals(expectedResponse.nome(), response.getBody().nome());
      assertEquals(expectedResponse.rua(), response.getBody().rua());
      assertEquals(expectedResponse.bairro(), response.getBody().bairro());
      assertEquals(expectedResponse.cidade(), response.getBody().cidade());
      assertEquals(expectedResponse.estado(), response.getBody().estado());
      assertEquals(expectedResponse.cep(), response.getBody().cep());
      assertEquals(expectedResponse.telefone(), response.getBody().telefone());
      assertEquals(expectedResponse.status(), response.getBody().status());
      assertEquals(expectedResponse.dataCriacao(), response.getBody().dataCriacao());
      assertEquals(expectedResponse.dataAtualizacao(), response.getBody().dataAtualizacao());
    }
  }
  @Nested
  class UpdateLoja {
    @Test
    void isReturnHttpOK() {
      // Arrange -> Prepara todos os mocks para a execução
      UUID lojaId = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");

      UUID empresaId = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");

      LojaPatchDTO patchData = LojaRequestFactory.lojaPatchDTO();

      LojaResponseDTO expectedResponse = LojaResponseFactory.createLojaPatchDTO();

      doReturn(expectedResponse)
        .when(lojaService)
        .updateLoja(lojaId, empresaId, patchData);

        // Act -> Chama o método que está sendo testado
        var response = lojaController.updateLoja(lojaId, empresaId, patchData);

      // Assert -> Verifica se o resultado está conforme o esperado
      assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    void isPassCorrectParametersToService() {
      // Arrange -> Prepara todos os mocks para a execução
      UUID lojaId = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");

      UUID empresaId = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");

      LojaPatchDTO patchData = LojaRequestFactory.lojaPatchDTO();

      LojaResponseDTO expectedResponse = LojaResponseFactory.createLojaPatchDTO();

      doReturn(expectedResponse)
        .when(lojaService)
        .updateLoja(lojaId, empresaId, patchData);

      // Act -> Chama o método que está sendo testado
      var response = lojaController.updateLoja(lojaId, empresaId, patchData);

      // Assert -> Verifica se o resultado está conforme o esperado
      verify(lojaService).updateLoja(uuidCaptor.capture(), uuidCaptor.capture(), eq(patchData));

      var capturedValues = uuidCaptor.getAllValues();

      assertEquals(2, capturedValues.size());
      assertEquals(capturedValues.get(0), lojaId);
      assertEquals(capturedValues.get(1), empresaId);

      assertEquals(expectedResponse, response.getBody());
      assertEquals(expectedResponse.nome(), response.getBody().nome());
      assertEquals(expectedResponse.cnpj(), response.getBody().cnpj());
      assertEquals(expectedResponse.rua(), response.getBody().rua());
    }

    @Test
    void isReturnResponseBodyCorrect() {
      // Arrange -> Prepara todos os mocks para a execução
      UUID lojaId = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");

      UUID empresaId = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");

      LojaPatchDTO patchData = LojaRequestFactory.lojaPatchDTO();

      LojaResponseDTO expectedResponse = LojaResponseFactory.createLojaPatchDTO();

      doReturn(expectedResponse)
        .when(lojaService)
        .updateLoja(lojaId, empresaId, patchData);

        // Act -> Chama o método que está sendo testado
        var response = lojaController.updateLoja(lojaId, empresaId, patchData);

      // Assert -> Verifica se o resultado está conforme o esperado
      assertEquals(expectedResponse, response.getBody());
      assertEquals(expectedResponse.id(), response.getBody().id());
      assertEquals(expectedResponse.idEmpresa(), response.getBody().idEmpresa());
      assertEquals(expectedResponse.cnpj(), response.getBody().cnpj());
      assertEquals(expectedResponse.nome(), response.getBody().nome());
      assertEquals(expectedResponse.rua(), response.getBody().rua());
      assertEquals(expectedResponse.bairro(), response.getBody().bairro());
      assertEquals(expectedResponse.cidade(), response.getBody().cidade());
      assertEquals(expectedResponse.estado(), response.getBody().estado());
      assertEquals(expectedResponse.cep(), response.getBody().cep());
      assertEquals(expectedResponse.telefone(), response.getBody().telefone());
      assertEquals(expectedResponse.status(), response.getBody().status());
      assertEquals(expectedResponse.dataCriacao(), response.getBody().dataCriacao());
      assertEquals(expectedResponse.dataAtualizacao(), response.getBody().dataAtualizacao());
    }
  }
  @Nested
  class DeleteLoja {
    @Test
    void isReturnHttpNoContent() {
      // Arrange -> Prepara todos os mocks para a execução
      UUID lojaId = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");

      UUID empresaId = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");

      doNothing()
        .when(lojaService)
        .deleteLoja(lojaId, empresaId);

        // Act -> Chama o método que está sendo testado
        var response = lojaController.deleteLoja(lojaId, empresaId);

      // Assert -> Verifica se o resultado está conforme o esperado
      assertEquals(HttpStatusCode.valueOf(204), response.getStatusCode());
    }

    @Test
    void isPassCorrectParametersToService() {
      // Arrange -> Prepara todos os mocks para a execução
       UUID lojaId = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");

      UUID empresaId = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");

      doNothing()
        .when(lojaService)
        .deleteLoja(lojaId, empresaId);

      // Act -> Chama o método que está sendo testado
      lojaController.deleteLoja(lojaId, empresaId);

      // Assert -> Verifica se o resultado está conforme o esperado
      verify(lojaService).deleteLoja(uuidCaptor.capture(), uuidCaptor.capture());

      var capturedValues = uuidCaptor.getAllValues();
      assertEquals(2, capturedValues.size());
      assertEquals(capturedValues.get(0), lojaId);
      assertEquals(capturedValues.get(1), empresaId);
    }
  }
}

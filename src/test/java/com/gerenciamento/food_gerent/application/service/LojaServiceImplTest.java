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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gerenciamento.food_gerent.adapters.outBound.repositories.empresas.EmpresaRepositoryImpl;
import com.gerenciamento.food_gerent.adapters.outBound.repositories.lojas.LojaRepositoryImpl;
import com.gerenciamento.food_gerent.domain.empresas.Empresa;
import com.gerenciamento.food_gerent.domain.loja.Loja;
import com.gerenciamento.food_gerent.domain.loja.LojaPatchDTO;
import com.gerenciamento.food_gerent.domain.loja.LojaRequestDTO;
import com.gerenciamento.food_gerent.domain.loja.LojaResponseDTO;
import com.gerenciamento.food_gerent.factory.empresa.EmpresaFactory;
import com.gerenciamento.food_gerent.factory.loja.LojaFactory;
import com.gerenciamento.food_gerent.factory.loja.LojaRequestFactory;
import com.gerenciamento.food_gerent.factory.loja.LojaResponseFactory;
import com.gerenciamento.food_gerent.infrastructure.config.exceptions.EntityNotFoundException;
import com.gerenciamento.food_gerent.utils.mappers.LojaMapper;

@ExtendWith(MockitoExtension.class)
public class LojaServiceImplTest {

  @Mock
  LojaRepositoryImpl lojaRepositoryImpl;

  @Mock
  EmpresaRepositoryImpl empresaRepositoryImpl;

  @Mock
  LojaMapper lojaMapper;

  @InjectMocks
  LojaServiceImpl lojaServiceImpl;

  @Captor
  ArgumentCaptor<Loja> lojaCaptor;

  @Nested
  class createLoja {
    @Test
    void deveChamarRepositorySave() {
      // Arrange -> Preparação dos dados e mocks necessários para o teste
      LojaRequestDTO lojaRequest = LojaRequestFactory.createLojaRequest();
      
      Empresa empresa = EmpresaFactory.build();
      Loja lojaDomain = LojaFactory.build();

      when(empresaRepositoryImpl
        .findById(UUID.fromString(lojaRequest.idEmpresa()))) 
        .thenReturn(Optional.of(empresa));

      when(lojaMapper.requestToJpa(lojaRequest, empresa))
        .thenReturn(lojaDomain); 
      
      // Act -> Chamada do método que está sendo testado
      lojaServiceImpl.createLoja(lojaRequest);
      
      // Assert -> Verificação dos resultados esperados
      verify(lojaRepositoryImpl, times(1)).save(any());
    }

    @Test
    void deveConverterRequestEmDominioComSucesso() {
      // Arrange -> Preparação dos dados e mocks necessários para o teste
      LojaRequestDTO lojaRequest = LojaRequestFactory.createLojaRequest();
      
      Empresa empresa = EmpresaFactory.build();
      Loja lojaDomain = LojaFactory.build();

      when(empresaRepositoryImpl
        .findById(UUID.fromString(lojaRequest.idEmpresa()))) 
        .thenReturn(Optional.of(empresa));

      when(lojaMapper.requestToJpa(lojaRequest, empresa))
        .thenReturn(lojaDomain); 
      
      // Act -> Chamada do método que está sendo testado
      lojaServiceImpl.createLoja(lojaRequest);
      
      // Assert -> Verificação dos resultados esperados
      verify(lojaRepositoryImpl, times(1)).save(lojaCaptor.capture());
      Loja entity = lojaCaptor.getValue();

      assertEquals(lojaDomain, entity);
      assertEquals(lojaDomain.getId(), entity.getId());
      assertEquals(lojaDomain.getIdEmpresa(), entity.getIdEmpresa());
      assertEquals(lojaDomain.getNome(), entity.getNome());
      assertEquals(lojaDomain.getCnpj(), entity.getCnpj());
      assertEquals(lojaDomain.getRua(), entity.getRua());
      assertEquals(lojaDomain.getBairro(), entity.getBairro());
      assertEquals(lojaDomain.getCidade(), entity.getCidade());
      assertEquals(lojaDomain.getEstado(), entity.getEstado());
      assertEquals(lojaDomain.getCep(), entity.getCep());
      assertEquals(lojaDomain.getStatus(), entity.getStatus());
      assertEquals(lojaDomain.getTelefone(), entity.getTelefone());
      assertEquals(lojaDomain.getDataCriacao(), entity.getDataCriacao());
      assertEquals(lojaDomain.getDataAtualizacao(), entity.getDataAtualizacao());
    }

    @Test
    void deveConverteDominioEmResponseDTOComSucesso() {
      // Arrange -> Preparação dos dados e mocks necessários para o teste
      LojaRequestDTO lojaRequest = LojaRequestFactory.createLojaRequest();
      LojaResponseDTO lojaResponse = LojaResponseFactory.createLojaResponse();

      Empresa empresa = EmpresaFactory.build();
      Loja lojaDomain = LojaFactory.build();

      when(empresaRepositoryImpl.findById(UUID.fromString(lojaRequest.idEmpresa())))
        .thenReturn(Optional.of(empresa));

      when(lojaMapper.requestToJpa(lojaRequest, empresa))
        .thenReturn(lojaDomain);

      when(lojaRepositoryImpl.save(lojaDomain))
        .thenReturn(lojaDomain);

      when(lojaMapper.toResponseDTO(lojaDomain))
        .thenReturn(lojaResponse);  

      // Act -> Chamada do método que está sendo testado
      LojaResponseDTO result = lojaServiceImpl.createLoja(lojaRequest);
      
      // Assert -> Verificação dos resultados esperados
      assertNotNull(result);
      assertEquals(lojaResponse, result);
      assertEquals(lojaResponse.id(), result.id());
      assertEquals(lojaResponse.idEmpresa(), result.idEmpresa());
      assertEquals(lojaResponse.nome(), result.nome());
      assertEquals(lojaResponse.cnpj(), result.cnpj());
      assertEquals(lojaResponse.rua(), result.rua());
      assertEquals(lojaResponse.bairro(), result.bairro());
      assertEquals(lojaResponse.cidade(), result.cidade());
      assertEquals(lojaResponse.estado(), result.estado());
      assertEquals(lojaResponse.cep(), result.cep());
      assertEquals(lojaResponse.telefone(), result.telefone());
      assertEquals(lojaResponse.status(), result.status());
      assertEquals(lojaResponse.dataCriacao(), result.dataCriacao());
      assertEquals(lojaResponse.dataAtualizacao(), result.dataAtualizacao());
    }
  }

  @Nested
  class getLojaById {
    @Test
    void deveChamarRepository(){
      // Arrange -> Preparação dos dados e mocks necessários para o teste
      UUID lojaId = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");
      Loja lojaDomain = LojaFactory.build();
      LojaResponseDTO lojaResponse = LojaResponseFactory.createLojaResponse();
      
      when(lojaRepositoryImpl.findById(eq(lojaId))) 
        .thenReturn(Optional.of(lojaDomain));

      // Act -> Chamada do método que está sendo testado
      lojaServiceImpl.getLojaById(lojaId);

      // Assert -> Verificação dos resultados esperados
      verify(lojaRepositoryImpl, times(1)).findById(eq(lojaId));
    }

    @Test
    void deveMapearResponseDTO(){
      // Arrange -> Preparação dos dados e mocks necessários para o teste
      UUID lojaId = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");
      Loja lojaDomain = LojaFactory.build();
      LojaResponseDTO lojaResponse = LojaResponseFactory.createLojaResponse();

      when(lojaRepositoryImpl.findById(eq(lojaId))) 
        .thenReturn(Optional.of(lojaDomain));
      when(lojaMapper.toResponseDTO(lojaDomain)) 
        .thenReturn(lojaResponse);

      // Act -> Chamada do método que está sendo testado
      LojaResponseDTO result = lojaServiceImpl.getLojaById(lojaId);

      // Assert -> Verificação dos resultados esperados
      assertEquals(lojaResponse, result);
      assertEquals(lojaResponse.id(), result.id());
      assertEquals(lojaResponse.idEmpresa(), result.idEmpresa());
      assertEquals(lojaResponse.nome(), result.nome());
      assertEquals(lojaResponse.cnpj(), result.cnpj());
      assertEquals(lojaResponse.rua(), result.rua());
      assertEquals(lojaResponse.bairro(), result.bairro());
      assertEquals(lojaResponse.cidade(), result.cidade());
      assertEquals(lojaResponse.estado(), result.estado());
      assertEquals(lojaResponse.cep(), result.cep());
      assertEquals(lojaResponse.telefone(), result.telefone());
      assertEquals(lojaResponse.status(), result.status());
      assertEquals(lojaResponse.dataCriacao(), result.dataCriacao());
      assertEquals(lojaResponse.dataAtualizacao(), result.dataAtualizacao());
    }

    @Test
    void deveLancarExcecaoQuandoLojaNaoExiste() {
      // Arrange
      UUID lojaId = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");
      when(lojaRepositoryImpl.findById(eq(lojaId)))
        .thenReturn(Optional.empty());

      // Act
      assertThrows(EntityNotFoundException.class,
        () -> lojaServiceImpl.getLojaById(lojaId));
      
      // Assert
      verify(lojaRepositoryImpl, times(1)).findById(eq(lojaId));
      verify(lojaMapper, never()).toResponseDTO(any());
    }
  }

  @Nested
  class getAllLojas {
    @Test
    void deveRetornarResponse(){
      // Arrange -> Preparação dos dados e mocks necessários para o teste
      List<Loja> lojasDomain = LojaFactory.buildList();
      List<LojaResponseDTO> lojasResponse = LojaResponseFactory.createLojaResponseList();

      when(lojaRepositoryImpl.findAll())
        .thenReturn(lojasDomain);
      when(lojaMapper.toResponseDTOList(lojasDomain))
        .thenReturn(LojaResponseFactory.createLojaResponseList());

      // Act -> Chamada do método que está sendo testado
      List<LojaResponseDTO> result = lojaServiceImpl.getAllLojas();

      // Assert -> Verificação dos resultados esperados
      assertNotNull(result);
      assertEquals(lojasResponse, result);
      assertEquals(lojasResponse.size(), result.size());

      verify(lojaRepositoryImpl, times(1)).findAll(); 
      verify(lojaMapper, times(1)).toResponseDTOList(lojasDomain);
    }
    @Test
    void deveRetornarArrayVazio(){
      // Arrange -> Preparação dos dados e mocks necessários para o teste
      List<Loja> lojasDomain = List.of();
  
      when(lojaRepositoryImpl.findAll())
        .thenReturn(lojasDomain);
      when(lojaMapper.toResponseDTOList(lojasDomain))
        .thenReturn(List.of());
  
      // Act -> Chamada do método que está sendo testado
      List<LojaResponseDTO> result = lojaServiceImpl.getAllLojas();
  
      // Assert -> Verificação dos resultados esperados
      assertNotNull(result);
      assertEquals(0, result.size());
  
      verify(lojaRepositoryImpl, times(1)).findAll(); 
      verify(lojaMapper, times(1)).toResponseDTOList(lojasDomain);
    }
  }

  @Nested
  class updateLoja {
    @Test
    void deveChamarRepositorySave() {
      // Arrange
      UUID idLoja = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");
      UUID idEmpresa = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");
      LojaPatchDTO lojaPatchDTO = LojaRequestFactory.lojaPatchDTO();
      
      Empresa empresa = EmpresaFactory.build();
      Loja lojaExistente = LojaFactory.build();
      Loja lojaAtualizada = LojaFactory.build();

      // Adiciona a loja existente na lista da empresa
      empresa.getLojas().add(lojaExistente);

      when(empresaRepositoryImpl.findById(idEmpresa))
        .thenReturn(Optional.of(empresa));

      when(lojaMapper.updateLojaEntityFromPatchDto(lojaPatchDTO, lojaExistente))
        .thenReturn(lojaAtualizada);

      when(lojaRepositoryImpl.save(lojaAtualizada))
        .thenReturn(lojaAtualizada);

      // Act
      lojaServiceImpl.updateLoja(idLoja, idEmpresa, lojaPatchDTO);

      // Assert
      verify(lojaRepositoryImpl, times(1)).save(lojaAtualizada);
    }


    @Test
    void deveAtualizarLojaComSucesso() {
      // Arrange
      UUID idLoja = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");
      UUID idEmpresa = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");
      LojaPatchDTO lojaPatchDTO = LojaRequestFactory.lojaPatchDTO();

      Empresa empresa = EmpresaFactory.build();
      Loja lojaExistente = LojaFactory.build();
      Loja lojaAtualizada = LojaFactory.build();
      LojaResponseDTO lojaResponseDTO = LojaResponseFactory.createLojaPatchDTO();

      lojaAtualizada.setNome("Nova Loja"); // simulando atualização

      // adiciona a loja existente na empresa
      empresa.getLojas().add(lojaExistente);

      when(empresaRepositoryImpl.findById(idEmpresa))
        .thenReturn(Optional.of(empresa));

      when(lojaMapper.updateLojaEntityFromPatchDto(lojaPatchDTO, lojaExistente))
        .thenReturn(lojaAtualizada);

      when(lojaRepositoryImpl.save(lojaAtualizada))
        .thenReturn(lojaAtualizada);

      when(lojaMapper.toResponseDTO(lojaAtualizada))
        .thenReturn(lojaResponseDTO);

      // Act
      LojaResponseDTO response = lojaServiceImpl.updateLoja(idLoja, idEmpresa, lojaPatchDTO);

      // Assert
      assertNotNull(response);
      assertEquals("Nova Loja", response.nome());
      verify(lojaRepositoryImpl, times(1)).save(lojaAtualizada);
    }


    @Test
    void deveLancarExcecaoQuandoEmpresaNaoExistir() {
      // Arrange
      UUID idLoja = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");
      UUID idEmpresa = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");
      LojaPatchDTO lojaPatchDTO = LojaRequestFactory.lojaPatchDTO();

      when(empresaRepositoryImpl.findById(idEmpresa))
        .thenReturn(Optional.empty());

      // Act + Assert
      assertThrows(EntityNotFoundException.class,
        () -> lojaServiceImpl.updateLoja(idLoja, idEmpresa, lojaPatchDTO));

      verify(lojaRepositoryImpl, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoLojaNaoExistir() {
      // Arrange
      UUID idLoja = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");
      UUID idEmpresa = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");
      LojaPatchDTO lojaPatchDTO = LojaRequestFactory.lojaPatchDTO();

      Empresa empresa = EmpresaFactory.build();
      // empresa sem lojas

      when(empresaRepositoryImpl.findById(idEmpresa))
        .thenReturn(Optional.of(empresa));

      // Act + Assert
      assertThrows(EntityNotFoundException.class,
        () -> lojaServiceImpl.updateLoja(idLoja, idEmpresa, lojaPatchDTO));

      verify(lojaRepositoryImpl, never()).save(any());
    }

    @Test
    void deveRetornarLojaAtualizada() {
      // Arrange
      UUID idLoja = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");
      UUID idEmpresa = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");
      LojaPatchDTO lojaPatchDTO = LojaRequestFactory.lojaPatchDTO();

      Empresa empresa = EmpresaFactory.build();
      Loja lojaExistente = LojaFactory.build();
      Loja lojaAtualizada = LojaFactory.build();
      LojaResponseDTO lojaResponseDTO = LojaResponseFactory.createLojaPatchDTO();

      lojaAtualizada.setNome("Nova Loja"); // simulando atualização

      // adiciona a loja existente na empresa
      empresa.getLojas().add(lojaExistente);

      when(empresaRepositoryImpl.findById(idEmpresa))
        .thenReturn(Optional.of(empresa));

      when(lojaMapper.updateLojaEntityFromPatchDto(lojaPatchDTO, lojaExistente))
        .thenReturn(lojaAtualizada);

      when(lojaRepositoryImpl.save(lojaAtualizada))
        .thenReturn(lojaAtualizada);

      // Simula o mapper convertendo para DTO
      when(lojaMapper.toResponseDTO(lojaAtualizada))
        .thenReturn(lojaResponseDTO);

      // Act
      LojaResponseDTO response = lojaServiceImpl.updateLoja(idLoja, idEmpresa, lojaPatchDTO);

      // Assert
      assertNotNull(response);
      assertEquals("Nova Loja", response.nome());
      assertEquals(lojaAtualizada.getId(), response.id());
      verify(lojaRepositoryImpl, times(1)).save(lojaAtualizada);
    }


    @Test
    void deveAtualizarApenasCamposPresentesNoPatchDTO() {
      // Arrange
      UUID idLoja = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");
      UUID idEmpresa = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");

      // Patch com apenas o nome alterado
      LojaPatchDTO patchDTO = LojaRequestFactory.lojaPatchDTO();
      LojaResponseDTO lojaResponseDTO = LojaResponseFactory.createLojaPatchDTO();

      Empresa empresa = EmpresaFactory.build();
      Loja lojaExistente = LojaFactory.build();
      Loja lojaAtualizada = LojaFactory.build();
      lojaAtualizada.setNome("Nova Loja"); // simula atualização só do nome

      empresa.getLojas().add(lojaExistente);

      when(empresaRepositoryImpl.findById(idEmpresa))
        .thenReturn(Optional.of(empresa));

      when(lojaMapper.updateLojaEntityFromPatchDto(patchDTO, lojaExistente))
        .thenReturn(lojaAtualizada);

      when(lojaRepositoryImpl.save(lojaAtualizada))
        .thenReturn(lojaAtualizada);

      when(lojaMapper.toResponseDTO(lojaAtualizada))
        .thenReturn(lojaResponseDTO);

      // Act
      LojaResponseDTO response = lojaServiceImpl.updateLoja(idLoja, idEmpresa, patchDTO);

      // Assert
      assertNotNull(response);
      assertEquals("Nova Loja", response.nome()); // campo atualizado
      assertEquals(lojaExistente.getCnpj(), lojaAtualizada.getCnpj()); // campo não alterado
      assertEquals(lojaExistente.getBairro(), lojaAtualizada.getBairro()); // campo não alterado

      verify(lojaRepositoryImpl, times(1)).save(lojaAtualizada);
    }
  }
  @Nested
  class deleteLoja {
    @Test
    void deveDeletarLojaComSucesso() {
      // Arrange
      UUID idLoja = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");
      UUID idEmpresa = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");

      Empresa empresa = EmpresaFactory.build();
      Loja lojaExistente = LojaFactory.build();
      empresa.getLojas().add(lojaExistente);

      when(empresaRepositoryImpl.findById(idEmpresa))
        .thenReturn(Optional.of(empresa));

      // Act
      lojaServiceImpl.deleteLoja(idLoja, idEmpresa);

      // Assert
      assertFalse(empresa.getLojas().contains(lojaExistente));
      verify(empresaRepositoryImpl, times(1)).save(empresa);
    }

    @Test
    void deveLancarExcecaoQuandoEmpresaNaoExistir(){
      UUID idLoja = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");
      UUID idEmpresa = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");

      when(empresaRepositoryImpl.findById(idEmpresa))
        .thenReturn(Optional.empty());

      // Act + Assert
      assertThrows(EntityNotFoundException.class,
        () -> lojaServiceImpl.deleteLoja(idLoja, idEmpresa));

      verify(empresaRepositoryImpl, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoLojaNaoExistir(){
      //Arrange
      UUID idLoja = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");
      UUID idEmpresa = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");

      Empresa empresa = EmpresaFactory.build();
      // empresa sem lojas

      when(empresaRepositoryImpl.findById(idEmpresa))
        .thenReturn(Optional.of(empresa));

      // Act + Assert
      assertThrows(EntityNotFoundException.class,
        () -> lojaServiceImpl.deleteLoja(idLoja, idEmpresa));

      verify(empresaRepositoryImpl, never()).save(any());
    }

    void deveLancarExcecaoQuandoLojaNaoExistirNaEmpresa(){
      //Arrange
      UUID idLoja = UUID.fromString("d59c7955-6777-4ca4-975f-c90f0cc33eac");
      UUID idEmpresa = UUID.fromString("6fe8dc87-623e-4b8e-b713-a754bc2ee14c");

      Empresa empresa = EmpresaFactory.build();
      Loja lojaExistente = LojaFactory.build();

      empresa.getLojas().add(lojaExistente);

      when(empresaRepositoryImpl.findById(idEmpresa))
        .thenReturn(Optional.of(empresa));

      // Act + Assert
      assertThrows(EntityNotFoundException.class,
        () -> lojaServiceImpl.deleteLoja(UUID.fromString("f1e2d3c4-b5a6-78b9-0c1d-2e3f4a5b6c78"), idEmpresa));

      verify(empresaRepositoryImpl, never()).save(any());
    }
  }
}

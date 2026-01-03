package com.gerenciamento.food_gerent.infrastructure.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaClienteEntity;
import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaPermissaoEntity;
import com.gerenciamento.food_gerent.domain.clientes.ClienteRepository;
import com.gerenciamento.food_gerent.domain.permissoes.PermissaoRepository;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AdminUserConfig implements CommandLineRunner {
 
  private final ClienteRepository clienteRepository;
  private final PermissaoRepository permissaoRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    permissaoRepository.findByNome("Basic")
      .orElseGet(() -> {
          JpaPermissaoEntity novaPermissao = new JpaPermissaoEntity();
          novaPermissao.setNome("Basic");
          return permissaoRepository.save(novaPermissao);
      });

    // Verifica ou cria a permissão
    JpaPermissaoEntity permissao = permissaoRepository.findByNome("Admin")
      .orElseGet(() -> {
          JpaPermissaoEntity novaPermissao = new JpaPermissaoEntity();
          novaPermissao.setNome("Admin");
          return permissaoRepository.save(novaPermissao);
      });

    // Verifica ou cria o cliente
    JpaClienteEntity cliente = clienteRepository.findByNome("Admin")
      .orElseGet(() -> {
          JpaClienteEntity novoCliente = new JpaClienteEntity();
          novoCliente.setNome("Admin");
          novoCliente.setEmail("admin@example.com");
          novoCliente.setSenha(passwordEncoder.encode("1234"));
          novoCliente.setCpf("000.000.000-00");
          novoCliente.setCargo(UsuarioEnumCargos.DONO);
          novoCliente.setStatus(EnumStatus.ATIVO);
          return novoCliente;
      });

    // Garante que o cliente tenha a permissão associada
    cliente.setPermissoes(new HashSet<>(Set.of(permissao)));
    clienteRepository.save(cliente);
  }
  
}
package com.gerenciamento.food_gerent.factory.cliente;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.clientes.Cliente;
import com.gerenciamento.food_gerent.domain.usuarios.UsuarioEnumCargos;
import com.gerenciamento.food_gerent.factory.permissao.PermissaoFactory;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public class ClienteFactory {
    public static Cliente build(){
      UUID idCliente = UUID.fromString("d290f1ee-6c54-4b01-90e6-d701748f0851");
      return new Cliente(
       idCliente,
        "Cliente Teste",
        "emailTeste@gmail.com",
        "123.456.789-00",
        "12345678",
        UsuarioEnumCargos.DONO,
        EnumStatus.ATIVO,
        LocalDate.parse("2024-01-01"),
        LocalDate.parse("2024-06-01"),
        "(83) 91234-5678",
        Set.of(PermissaoFactory.build())
      );
    }

}

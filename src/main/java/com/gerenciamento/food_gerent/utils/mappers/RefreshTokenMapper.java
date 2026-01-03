package com.gerenciamento.food_gerent.utils.mappers;

import java.time.Instant;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaRefreshTokenEntity;
import com.gerenciamento.food_gerent.domain.refreshTokens.RefreshToken;
import com.gerenciamento.food_gerent.domain.usuarios.Usuario;

@Mapper(componentModel = "spring")
public interface RefreshTokenMapper {

  @Mapping(target = "token", source = "token")
  @Mapping(target = "usuario", source = "usuario")
  @Mapping(target = "expiryDate", source = "expiryDate")
  JpaRefreshTokenEntity toDomain(RefreshToken refreshToken);


  @Mapping(target = "id", source = "id")
  @Mapping(target = "token", source = "token")
  @Mapping(target = "usuario", source = "usuario")
  @Mapping(target = "expiryDate", source = "expiryDate")
  RefreshToken jpaToDomain(JpaRefreshTokenEntity savedToken);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "token", source = "tokenValue")
  @Mapping(target = "usuario", source = "usuario")
  @Mapping(target = "expiryDate", source = "expiryDate")
  RefreshToken toEntity(String tokenValue, Usuario usuario, Instant expiryDate);


}

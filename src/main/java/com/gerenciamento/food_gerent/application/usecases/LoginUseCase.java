package com.gerenciamento.food_gerent.application.usecases;

import com.gerenciamento.food_gerent.domain.auth.LoginRequestDTO;
import com.gerenciamento.food_gerent.domain.auth.LoginResponseDTO;

public interface LoginUseCase {

  LoginResponseDTO login(LoginRequestDTO login);

}

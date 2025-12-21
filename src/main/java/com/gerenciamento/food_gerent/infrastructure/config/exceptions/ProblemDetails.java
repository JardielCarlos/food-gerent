package com.gerenciamento.food_gerent.infrastructure.config.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDetails {
    private String title;
    private Integer code;
    private String status;
    private String detail;
    private String instance;
}
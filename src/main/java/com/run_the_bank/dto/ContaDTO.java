package com.run_the_bank.dto;

import com.run_the_bank.model.Conta;
import lombok.Data;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import com.run_the_bank.dto.ClienteDTO;
@Data
public class ContaDTO {

    private Long id;

    @NotBlank
    private String agencia;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal saldo;

    @NotNull
    private Conta.StatusConta status;

    private ClienteDTO cliente;
}

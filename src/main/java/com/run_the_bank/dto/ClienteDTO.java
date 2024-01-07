package com.run_the_bank.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ClienteDTO {

    private Long id;

    @NotEmpty
    @NotNull
    @Size(min = 5, max = 50, message = "O tamanho do nome deve estar entre 2 e 50 caracteres.")
    private String nome;

    @NotBlank(message = "Este campo cpfCnpj não deve estar em branco ")
    @NotEmpty
    @NotNull
    private String cpfCnpj; // Pode ser CPF ou CNPJ

    @NotEmpty
    @NotNull
    private String endereco;

    @NotNull
    @Size(min = 4, max = 8, message = "A senha deve conter de 4 a 8 caracteres. ")
    private String senha;

//    @NotNull
//    private Cliente.TipoCliente tipoCliente;

    @NotNull
    private boolean status;

}

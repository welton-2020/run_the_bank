package com.run_the_bank.service.impl;

import com.run_the_bank.dto.ClienteDTO;
import com.run_the_bank.model.Cliente;
import com.run_the_bank.repository.ClienteRepository;
import com.run_the_bank.utilitario.ValidaCNPJ;
import com.run_the_bank.utilitario.ValidaCPF;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private ClienteServiceImpl clienteService;

        @Test
    void createClienteWithExistingCpfCnpj() {

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpfCnpj("56657053061"); // Um CPF válido para o teste
        clienteDTO.setNome("Nome do Cliente");

        when(repository.existsByCpfCnpj(clienteDTO.getCpfCnpj())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> clienteService.create(clienteDTO));
    }

    @Test
    void createInvalidCpfCnpj() {
        // Arrange
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpfCnpj("invalid_cpf_cnpj");
        clienteDTO.setNome("Nome do Cliente");

        assertThrows(RuntimeException.class, () -> clienteService.create(clienteDTO));
    }

}

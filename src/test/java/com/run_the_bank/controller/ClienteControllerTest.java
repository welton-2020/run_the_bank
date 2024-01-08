package com.run_the_bank.controller;

import com.run_the_bank.dto.ClienteDTO;
import com.run_the_bank.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    @Test
    public void testCreate() {
        // Arrange
        ClienteDTO request = new ClienteDTO();
        when(clienteService.create(request)).thenReturn(Optional.of(request));

        ResponseEntity<ClienteDTO> responseEntity = clienteController.create(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(request, responseEntity.getBody());
    }

    @Test
    public void testCreateBadRequest() {

        ClienteDTO request = new ClienteDTO();
        when(clienteService.create(request)).thenReturn(Optional.empty());

        ResponseEntity<ClienteDTO> responseEntity = clienteController.create(request);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testGetAll() {
        // Arrange
        List<ClienteDTO> clientes = Arrays.asList(new ClienteDTO(), new ClienteDTO());
        when(clienteService.getAll()).thenReturn(clientes);

        ResponseEntity<List<ClienteDTO>> responseEntity = clienteController.getAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clientes, responseEntity.getBody());
    }

    @Test
    public void testGetById() {

        Long id = 1L;
        ClienteDTO cliente = new ClienteDTO();
        when(clienteService.findById(id)).thenReturn(Optional.of(cliente));

        ResponseEntity<ClienteDTO> responseEntity = clienteController.getById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(cliente, responseEntity.getBody());
    }

    @Test
    public void testGetByIdNotFound() {

        Long id = 1L;
        when(clienteService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<ClienteDTO> responseEntity = clienteController.getById(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

}

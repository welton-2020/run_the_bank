package com.run_the_bank.controller;

import com.run_the_bank.dto.ContaDTO;
import com.run_the_bank.service.ContaService;
import com.run_the_bank.service.MovimentacaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContaControllerTest {

    @InjectMocks
    private ContaController contaController;

    @Mock
    private ContaService contaService;

    @Mock
    private MovimentacaoService movimentacaoService;

    @Test
    public void testCreate() {

        ContaDTO request = new ContaDTO();
        when(contaService.create(request)).thenReturn(Optional.of(request));


        ResponseEntity<ContaDTO> responseEntity = contaController.create(request);


        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(request, responseEntity.getBody());
    }

    @Test
    public void testCreateBadRequest() {

        ContaDTO request = new ContaDTO();
        when(contaService.create(request)).thenReturn(Optional.empty());


        ResponseEntity<ContaDTO> responseEntity = contaController.create(request);


        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testGetAll() {

        List<ContaDTO> contas = Arrays.asList(new ContaDTO(), new ContaDTO());
        when(contaService.getAll()).thenReturn(contas);


        ResponseEntity<List<ContaDTO>> responseEntity = contaController.getAll();


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(contas, responseEntity.getBody());
    }

    @Test
    public void testGetById() {

        Long id = 1L;
        ContaDTO conta = new ContaDTO();
        when(contaService.findById(id)).thenReturn(Optional.of(conta));

        ResponseEntity<ContaDTO> responseEntity = contaController.getById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(conta, responseEntity.getBody());
    }

    @Test
    public void testGetByIdNotFound() {
        Long id = 1L;
        when(contaService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<ContaDTO> responseEntity = contaController.getById(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testTransferencia() {

        Long idDetentor = 1L;
        Long idDestino = 2L;
        BigDecimal valor = new BigDecimal("100");

        ResponseEntity<Void> responseEntity = contaController.transferencia(idDetentor, idDestino, valor);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(movimentacaoService, times(1)).transferencia(idDetentor, idDestino, valor);
    }

}

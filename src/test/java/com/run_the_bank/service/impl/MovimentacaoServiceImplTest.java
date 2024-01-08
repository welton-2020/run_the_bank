package com.run_the_bank.service.impl;

import com.run_the_bank.external.NotificacaoService;
import com.run_the_bank.model.Conta;
import com.run_the_bank.repository.ContaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovimentacaoServiceImplTest {

    @InjectMocks
    private MovimentacaoServiceImpl movimentacaoService;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private NotificacaoService notificacaoService;

    @Test
    public void testTransferencia() {
        // Criar dados de teste
        Long idDetentor = 1L;
        Long idDestino = 2L;
        BigDecimal valor = new BigDecimal("100");

        Conta contaDetentora = new Conta();
        contaDetentora.setId(idDetentor);
        contaDetentora.setSaldo(new BigDecimal("200"));

        Conta contaDestino = new Conta();
        contaDestino.setId(idDestino);
        contaDestino.setSaldo(new BigDecimal("50"));

        // Configurar comportamento do mock do ContaRepository
        when(contaRepository.getContaAtiva(idDetentor)).thenReturn(contaDetentora);
        when(contaRepository.getContaAtiva(idDestino)).thenReturn(contaDestino);

        // Chamar o método que queremos testar
        movimentacaoService.transferencia(idDetentor, idDestino, valor);

        // Verificar se os métodos do mock foram chamados corretamente
        verify(contaRepository, times(2)).save(any(Conta.class));
        verify(notificacaoService).enviarNotificacao(anyString());

        // Verificar o estado das contas após a transferência
        assertEquals(new BigDecimal("100"), contaDetentora.getSaldo());
        assertEquals(new BigDecimal("150"), contaDestino.getSaldo());
    }
}

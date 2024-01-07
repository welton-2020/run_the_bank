package com.run_the_bank.service.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.run_the_bank.exceptions.ErroException;
import com.run_the_bank.exceptions.SaqueException;
import com.run_the_bank.external.NotificacaoService;
import com.run_the_bank.model.Conta;
import com.run_the_bank.repository.ContaRepository;
import com.run_the_bank.service.MovimentacaoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {

    private final ContaRepository contaRepository;
    private final NotificacaoService notificacaoService;


    @Transactional
    @Override
    public void transferencia(Long idDetentor, Long idDestino, BigDecimal valor) {

        Conta contaDetentora = contaRepository.getContaAtiva(idDetentor);
        Conta contaDestino = contaRepository.getContaAtiva(idDestino);
        try {
            if (contaDetentora.equals(contaDestino)) {
                throw new ErroException();
            }

            contaDetentora.sacar(valor);
            contaDestino.depositar(valor);

            contaRepository.save(contaDetentora);
            contaRepository.save(contaDestino);

            System.out.println("Transferência concluída com sucesso.");


            // Adicionando notificação após a transferência bem-sucedida
            notificacaoService.enviarNotificacao("https://run.mocky.io/v3/9769bf3a-b0b6-477a-9ff5-91f63010c9d3");

        } catch (SaqueException e) {
            throw new ErroException();

        } catch (Exception e) {
            throw new ErroException();
        }
    }
}

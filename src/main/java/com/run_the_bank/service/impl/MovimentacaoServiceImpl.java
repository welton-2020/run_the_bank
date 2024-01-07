package com.run_the_bank.service.impl;

import com.run_the_bank.exceptions.ErroException;
import com.run_the_bank.exceptions.SaqueException;
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

        } catch (SaqueException e) {
            throw new ErroException();

        } catch (Exception e) {
            throw new ErroException();
        }
    }
}

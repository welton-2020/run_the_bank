package com.run_the_bank.service;

import java.math.BigDecimal;

public interface MovimentacaoService {

    void transferencia (Long idDetentor, Long idDestino, BigDecimal valor);
}

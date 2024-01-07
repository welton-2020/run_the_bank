package com.run_the_bank.model;

import com.run_the_bank.exceptions.DepositoException;
import com.run_the_bank.exceptions.SaqueException;
import jakarta.persistence.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;


@Data
@Table(name = "tb_conta")
@Entity
public class Conta {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(Conta.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String agencia;

    @Column(nullable = false)
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConta status;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;


    public enum StatusConta {
        ATIVA,
        INATIVA
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", agencia='" + agencia + '\'' +
                ", saldo=" + saldo +
                ", status=" + status +
                '}';
    }

    public void sacar(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            LOGGER.error("Tentativa de saque com valor inválido: {}", valor);
            throw new SaqueException();
        }

        if (saldo.compareTo(valor) < 0) {
            LOGGER.error("Tentativa de saque com saldo insuficiente. Saldo atual: {}", saldo);
            throw new SaqueException();
        }

        saldo = saldo.subtract(valor);
        LOGGER.info("Saque de {} realizado com sucesso. Novo saldo: {}");
    }


    public void depositar(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            LOGGER.error("Tentativa de depósito com valor inválido: {}", valor);
            throw new DepositoException();
        }

        saldo = saldo.add(valor);
        LOGGER.info("Depósito de {} realizado com sucesso. Novo saldo: {}", valor, saldo);
    }

}
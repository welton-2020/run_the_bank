package com.run_the_bank.repository;

import com.run_the_bank.exceptions.ErroException;
import com.run_the_bank.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    boolean existsByAgenciaAndIdNot(String agencia, Long id);

    default Conta getContaAtiva(Long id) {
        Optional<Conta> conta = findById(id);
        if (!conta.isPresent() && conta.get().getStatus().equals(Conta.StatusConta.ATIVA)) {
            throw new ErroException();
        }
        return conta.get();
    }
}

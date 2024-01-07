package com.run_the_bank.service;

import com.run_the_bank.dto.ClienteDTO;
import com.run_the_bank.dto.ContaDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ContaService {

    Optional<ContaDTO> create(ContaDTO contaDTO);
    List<ContaDTO> getAll();
    Optional<ContaDTO> findById(Long id);
    Optional<ContaDTO> update(Long id, ContaDTO contaDTO);
    boolean inactive(Long id);

}

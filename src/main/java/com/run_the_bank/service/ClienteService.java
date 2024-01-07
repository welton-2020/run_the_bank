package com.run_the_bank.service;

import com.run_the_bank.dto.ClienteDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Optional<ClienteDTO> create(ClienteDTO clienteDTO);
    List<ClienteDTO> getAll();
    Optional<ClienteDTO> findById(Long id);
    Optional<ClienteDTO> update(Long id, ClienteDTO dto);
    boolean inactive(Long id);
}

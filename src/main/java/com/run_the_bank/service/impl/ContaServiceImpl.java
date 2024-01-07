package com.run_the_bank.service.impl;

import com.run_the_bank.dto.ClienteDTO;
import com.run_the_bank.dto.ContaDTO;
import com.run_the_bank.model.Cliente;
import com.run_the_bank.model.Conta;
import com.run_the_bank.repository.ClienteRepository;
import com.run_the_bank.repository.ContaRepository;
import com.run_the_bank.service.ClienteService;
import com.run_the_bank.service.ContaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Optional<ContaDTO> create(ContaDTO request) {

        // Verifique se o cliente associado existe
        ClienteDTO clienteDTO = clienteService.findById(request.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Verifique se já existe uma conta com o mesmo ID e Agência
        if (contaRepository.existsByAgenciaAndIdNot(request.getAgencia(), request.getId())) {
            throw new RuntimeException("Já existe uma conta com a mesma agência e id.");
        }

        // Mapeie os dados do DTO para a entidade Conta
        Conta novaConta = mapper.map(request, Conta.class);
        // Associe a conta ao cliente
        novaConta.setCliente(mapper.map(clienteDTO, Cliente.class));

        // Salve a nova conta no repositório
        Conta contaSalva = contaRepository.save(novaConta);

        // Mapeie a conta salva de volta para o DTO
        ContaDTO contaSalvaDTO = mapper.map(contaSalva, ContaDTO.class);

        // Retorne o Optional contendo o DTO da conta salva
        return Optional.of(contaSalvaDTO);

    }


    @Override
    public List<ContaDTO> getAll() {
        List<Conta> contas = contaRepository.findAll();
        List<ContaDTO> responses = new ArrayList<>();

        contas.forEach(conta -> {
            ContaDTO response = mapper.map(conta, ContaDTO.class);
                responses.add(response);

        });
        return responses;
    }

    @Override
    public Optional<ContaDTO> findById(Long id) {
        Optional<Conta> conta = contaRepository.findById(id);

        if (conta.isPresent()) {
            return Optional.of(mapper.map(conta.get(), ContaDTO.class));
        }

        return Optional.empty();
    }

    @Override
    public Optional<ContaDTO> update(Long id, ContaDTO contaDTO) {
        Optional<Conta> updateConta = contaRepository.findById(id);
        if (updateConta.isPresent()) {
            updateConta.get().setAgencia(contaDTO.getAgencia());
            updateConta.get().setSaldo(contaDTO.getSaldo());

            Cliente cliente = clienteRepository.findById(contaDTO.getCliente().getId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            updateConta.get().setCliente(cliente);

            contaRepository.save(updateConta.get());

            return Optional.of(mapper.map(updateConta.get(),ContaDTO.class));
        }
            return Optional.empty();

    }
        @Override
        public boolean inactive (Long id){
            Optional<Conta> conta = contaRepository.findById(id);

            if (conta.isPresent()){
                conta.get().setStatus(Conta.StatusConta.INATIVA);
                contaRepository.save(conta.get());
                return true;
            }
            return false;
        }
    }

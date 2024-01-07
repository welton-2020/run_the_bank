package com.run_the_bank.service.impl;

import com.run_the_bank.dto.ClienteDTO;
import com.run_the_bank.model.Cliente;
import com.run_the_bank.repository.ClienteRepository;
import com.run_the_bank.service.ClienteService;
import com.run_the_bank.utilitario.ValidaCNPJ;
import com.run_the_bank.utilitario.ValidaCPF;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public Optional<ClienteDTO> create(ClienteDTO request) {
        request.setStatus(true);

        String cpfCnpj = request.getCpfCnpj();
        if (!ValidaCPF.isCPF(cpfCnpj) && !ValidaCNPJ.isCNPJ(cpfCnpj)) {
            throw new RuntimeException("CPF ou CNPJ inválido");
        }

        if (repository.existsByCpfCnpj(request.getCpfCnpj())) {
          throw new RuntimeException("CPF ou CNPJ já cadastrado");
        }

        Cliente cliente = mapper.map(request, Cliente.class);

        if (ValidaCPF.isCPF(cpfCnpj)) {
            cliente.setTipo(Cliente.TipoCliente.PESSOA_FISICA);
        } else if (ValidaCNPJ.isCNPJ(cpfCnpj)) {
            cliente.setTipo(Cliente.TipoCliente.PESSOA_JURIDICA);
        }
        repository.save(cliente);

        ClienteDTO response = mapper.map(request, ClienteDTO.class);

        return Optional.of(response);
    }

    @Override
    public List<ClienteDTO> getAll() {
        List<Cliente> clientes = repository.findAll();
        List<ClienteDTO> responses = new ArrayList<>();

        clientes.forEach(cliente -> {
            ClienteDTO response = mapper.map(cliente, ClienteDTO.class);

            responses.add(response);

        });
        return responses;
    }

    @Override
    public Optional<ClienteDTO> findById(Long id) {
        Optional<Cliente> cliente = repository.findById(id);

        if (cliente.isPresent()){
            return Optional.of(mapper.map(cliente.get(), ClienteDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ClienteDTO> update(Long id, ClienteDTO dto) {
        Optional<Cliente> updateCliente = repository.findById(id);

        if (updateCliente.isPresent()){
            updateCliente.get().setNome(dto.getNome());
            updateCliente.get().setEndereco(dto.getEndereco());
            updateCliente.get().setSenha(dto.getSenha());

            repository.save(updateCliente.get());

            return Optional.of(mapper.map(updateCliente.get(), ClienteDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public boolean inactive(Long id) {
        Optional<Cliente> cliente = repository.findById(id);

        if (cliente.isPresent()){
            cliente.get().setStatus(false);
            repository.save(cliente.get());
            return true;
        }
        return false;
    }


}

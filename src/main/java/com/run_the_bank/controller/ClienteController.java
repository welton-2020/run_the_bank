package com.run_the_bank.controller;

import com.run_the_bank.dto.ClienteDTO;
import com.run_the_bank.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@RequestMapping("/clientes")
@RestController
public class ClienteController {


    private final ClienteService clienteService;


    @PostMapping("/criar")
    public ResponseEntity<ClienteDTO> create(@RequestBody @Valid ClienteDTO request){
        Optional<ClienteDTO> response =clienteService.create(request);
        if (response.isPresent()){
            return new ResponseEntity<>(response.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/buscar-todos")
    public ResponseEntity<List<ClienteDTO>> getAll(){
        return ResponseEntity.ok(clienteService.getAll());
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable("id") Long id){
        Optional<ClienteDTO> response = clienteService.findById(id);

        if (response.isPresent()){
            return ResponseEntity.ok(response.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        /* Pode ser feito conforme abaixo usando função anonima
            return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
         */
    }

    @PutMapping("/update-for-id/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable("id") Long id, @RequestBody @Valid ClienteDTO request){
        Optional<ClienteDTO> response =clienteService.update(id, request);
        if (response.isEmpty()){
            return ResponseEntity.ok(response.get());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete-for-id/{id}")
    public ResponseEntity<Void> inactive(@PathVariable("id") Long id){
        boolean inactive = clienteService.inactive(id);

        return inactive ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}


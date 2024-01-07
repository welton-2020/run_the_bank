package com.run_the_bank.controller;

import com.run_the_bank.dto.ContaDTO;
import com.run_the_bank.service.ContaService;
import com.run_the_bank.service.MovimentacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@RequestMapping("/conta")
@RestController
public class ContaController {

    private final ContaService contaService;
    private final MovimentacaoService movimentacaoService;
    @PostMapping("/criar")
    public ResponseEntity<ContaDTO> create(@RequestBody @Valid ContaDTO request){
        Optional<ContaDTO> response = contaService.create(request);
        if (response.isPresent()){
            return new ResponseEntity<>(response.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/buscar-todos")
    public ResponseEntity<List<ContaDTO>> getAll(){
        return ResponseEntity.ok(contaService.getAll());
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ContaDTO> getById(@PathVariable("id") Long id){
        Optional<ContaDTO> response = contaService.findById(id);
        if (response.isPresent()){
            return ResponseEntity.ok(response.get());
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/update-for-id/{id}")
    public ResponseEntity<ContaDTO> update(@PathVariable("id") Long id, @RequestBody @Valid ContaDTO request){
        Optional<ContaDTO> response = contaService.update(id, request);
        if (response.isEmpty()){
            return ResponseEntity.ok(response.get());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete-for-id/{id}")
    public ResponseEntity<Void> inactive(@PathVariable("id") Long id){
        boolean inactive = contaService.inactive(id);

        return inactive ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/transferencia")
    public ResponseEntity<Void> transferencia(@RequestParam (required = true) Long idDetentor,
                                              @RequestParam Long idDestino,
                                                  @RequestParam BigDecimal valor){
        movimentacaoService.transferencia(idDetentor, idDestino, valor);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

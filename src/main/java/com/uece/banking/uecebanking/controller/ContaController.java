package com.uece.banking.uecebanking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uece.banking.uecebanking.dto.Conta;
import com.uece.banking.uecebanking.repository.ContaRepository;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaRepository contaRepository;

    @GetMapping("/listar")
    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    @Autowired
    public ContaController(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @PostMapping
    public Conta criarConta(@RequestBody Conta conta) {
        return contaRepository.save(conta);
    }

    @GetMapping("/{id}")
    public Conta consultarSaldo(@PathVariable Long id) {
        return contaRepository.findById(id).orElse(null);
    }

    @PostMapping("/transferir")
    public ResponseEntity<String> transferir(@RequestParam Long origemId, @RequestParam Long destinoId, @RequestParam double valor) {
        // Lógica de transferência de dinheiro aqui
        return ResponseEntity.ok("Transferência realizada com sucesso.");
    }
}

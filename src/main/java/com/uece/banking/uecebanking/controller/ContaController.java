package com.uece.banking.uecebanking.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping()
    public Conta criarConta(@RequestBody Conta conta) {
        return contaRepository.save(conta);
    }

    @GetMapping("/{id}")
    public Conta consultarSaldo(@PathVariable Long id) {
        return contaRepository.findById(id).orElse(null);
    }

    @GetMapping("/numero/{numeroConta}")
    public Conta consultarPorNumero(@PathVariable String numeroConta) {
        return contaRepository.findByNumeroConta(numeroConta);
    }

/* 
    @PostMapping("/transferir")
    public ResponseEntity<String> transferir(@RequestParam Long origemId, @RequestParam Long destinoId, @RequestParam double valor) {
        // Lógica de transferência de dinheiro aqui
        return ResponseEntity.ok("Transferência realizada com sucesso.");
    }*/

    @PostMapping("/transferir/{contaOrigem}/{contaDestino}/{valor}")
    public ResponseEntity<String> transferir(@PathVariable String contaOrigem, @PathVariable String contaDestino, @PathVariable double valor) {
    // Busca as contas no banco de dados
    Conta origem = contaRepository.findByNumeroConta(contaOrigem);
    Conta destino = contaRepository.findByNumeroConta(contaDestino);

    // Verifica se as contas existem
    if (origem != null && destino != null) {
        // Verifica se há saldo suficiente na conta de origem
        if (origem.getSaldo() < valor) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente na conta de origem.");
        }

        // Atualiza os saldos
        origem.debitar(valor);
        destino.creditar(valor);

        // Salva as atualizações no banco de dados
        contaRepository.save(origem);
        contaRepository.save(destino);

        return ResponseEntity.ok("Transferência realizada com sucesso.");
    } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conta de origem ou destino não encontrada.");
    }
}
}

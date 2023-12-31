package com.uece.banking.uecebanking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uece.banking.uecebanking.dto.Conta;
import com.uece.banking.uecebanking.repository.ContaRepository;

@RestController
@RequestMapping("/operacao")
public class OperacaoController {

    private final ContaRepository contaRepository;

    public OperacaoController(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }
    
    //transfere valores entre contas
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

            return ResponseEntity.ok("" + origem.getSaldo());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conta de origem ou destino não encontrada.");
        }
    }


    //deposita valor em determinada conta
     @PostMapping("/depositar/{numeroConta}/{valor}")
    public ResponseEntity<String> realizarDeposito(@PathVariable String numeroConta, @PathVariable double valor) {
        // Busca a conta com o número especificado
        Conta conta = contaRepository.findByNumeroConta(numeroConta);

        // Verifica se a conta existe
        if (conta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
        }

        // Realiza o depósito
        conta.creditar(valor);

        // Salva a conta atualizada no banco de dados
        contaRepository.save(conta);

        return ResponseEntity.ok("" + conta.getSaldo());
    }

    //efetua saque de determinada conta
    @PostMapping("/sacar/{numeroConta}/{valor}")
    public ResponseEntity<String> realizarSaque(@PathVariable String numeroConta, @PathVariable double valor) {
        // Busca a conta com o número especificado
        Conta conta = contaRepository.findByNumeroConta(numeroConta);

        // Verifica se a conta existe
        if (conta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
        }

        // Verifica se há saldo suficiente para o saque
        if (conta.getSaldo() < valor) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente para o saque.");
        }

        // Realiza o saque
        conta.debitar(valor);

        // Salva a conta atualizada no banco de dados
        contaRepository.save(conta);

        return ResponseEntity.ok("" + conta.getSaldo());
    }

}

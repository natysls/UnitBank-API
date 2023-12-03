package com.uece.banking.uecebanking.controller;

import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uece.banking.uecebanking.dto.Conta;
import com.uece.banking.uecebanking.repository.ContaRepository;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaRepository contaRepository;

    public ContaController(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    //lista todas as contas cadastradas no banco
    @GetMapping("/listar")
    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    Random random = new Random();
    //cria uma nova conta passando um body json com nome, numero da conta e o saldo inicial
    @PostMapping("/{nome}/{numero}/{saldo}")
    public Conta criarConta(@PathVariable String nome, 
    @PathVariable String numero, @PathVariable double saldo) {

        Conta conta = new Conta();
        
        Integer id = random.nextInt(100000);
        conta.setId(id);
        conta.setNome(nome);
        conta.setNumeroConta(numero);
        conta.setSaldo(saldo);
        return contaRepository.save(conta);
    }

    //retorna dados da conta passando o id
    @GetMapping("/{id}")
    public Conta consultarPorId(@PathVariable Long id) {
        return contaRepository.findById(id).orElse(null);
    }

    //retorna dados passando o numero da conta
    @GetMapping("/numero/{numeroConta}")
    public Conta consultarPorNumero(@PathVariable String numeroConta) {
        return contaRepository.findByNumeroConta(numeroConta);
    }

    
}

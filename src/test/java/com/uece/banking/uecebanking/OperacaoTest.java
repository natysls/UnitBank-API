package com.uece.banking.uecebanking;

import com.uece.banking.uecebanking.controller.OperacaoController;
import com.uece.banking.uecebanking.dto.Conta;
import com.uece.banking.uecebanking.repository.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperacaoTest {

    private OperacaoController operacaoController;
    private ContaRepository contaRepository;

    @BeforeEach
    public void setUp() {
        contaRepository = Mockito.mock(ContaRepository.class);
        operacaoController = new OperacaoController(contaRepository);
    }

    @Test
    public void testTransferir() {
        String contaOrigem = "123";
        Conta origem = new Conta();
        origem.setNumeroConta(contaOrigem);
        origem.setSaldo(1000.0);

        String contaDestino = "321";
        Conta destino = new Conta();
        destino.setNumeroConta(contaDestino);
        destino.setSaldo(500.0);

        Mockito.when(contaRepository.findByNumeroConta(contaOrigem)).thenReturn(origem);
        Mockito.when(contaRepository.findByNumeroConta(contaDestino)).thenReturn(destino);

        ResponseEntity<String> response = operacaoController.transferir(contaOrigem, contaDestino, 200.0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRealizarDeposito() {
        String contaDeposito = "123";
        Conta conta = new Conta();
        conta.setNumeroConta(contaDeposito);
        conta.setSaldo(1000.0);

        Mockito.when(contaRepository.findByNumeroConta(contaDeposito)).thenReturn(conta);

        ResponseEntity<String> response = operacaoController.realizarDeposito(contaDeposito, 200.0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRealizarSaque() {
        String contaSaque = "123";
        Conta conta = new Conta();
        conta.setNumeroConta(contaSaque);
        conta.setSaldo(1000.0);

        Mockito.when(contaRepository.findByNumeroConta(contaSaque)).thenReturn(conta);

        ResponseEntity<String> response = operacaoController.realizarSaque(contaSaque, 200.0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSaqueSemSaldo() {
        String contaSaque = "123";
        Conta conta = new Conta();
        conta.setNumeroConta(contaSaque);
        conta.setSaldo(0);

        Mockito.when(contaRepository.findByNumeroConta(contaSaque)).thenReturn(conta);

        ResponseEntity<String> response = operacaoController.realizarSaque(contaSaque, 78);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Saldo insuficiente para o saque.", response.getBody());
    }

    @Test
    public void testTransferenciaSemSaldo() {
        String contaNumDestino = "123";
        Conta contaDestino = new Conta();
        contaDestino.setNumeroConta(contaNumDestino);
        contaDestino.setSaldo(0);

        String contaNumOrigem = "456";
        Conta contaOrigem = new Conta();
        contaOrigem.setNumeroConta(contaNumOrigem);
        contaOrigem.setSaldo(0);

        Mockito.when(contaRepository.findByNumeroConta(contaNumOrigem)).thenReturn(contaOrigem);
        Mockito.when(contaRepository.findByNumeroConta(contaNumDestino)).thenReturn(contaDestino);

        ResponseEntity<String> response = operacaoController.transferir(contaNumOrigem, contaNumDestino, 12);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Saldo insuficiente na conta de origem.", response.getBody());

    }
}

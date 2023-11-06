package com.uece.banking.uecebanking;

import com.uece.banking.uecebanking.controller.ContaController;
import com.uece.banking.uecebanking.dto.Conta;
import com.uece.banking.uecebanking.repository.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
@AutoConfigureMockMvc
public class ContaTest {

    @Mock
    private ContaRepository contaRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ContaController(contaRepository)).build();
    }

    @Test
    public void testListarContas() throws Exception {
        Random random = new Random();
        int id = random.nextInt(100000);
        int id2 = random.nextInt(100000);

        List<Conta> contas = new ArrayList<>();
        contas.add(new Conta(id,"Davi", "12345", 1000.00));
        contas.add(new Conta(id2, "Natalia", "67890", 2000.00));

        Mockito.when(contaRepository.findAll()).thenReturn(contas);

        mockMvc.perform(MockMvcRequestBuilders.get("/conta/listar"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    public void testCriarConta() throws Exception {
        Random random = new Random();
        int id = random.nextInt(100000);
        Conta novaConta = new Conta();
        novaConta.setId(id);
        novaConta.setNome("Wesley");
        novaConta.setNumeroConta("45678");
        novaConta.setSaldo(2000.0d);

        Mockito.when(contaRepository.save(novaConta)).thenReturn(novaConta);

        mockMvc.perform(MockMvcRequestBuilders.post("/conta/Wesley/45678/2000.0"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testConsultarPorId() throws Exception {
        Random random = new Random();
        int id = random.nextInt(100000);
        Conta conta = new Conta();
        conta.setId(id);
        conta.setNome("Alicia");
        conta.setNumeroConta("99999");
        conta.setSaldo( 123.00);

        Mockito.when(contaRepository.findById((long) id)).thenReturn(Optional.of(conta));

        mockMvc.perform(MockMvcRequestBuilders.get("/conta/{id}", id))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    public void testConsultarPorNumero() throws Exception {
        String numeroConta = "77777";
        Conta conta = new Conta();
        Random random = new Random();
        int id = random.nextInt(100000);
        conta.setId(id);
        conta.setNome("Rebeca");
        conta.setNumeroConta(numeroConta);
        conta.setSaldo( 200.00);

        Mockito.when(contaRepository.findByNumeroConta(numeroConta)).thenReturn(conta);

        mockMvc.perform(MockMvcRequestBuilders.get("/conta/numero/{numeroConta}", numeroConta))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
}

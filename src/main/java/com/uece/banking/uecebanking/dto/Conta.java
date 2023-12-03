package com.uece.banking.uecebanking.dto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Conta {
    
    @Id
    private Integer id;
    private String numeroConta;
    private String nomeTitular;
    private double saldo;

    public Conta(){}

    public Conta(Integer idConta, String nome, String numero, double saldoConta){
        id = idConta;
        nomeTitular = nome;
        numeroConta = numero;
        saldo = saldoConta;

    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNumeroConta() {
        return numeroConta;
    }
    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }
    public String getNome() {
        return nomeTitular;
    }
    public void setNome(String nome) {
        this.nomeTitular = nome;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void debitar(double valor) {
        this.saldo -= valor;
    }

    public void creditar(double valor) {
        this.saldo += valor;
    }

    
    
}

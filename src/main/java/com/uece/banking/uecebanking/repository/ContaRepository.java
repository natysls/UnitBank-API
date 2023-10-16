package com.uece.banking.uecebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uece.banking.uecebanking.dto.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    // Métodos personalizados, se necessário
    Conta findByNumeroConta(String numeroConta);
}

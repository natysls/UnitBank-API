package com.uece.banking.uecebanking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UeceBankingController {
    
    @GetMapping("/hello")
    public String hello(){
        return "Olá, Mundo!";
    }
}

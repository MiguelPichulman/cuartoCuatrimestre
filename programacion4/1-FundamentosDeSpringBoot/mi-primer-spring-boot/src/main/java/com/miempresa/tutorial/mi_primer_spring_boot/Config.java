package com.miempresa.tutorial.mi_primer_spring_boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //archivo para registrar Beans de manera manual
public class Config {
    @Bean
    public SaludoService otroSaludoService(){
        return new SaludoService();
    }
}

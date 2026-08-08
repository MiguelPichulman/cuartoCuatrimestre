package com.miempresa.tutorial.mi_primer_spring_boot;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class SaludoService {

        public SaludoService(){
            System.out.println(">>> Constructor de SaludoService llamado");
        }

        @PostConstruct
        public void init() {
            System.out.println(">>> Metodo init() de  SaludoService ejecutado");

        }
        public String saludar(){
            return "hola desde un Bean de Spring";
        }
}

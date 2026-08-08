package com.miempresa.tutorial.mi_primer_spring_boot;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;//no hace falta new si inyecta abajo

    //inyeccion por consructor
    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public String getUserGreeting(){
        return "Hola " + repository.findUserNamer();
    }
}

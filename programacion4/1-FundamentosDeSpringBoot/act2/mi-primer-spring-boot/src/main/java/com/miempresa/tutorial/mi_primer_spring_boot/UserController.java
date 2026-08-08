package com.miempresa.tutorial.mi_primer_spring_boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService service;

    //inyeccion por constructor
    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping("/hello")
    public String sayHello(){
        return service.getUserGreeting();
    }

}

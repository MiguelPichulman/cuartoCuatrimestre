package com.miempresa.tutorial.mi_primer_spring_boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class HelloController {
    //1.instanciar un objeto Logger
    private static Logger logger = Logger.getLogger(HelloController.class.getName());


    @GetMapping("/hello")
    public String hello() {

        logger.trace("este es un mensaje trace");
        logger.debug("ingresando a endpoint /hello");


        return "Hola con hot reload";

    }
}

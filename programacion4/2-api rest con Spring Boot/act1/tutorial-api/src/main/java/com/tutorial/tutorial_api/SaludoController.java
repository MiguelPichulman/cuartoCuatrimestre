package com.tutorial.tutorial_api;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ejemplo")
public class SaludoController {
    @GetMapping("/saludo")
    public String obtenerSaludo(){
        return "Saludo";
    }

    @GetMapping("/usuario")
    public Map<String,String> obtenerUsuario(){
        Map<String,String> usuario = new HashMap<>();
        usuario.put("nombre","Jose");
        usuario.put("apellido","Perez");
        return usuario;
    }

    //TUTORIAL GET / POST / PUT / DELETE
    //GET: leer datos
    @GetMapping
    public Map<String,String> obtener(){
        Map<String,String> respuesta = new HashMap<>();
        respuesta.put("mensaje","GET : rescurso consultado");
        return respuesta;
    }

    //POST: crear recurso
    @PostMapping
    public Map<String,String> crear(){
        Map<String,String> respuesta = new HashMap<>();
        respuesta.put("mensaje","POST : recurso creado");
        return respuesta;
    }

    @PutMapping("/{id}")
    public Map<String,String> actualizar(@PathVariable int id){
        Map<String,String> respuesta = new HashMap<>();
        respuesta.put("mensaje","PUT : recurso " + id + " actualizado");
        return respuesta;
    }

    @DeleteMapping("/{id}")
    public Map<String,String> eliminar(@PathVariable int id){
        Map<String,String> respuesta = new HashMap<>();
        respuesta.put("mensaje","DELETE : recurso " + id + " eliminado");
        return respuesta;
    }
}
package com.tutorial.tutorial_api;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    //@PathVariable
    @GetMapping("/{id}")
    public Map<String, Object> obtenerPorId(@PathVariable Long id){
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("id",id);
        respuesta.put("nombre", "Usuario " + id);
        return respuesta;
    }

    //@RequestParam
    @GetMapping
    public Map<String,Object> filtrar(@RequestParam (required = false) String categoria,
                                      @RequestParam (defaultValue = "0") int pagina){
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("categoria", categoria);
        respuesta.put("pagina", pagina);
        respuesta.put(("resultados"), Arrays.asList("item1", "item2"));
        return respuesta;
    }

    //@RequestBody
    @PostMapping
    public Map<String, Object> crear(@RequestBody Map<String,Object> nuevoUsuario){
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "usuario creado con exito");
        respuesta.put("resultados", nuevoUsuario);
        return respuesta;
    }
}
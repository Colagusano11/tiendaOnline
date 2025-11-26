package com.colagusano11.tiendaonline.controllers;


import com.colagusano11.tiendaonline.models.Usuario;

import com.colagusano11.tiendaonline.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Listar usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios(){
     return usuarioService.getAllUsuarios();

    }
    //Agregar usuarios
    @PostMapping
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario){
        return usuarioService.createUsuario(usuario);
    }
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id){
       usuarioService.deleteUsuario(id);


    }

}

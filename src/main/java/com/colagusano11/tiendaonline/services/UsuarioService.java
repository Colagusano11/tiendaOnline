package com.colagusano11.tiendaonline.services;

import com.colagusano11.tiendaonline.models.Usuario;

import java.util.List;
// Capa donde vamos a definir los metodos que el Usuario va ofrecer.
public interface UsuarioService {

    List<Usuario> getAllUsuarios();
    Usuario createUsuario(Usuario usuario);
    void deleteUsuario(Long id);


}

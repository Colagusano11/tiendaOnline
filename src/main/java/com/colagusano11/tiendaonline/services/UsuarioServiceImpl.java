package com.colagusano11.tiendaonline.services;

import com.colagusano11.tiendaonline.models.Usuario;
import com.colagusano11.tiendaonline.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Capa donde va la logica real( validaciones, reglas etc.)
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }
    public Usuario createUsuario(Usuario usuario){
        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()){
            throw new RuntimeException("El email ya esta registrado");
        }

        return usuarioRepository.save(usuario);
    }
    public void deleteUsuario(Long id){
         usuarioRepository.deleteById(id);
    }



}

package com.colagusano11.tiendaonline.services;


import com.colagusano11.tiendaonline.models.Gender;
import com.colagusano11.tiendaonline.models.Producto;
import com.colagusano11.tiendaonline.models.Usuario;
import com.colagusano11.tiendaonline.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto getProducto(Long id){
     return productoRepository.findById(id).orElseThrow(
             ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Producto no encontrado"));
    }
    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }
    public List<Producto> getProductosOrdenadosPorPrecio (String orden){
        if(orden.equalsIgnoreCase("desc")){
            return productoRepository.findAllByOrderByPrecioDesc();
        }else {
            return productoRepository.findAllByOrderByPrecioAsc();
        }

    }
    @Override
    public List<Producto> findByGender(Gender gender){
        return productoRepository.findByGender(gender);
    }


    }



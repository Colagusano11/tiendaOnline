package com.colagusano11.tiendaonline.controllers;



import com.colagusano11.tiendaonline.models.Gender;
import com.colagusano11.tiendaonline.models.Producto;

import com.colagusano11.tiendaonline.services.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    @GetMapping("/{id}")
    public Producto getProducto(@PathVariable Long id){
        return productoService.getProducto(id);
    }

    @GetMapping
    public List<Producto> getAllProductos(){
        return productoService.getAllProductos();
    }

    @GetMapping("/orden/precio")
    public List<Producto> getProductosOrdenadosPorPrecio(@RequestParam(defaultValue = "desc") String orden){
        return productoService.getProductosOrdenadosPorPrecio(orden);
        }

      @GetMapping("/genero/{gender}")
    public List<Producto> findByGender(@PathVariable String gender){
        try{
            Gender g= Gender.valueOf((gender.toUpperCase()));
            return productoService.findByGender(g);
          }catch (IllegalArgumentException e){
              return Collections.emptyList();
          }

      }
 }




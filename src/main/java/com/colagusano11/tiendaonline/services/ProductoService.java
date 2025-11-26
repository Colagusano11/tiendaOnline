package com.colagusano11.tiendaonline.services;

import com.colagusano11.tiendaonline.models.Gender;
import com.colagusano11.tiendaonline.models.Producto;
import com.colagusano11.tiendaonline.models.Usuario;

import java.util.List;

public interface ProductoService {

    Producto getProducto(Long id);

    List<Producto> getAllProductos();
    List<Producto> getProductosOrdenadosPorPrecio(String orden);
    List<Producto> findByGender(Gender gender);

}

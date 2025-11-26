package com.colagusano11.tiendaonline.repositories;

import com.colagusano11.tiendaonline.models.Gender;
import com.colagusano11.tiendaonline.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

    Optional<Producto> findByNombre(String nombre);
    List<Producto> findAllByOrderByPrecioAsc();
    List<Producto> findAllByOrderByPrecioDesc();
    List<Producto> findByGender(Gender gender);
}

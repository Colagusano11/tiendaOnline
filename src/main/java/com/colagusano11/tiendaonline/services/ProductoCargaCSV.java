package com.colagusano11.tiendaonline.services;


import com.colagusano11.tiendaonline.models.Gender;
import com.colagusano11.tiendaonline.models.Producto;
import com.colagusano11.tiendaonline.repositories.ProductoRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;


@Component
public class ProductoCargaCSV {

    @Autowired
    ProductoRepository productoRepository;

    @PostConstruct
    public void init() {
        try {
            cargarProductos();
            System.out.println("Productos CSV cargados correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void cargarProductos() throws Exception {
        productoRepository.deleteAll();
        ClassPathResource resource = new ClassPathResource("bts_products (6).csv");
        InputStream inputStream = resource.getInputStream();
        try (CSVParser csvParser = CSVFormat.DEFAULT
                .withDelimiter(';')
                .withHeader()
                .withSkipHeaderRecord()
                .parse(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            for (CSVRecord record : csvParser) {
                try {
                    Producto producto = new Producto();

                    producto.setId(Long.parseLong(record.get("id")));
                    producto.setEan(record.get("ean"));
                    producto.setNombre(record.get("name"));
                    producto.setDescripcion(record.get("description"));

                    // Cuidado con los precios: limpia símbolo y formato
                    String precioStr = record.get("price").replace("€", "").replace(",", ".").trim();
                    producto.setPrecio(precioStr.isEmpty() ? BigDecimal.ZERO : new BigDecimal(precioStr));

                    // Stock
                    String stockStr = record.get("stock").trim();
                    producto.setStock(stockStr.isEmpty() ? 0 : Integer.parseInt(stockStr));

                    producto.setImagen(record.get("image"));
                    producto.setManufacturer(record.get("manufacturer"));

                    // Gender
                    String genderCsv = record.get("gender").trim().toLowerCase();
                    switch (genderCsv) {
                        case "woman":
                            producto.setGender(Gender.WOMAN);
                            break;
                        case "man":
                            producto.setGender(Gender.MAN);
                            break;
                        default:
                            producto.setGender(Gender.UNISEX);
                    }

                    productoRepository.save(producto);

                } catch (Exception e) {
                    System.err.println("Error cargando producto: " + record.toString() + " - " + e.getMessage());
                }
            }
        }
    }
}
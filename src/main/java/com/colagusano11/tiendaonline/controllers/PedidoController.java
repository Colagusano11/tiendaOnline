package com.colagusano11.tiendaonline.controllers;

import com.colagusano11.tiendaonline.models.Pedido;
import com.colagusano11.tiendaonline.services.PedidoServicie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoServicie pedidoService;


    @GetMapping
    public List<Pedido> getAllPedidos(){
        return pedidoService.getAllPedidos();
    }

    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido){
        return pedidoService.createPedido(pedido);

    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id){
        pedidoService.deletePedido(id);
    }


}

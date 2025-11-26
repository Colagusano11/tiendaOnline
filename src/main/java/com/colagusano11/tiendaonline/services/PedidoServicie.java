package com.colagusano11.tiendaonline.services;

import com.colagusano11.tiendaonline.models.EstadoPedido;
import com.colagusano11.tiendaonline.models.Pedido;
import com.colagusano11.tiendaonline.models.Producto;

import java.util.List;

public interface PedidoServicie {

    List<Pedido> getAllPedidos();
    Pedido createPedido(Pedido pedido);
    void cambiarEstado(Long idPedido, EstadoPedido nuevoEstado);
    void deletePedido(Long id);

}

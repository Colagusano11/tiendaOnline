package com.colagusano11.tiendaonline.services;

import com.colagusano11.tiendaonline.exception.StockInsuficienteException;
import com.colagusano11.tiendaonline.models.EstadoPedido;
import com.colagusano11.tiendaonline.models.Pedido;
import com.colagusano11.tiendaonline.models.PedidoProducto;
import com.colagusano11.tiendaonline.models.Producto;
import com.colagusano11.tiendaonline.repositories.PedidoRepository;
import com.colagusano11.tiendaonline.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PedidoServicieImpl implements PedidoServicie {

    @Autowired
    private PedidoRepository pedidoRepository;
    private ProductoRepository productoRepository;


    @Override
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido createPedido(Pedido pedido) {

        List<PedidoProducto> list = pedido.getLineas();
        for (PedidoProducto p : list) {
            if (p.getCantidad() > p.getProducto().getStock()) {
                throw new StockInsuficienteException("Fuera de Stock");
            }
        }
        for (PedidoProducto c : list) {
            Integer nuevoStock = 0;
            nuevoStock = c.getProducto().getStock() - c.getCantidad();
            c.getProducto().setStock(nuevoStock);
            productoRepository.save(c.getProducto());
        }
        return pedidoRepository.save(pedido);
    }

    @Override
    public void cambiarEstado(Long idPedido, EstadoPedido nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        EstadoPedido estadoActual = pedido.getEstado();

        if (estadoActual == EstadoPedido.PENDIENTE && nuevoEstado == EstadoPedido.RECIBIDO
                || estadoActual == EstadoPedido.RECIBIDO && nuevoEstado == EstadoPedido.ENVIADO
                || estadoActual == EstadoPedido.ENVIADO && nuevoEstado == EstadoPedido.ENTREGADO) {

            pedido.setEstado(nuevoEstado);
            pedidoRepository.save(pedido);
        } else {
            throw new RuntimeException("Transición de estado no permitida");
        }


    }

    @Override
    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}

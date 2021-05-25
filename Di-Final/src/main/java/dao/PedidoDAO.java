package dao;

import models.Pedido;
import exceptions.WrongDeliveryDayCreationException;
import exceptions.WrongOrderDayCreationException;
import java.util.*;

public class PedidoDAO implements DAO<Pedido> {

    List<Pedido> pedidos;

    public PedidoDAO() {
        pedidos = new ArrayList<>();
        pedidos.add(new Pedido.PedidoBuilder(1).setCod_cliente(1).setFecha_creacion(new Date("01/13/2021"))
                .setFecha_entrega(new Date("01/16/2021")).build());
        pedidos.add(new Pedido.PedidoBuilder(2).setCod_cliente(2).setFecha_creacion(new Date("01/13/2021"))
                .setFecha_entrega(new Date("01/20/2021")).build());
    }

    public List<Pedido> getAll() {
        return pedidos;
    }

    public Pedido get(int cod_pedido) {
        return pedidos.stream().filter(p -> p.getCod_pedido() == cod_pedido).findAny().orElse(null);
    }

    public void save(Pedido pedido) throws WrongOrderDayCreationException, WrongDeliveryDayCreationException {
        if (pedidos.stream().anyMatch(p -> p.getCod_pedido() == pedido.getCod_pedido())) {
            throw new WrongOrderDayCreationException("No se pudo agregar el pedido. Codigo de pedido en uso");
        }
        if (pedido.getFecha_creacion().getDay() != new Date().getDay()
                || pedido.getFecha_creacion().getMonth() != new Date().getMonth()
                || pedido.getFecha_creacion().getYear() != new Date().getYear()) {
            throw new WrongOrderDayCreationException("La fecha de creacion no es la fecha actual. No se pudo agregar el pedido");
        }
        if ((pedido.getFecha_creacion().getDay() + 3) > pedido.getFecha_entrega().getDay()) {
            throw new WrongDeliveryDayCreationException(
                    "No se pudo agregar el pedido. La fecha de entrega debe ser 3 dias minimos despues de la fecha de envio");
        }
        pedidos.add(pedido);
    }

    public void delete(int cod_pedido) {
        pedidos.remove(pedidos.stream().filter(p -> p.getCod_pedido() == cod_pedido).findAny().orElse(null));
    }
}

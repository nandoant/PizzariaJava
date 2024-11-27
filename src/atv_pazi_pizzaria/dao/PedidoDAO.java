package atv_pazi_pizzaria.dao;

import atv_pazi_pizzaria.Model.Pedido;
import java.util.List;

public interface PedidoDAO {
    void adicionarPedido(Pedido pedido);
    Pedido obterPedidoPorId(Integer id);
    Pedido obterPedidoPorClienteID(Integer clienteID);
    List<Pedido> obterTodosPedidos();
    void atualizarPedido(Pedido pedido);
    void removerPedido(Integer id);
}

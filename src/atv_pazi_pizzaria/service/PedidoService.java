
package atv_pazi_pizzaria.service;

import atv_pazi_pizzaria.Model.Pedido;
import atv_pazi_pizzaria.dao.PedidoDAO;
import java.util.List;

public class PedidoService {
    private PedidoDAO pedidoDAO;

    public PedidoService(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public void adicionarPedido(Pedido pedido) {
        pedidoDAO.adicionarPedido(pedido);
    }

    public Pedido obterPedidoPorId(Integer id) {
        return pedidoDAO.obterPedidoPorId(id);
    }
    
    public Pedido obterPedidoPorClienteID(Integer clienteID) {
        return pedidoDAO.obterPedidoPorClienteID(clienteID);
    }

    public List<Pedido> obterTodosPedidos() {
        return pedidoDAO.obterTodosPedidos();
    }

    public void atualizarPedido(Pedido pedido) {
        pedidoDAO.atualizarPedido(pedido);
    }

    public void removerPedido(Integer id) {
        pedidoDAO.removerPedido(id);
    }

    public Double calcularValorTotalPedido(Integer id) {
        Pedido pedido = pedidoDAO.obterPedidoPorId(id);
        if (pedido != null) {
            return pedido.calcularValorTotal();
        }
        return 0.0;
    }
}

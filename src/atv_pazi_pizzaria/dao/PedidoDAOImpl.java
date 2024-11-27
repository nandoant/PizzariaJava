package atv_pazi_pizzaria.dao;


import atv_pazi_pizzaria.Model.Pedido;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PedidoDAOImpl implements PedidoDAO {
    private static Map<Integer, Pedido> pedidos = new HashMap<>();
    private static int idAtual = 1;

    @Override
    public void adicionarPedido(Pedido pedido) {
        pedido.setId(idAtual++);
        pedidos.put(pedido.getId(), pedido);
    }

    @Override
    public Pedido obterPedidoPorId(Integer id) {
        return pedidos.get(id);
    }
    
    @Override
    public Pedido obterPedidoPorClienteID(Integer clienteID) {
        for (Pedido pedido: pedidos.values())
            if(pedido.getId() == clienteID)
                return pedido;
        
        return null;
    }

    @Override
    public List<Pedido> obterTodosPedidos() {
        return new ArrayList<>(pedidos.values());
    }

    @Override
    public void atualizarPedido(Pedido pedido) {
        pedidos.put(pedido.getId(), pedido);
    }

    @Override
    public void removerPedido(Integer id) {
        pedidos.remove(id);
    }


}

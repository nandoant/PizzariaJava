package atv_pazi_pizzaria.dao;


import atv_pazi_pizzaria.Model.Cliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteDAOImpl implements ClienteDAO {
    private static Map<Integer, Cliente> clientes = new HashMap<>();
    private static int idAtual = 1;

    @Override
    public void adicionarCliente(Cliente cliente) {
        cliente.setId(idAtual++);
        clientes.put(cliente.getId(), cliente);
    }

    @Override
    public Cliente obterClientePorId(Integer id) {
        return clientes.get(id);
    }

    @Override
    public List<Cliente> obterTodosClientes() {
        return new ArrayList<>(clientes.values());
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        clientes.put(cliente.getId(), cliente);
    }

    @Override
    public void removerCliente(Integer id) {
        clientes.remove(id);
    }
}

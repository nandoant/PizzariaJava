package atv_pazi_pizzaria.service;

import atv_pazi_pizzaria.Model.Cliente;
import atv_pazi_pizzaria.dao.ClienteDAO;
import java.util.List;

public class ClienteService {
    private ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public void adicionarCliente(Cliente cliente) {
        clienteDAO.adicionarCliente(cliente);
    }

    public Cliente obterClientePorId(Integer id) {
        return clienteDAO.obterClientePorId(id);
    }

    public List<Cliente> obterTodosClientes() {
        return clienteDAO.obterTodosClientes();
    }

    public void atualizarCliente(Cliente cliente) {
        clienteDAO.atualizarCliente(cliente);
    }

    public void removerCliente(Integer id) {
        clienteDAO.removerCliente(id);
    }
}

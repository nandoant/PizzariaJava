package atv_pazi_pizzaria.dao;

import atv_pazi_pizzaria.Model.Cliente;
import java.util.List;

public interface ClienteDAO {
    void adicionarCliente(Cliente cliente);
    Cliente obterClientePorId(Integer id);
    List<Cliente> obterTodosClientes();
    void atualizarCliente(Cliente cliente);
    void removerCliente(Integer id);
}

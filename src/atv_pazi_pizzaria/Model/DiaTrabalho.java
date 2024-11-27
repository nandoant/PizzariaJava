
package atv_pazi_pizzaria.Model;

import java.util.ArrayList;
import java.util.List;

public class DiaTrabalho {
    private Integer id;
    private String data;
    private List<Pedido> pedidos;

    public DiaTrabalho(Integer id, String data) {
        this.id = id;
        this.data = data;
        pedidos = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}

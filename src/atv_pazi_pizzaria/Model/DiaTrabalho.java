
package atv_pazi_pizzaria.Model;

import java.util.ArrayList;
import java.util.List;

public class DiaTrabalho {
    private String data;
    private List<Pedido> pedidos;

    public DiaTrabalho(String data) {
        this.data = data;
        pedidos = new ArrayList<>();
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

    public double getTotalVendas() {
        double total = 0;
        for (Pedido pedido : pedidos) {
            total += pedido.calcularValorTotal();
        }
        return total;
    }
}

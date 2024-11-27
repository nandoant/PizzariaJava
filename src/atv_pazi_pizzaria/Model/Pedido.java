package atv_pazi_pizzaria.Model;

import java.util.ArrayList;
import java.util.List;


public class Pedido {
    private Integer id;
    private Cliente cliente;
    private List<Pizza> pizzas;
    private String observacao;

    public Pedido(Integer id, Cliente cliente, String observacao) {
        this.id = id;
        this.cliente = cliente;
        this.observacao = observacao;
        pizzas = new ArrayList<>();
    }

    public Double calcularValorTotal() {
        double total = 0.0;
        for (Pizza pizza : pizzas) {
            total += pizza.getPreco();
        }
        return total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
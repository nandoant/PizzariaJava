package atv_pazi_pizzaria.dao;

import atv_pazi_pizzaria.Model.Pizza;
import atv_pazi_pizzaria.Model.Pizzas.Caipira;
import atv_pazi_pizzaria.Model.Pizzas.Calabresa;
import atv_pazi_pizzaria.Model.Pizzas.FrangoComCatupiry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaDAOImpl implements PizzaDAO {
    private static Map<Integer, Pizza> pizzas = new HashMap<>();
    private static int idAtual = 4;
    
    public PizzaDAOImpl(){
        Caipira caipira = new Caipira();
        Calabresa calabresa = new Calabresa();
        FrangoComCatupiry frangoComCatupiry = new FrangoComCatupiry();
        pizzas.put(caipira.getId(), caipira);
        pizzas.put(calabresa.getId(), calabresa);
        pizzas.put(frangoComCatupiry.getId(), frangoComCatupiry);
    }

    @Override
    public void adicionarPizza(Pizza pizza) {
        pizza.setId(idAtual++);
        pizzas.put(pizza.getId(), pizza);
    }

    @Override
    public Pizza obterPizzaPorId(Integer id) {
        return pizzas.get(id);
    }

    @Override
    public List<Pizza> obterTodasPizzas() {
        return new ArrayList<>(pizzas.values());
    }

    @Override
    public void atualizarPizza(Pizza pizza) {
        pizzas.put(pizza.getId(), pizza);
    }

    @Override
    public void removerPizza(Integer id) {
        pizzas.remove(id);
    }
}

package atv_pazi_pizzaria.service;

import atv_pazi_pizzaria.Model.Pizza;
import atv_pazi_pizzaria.dao.PizzaDAO;
import java.util.List;

public class PizzaService {
    private PizzaDAO pizzaDAO;

    public PizzaService(PizzaDAO pizzaDAO) {
        this.pizzaDAO = pizzaDAO;
    }

    public void adicionarPizza(Pizza pizza) {
        pizzaDAO.adicionarPizza(pizza);
    }

    public Pizza obterPizzaPorId(Integer id) {
        return pizzaDAO.obterPizzaPorId(id);
    }

    public List<Pizza> obterTodasPizzas() {
        return pizzaDAO.obterTodasPizzas();
    }

    public void atualizarPizza(Pizza pizza) {
        pizzaDAO.atualizarPizza(pizza);
    }

    public void removerPizza(Integer id) {
        pizzaDAO.removerPizza(id);
    }
}

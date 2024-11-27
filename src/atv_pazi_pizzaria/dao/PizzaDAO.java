package atv_pazi_pizzaria.dao;

import atv_pazi_pizzaria.Model.Pizza;
import java.util.List;


public interface PizzaDAO {
    void adicionarPizza(Pizza pizza);
    Pizza obterPizzaPorId(Integer id);
    List<Pizza> obterTodasPizzas();
    void atualizarPizza(Pizza pizza);
    void removerPizza(Integer id);
}

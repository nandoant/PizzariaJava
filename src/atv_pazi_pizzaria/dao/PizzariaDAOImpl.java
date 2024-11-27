package atv_pazi_pizzaria.dao;

import atv_pazi_pizzaria.Model.Pizzaria;


public class PizzariaDAOImpl implements PizzariaDAO {
    private static Pizzaria pizzaria;

    @Override
    public void adicionarPizzaria(Pizzaria pizzaria) {
        this.pizzaria = pizzaria;
    }

    @Override
    public Pizzaria obterPizzaria() {
        return pizzaria;
    }

    @Override
    public void atualizarPizzaria(Pizzaria pizzaria) {
        this.pizzaria = pizzaria;
    }
}

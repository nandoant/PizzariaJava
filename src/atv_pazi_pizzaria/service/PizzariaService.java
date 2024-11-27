package atv_pazi_pizzaria.service;

import atv_pazi_pizzaria.Model.Pizzaria;
import atv_pazi_pizzaria.dao.PizzariaDAO;

public class PizzariaService {
    private PizzariaDAO pizzariaDAO;

    public PizzariaService(PizzariaDAO pizzariaDAO) {
        this.pizzariaDAO = pizzariaDAO;
    }

    public void adicionarPizzaria(Pizzaria pizzaria) {
        pizzariaDAO.adicionarPizzaria(pizzaria);
    }

    public Pizzaria obterPizzaria() {
        return pizzariaDAO.obterPizzaria();
    }

    public void atualizarPizzaria(Pizzaria pizzaria) {
        pizzariaDAO.atualizarPizzaria(pizzaria);
    }
}

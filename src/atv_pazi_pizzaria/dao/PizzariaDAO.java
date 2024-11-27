package atv_pazi_pizzaria.dao;

import atv_pazi_pizzaria.Model.Pizzaria;


public interface PizzariaDAO {
    void adicionarPizzaria(Pizzaria pizzaria);
    Pizzaria obterPizzaria();
    void atualizarPizzaria(Pizzaria pizzaria);
}

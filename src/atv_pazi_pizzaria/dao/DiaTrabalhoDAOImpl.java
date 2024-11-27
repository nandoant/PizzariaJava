package atv_pazi_pizzaria.dao;

import atv_pazi_pizzaria.Model.DiaTrabalho;
import java.util.*;

public class DiaTrabalhoDAOImpl implements DiaTrabalhoDAO {
    private static Map<String, DiaTrabalho> diasTrabalho = new HashMap<>();
    private static int idAtual = 1;
    //DATA dd/mm/aa

    @Override
    public void adicionarDiaTrabalho(DiaTrabalho diaTrabalho) {
        diaTrabalho.setId(idAtual++);
        diasTrabalho.put(diaTrabalho.getData(), diaTrabalho);
    }

    @Override
    public DiaTrabalho obterDiaTrabalhoPorId(String data) {
        return diasTrabalho.get(data);
    }

    @Override
    public List<DiaTrabalho> obterTodosDiasTrabalho() {
        return new ArrayList<>(diasTrabalho.values());
    }

    @Override
    public void atualizarDiaTrabalho(DiaTrabalho diaTrabalho) {
        diasTrabalho.put(diaTrabalho.getData(), diaTrabalho);
    }

    @Override
    public void removerDiaTrabalho(String data) {
        diasTrabalho.remove(data);
    }
}

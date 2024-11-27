package atv_pazi_pizzaria.service;

import atv_pazi_pizzaria.Model.DiaTrabalho;
import atv_pazi_pizzaria.dao.DiaTrabalhoDAO;
import java.util.List;

public class DiaTrabalhoService {
    private DiaTrabalhoDAO diaTrabalhoDAO;

    public DiaTrabalhoService(DiaTrabalhoDAO diaTrabalhoDAO) {
        this.diaTrabalhoDAO = diaTrabalhoDAO;
    }

    public void adicionarDiaTrabalho(DiaTrabalho diaTrabalho) {
        diaTrabalhoDAO.adicionarDiaTrabalho(diaTrabalho);
    }

    public DiaTrabalho obterDiaTrabalhoPorId(String data) {
        return diaTrabalhoDAO.obterDiaTrabalhoPorId(data);
    }

    public List<DiaTrabalho> obterTodosDiasTrabalho() {
        return diaTrabalhoDAO.obterTodosDiasTrabalho();
    }

    public void atualizarDiaTrabalho(DiaTrabalho diaTrabalho) {
        diaTrabalhoDAO.atualizarDiaTrabalho(diaTrabalho);
    }

    public void removerDiaTrabalho(String data) {
        diaTrabalhoDAO.removerDiaTrabalho(data);
    }
}

package atv_pazi_pizzaria.dao;

import atv_pazi_pizzaria.Model.DiaTrabalho;
import java.util.List;


public interface DiaTrabalhoDAO {
    void adicionarDiaTrabalho(DiaTrabalho diaTrabalho);
    DiaTrabalho obterDiaTrabalhoPorId(String data);
    List<DiaTrabalho> obterTodosDiasTrabalho();
    void atualizarDiaTrabalho(DiaTrabalho diaTrabalho);
    void removerDiaTrabalho(String data);
}

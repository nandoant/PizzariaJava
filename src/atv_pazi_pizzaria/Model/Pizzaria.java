package atv_pazi_pizzaria.Model;

import java.util.ArrayList;
import java.util.List;

public class Pizzaria {
    private List<DiaTrabalho> diasDeTrabalho;

    public Pizzaria() {
        diasDeTrabalho = new ArrayList<>();
    }

    public List<DiaTrabalho> getDiasDeTrabalho() {
        return diasDeTrabalho;
    }

    public void setDiasDeTrabalho(List<DiaTrabalho> diasDeTrabalho) {
        this.diasDeTrabalho = diasDeTrabalho;
    }
}

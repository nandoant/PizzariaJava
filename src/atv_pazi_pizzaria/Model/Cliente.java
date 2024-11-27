package atv_pazi_pizzaria.Model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private Integer id;
    private String telefone;
    private String nome;

    public Cliente(Integer id, String telefone, String nome) {
        this.id = id;
        this.telefone = telefone;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

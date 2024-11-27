package atv_pazi_pizzaria.Model;

public abstract class Pizza {
    protected Double preco;
    protected String nome;
    protected String ingredientes;
    protected Integer id;

    public Pizza(Double preco, String nome, String ingredientes, Integer id) {
        this.preco = preco;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}

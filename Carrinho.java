public class Carrinho {
    String nome;
    int quantidade;
    float valor;

    public Carrinho(String nome, int quantidade, float valor) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
    }
    public String getNome() {
        return nome;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public Float getValor() {
        return valor;
    }
    void setValor (float valor) {
        this.valor = valor;
    }
    void setNome (String nome) {
        this.nome = nome;
    }
    void setQuantidade (int quantidade) {
        this.quantidade = quantidade;
    }

}

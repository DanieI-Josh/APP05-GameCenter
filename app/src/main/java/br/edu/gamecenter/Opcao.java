package br.edu.gamecenter;
import java.io.Serializable;
public final class Opcao implements Serializable  {
    private static final long serialVersionUID=1L;
    private final String valor,categoria;
    public Opcao(String valor,String categoria) {
        this.valor=valor;
        this.categoria=categoria;
    }
    public String getValor() {
        return valor;
    }
    public String getCategoria() {
        return categoria;
    }
}

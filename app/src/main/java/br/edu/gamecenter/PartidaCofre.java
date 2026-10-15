package br.edu.gamecenter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
public final class PartidaCofre implements Serializable  {
    private static final long serialVersionUID=1L;
    private final String codigo;
    private final long inicio;
    private long fim;
    private boolean ganhou;
    private final ArrayList<String> tentativas=new ArrayList<>();
    public PartidaCofre(Random random,long agora) {
        ArrayList<Character> digitos=new ArrayList<>();
        for(char c='0';c<='9';c++)digitos.add(c);
        StringBuilder valor=new StringBuilder();
        for(int i=0;i<4;i++)valor.append(digitos.remove(random.nextInt(digitos.size())));
        codigo=valor.toString();
        inicio=agora;
    }
    public static boolean valida(String valor) {
        if(valor==null || !valor.matches("[0-9]{4}"))return false;
        boolean[] usados=new boolean[10];
        for(char digito:valor.toCharArray()) {
            int numero=digito-'0';
            if(usados[numero])return false;
            usados[numero]=true;
        }
        return true;
    }
    public boolean tentar(String valor,long agora) {
        if(terminou()||!valida(valor))return false;
        tentativas.add(valor);
        ganhou=codigo.equals(valor);
        if(terminou())fim=agora;
        return true;
    }
    public static int pista(String codigo,char digito,int posicao) {
        if(codigo.charAt(posicao)==digito)return 2;
        return codigo.indexOf(digito)>=0?1:0;
    }
    public ArrayList<String> getTentativas() {
        return new ArrayList<>(tentativas);
    }
    public String getCodigo() {
        return codigo;
    }
    public boolean ganhou() {
        return ganhou;
    }
    public boolean terminou() {
        return ganhou||tentativas.size()>=8;
    }
    public long segundos() {
        return Math.max(0,fim-inicio)/1000;
    }
}

package br.edu.gamecenter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public final class Sorteador  {
    private Sorteador() {
    }
    public static Opcao sortear(List<Opcao> opcoes,String categoria,Random random) {
        ArrayList<Opcao> candidatas=new ArrayList<>();
        for(Opcao opcao:opcoes)if(opcao.getCategoria().equals(categoria))candidatas.add(opcao);
        return candidatas.isEmpty()?null:candidatas.get(random.nextInt(candidatas.size()));
    }
}

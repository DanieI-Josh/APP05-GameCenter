package br.edu.gamecenter;
public final class RegrasArena  {
    private RegrasArena() {
    }
    /** 0 pedra, 1 papel, 2 tesoura; retorno 1 vitória, 0 empate, -1 derrota. */
    public static int resultado(int jogador,int dispositivo) {
        if(jogador<0||jogador>2||dispositivo<0||dispositivo>2)throw new IllegalArgumentException();
        if(jogador==dispositivo)return 0;
        return (jogador-dispositivo+3)%3==1?1:-1;
    }
    public static boolean terminou(int jogador,int dispositivo) {
        return jogador>=2||dispositivo>=2;
    }
}

package br.edu.gamecenter;
public final class Navegacao  {
    private Navegacao()  {
    }
    public static final String EXTRA_APELIDO="br.edu.gamecenter.APELIDO";
    public static final String EXTRA_MODULO="br.edu.gamecenter.MODULO";
    public static boolean apelidoValido(String valor)  {
        return valor!=null && valor.trim().length()>=3;
    }
    public static boolean moduloValido(String valor)  {
        return "scanner".equals(valor)||"arena".equals(valor)||"cofre".equals(valor)||"randomizador".equals(valor);
    }
    public static int tituloModulo(String valor)  {
        if("scanner".equals(valor))return R.string.scanner;
        if("arena".equals(valor))return R.string.arena;
        if("cofre".equals(valor))return R.string.cofre;
        return R.string.randomizador;
    }
}

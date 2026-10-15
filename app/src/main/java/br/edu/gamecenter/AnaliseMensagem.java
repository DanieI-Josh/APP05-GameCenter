package br.edu.gamecenter;
import java.text.Normalizer;
import java.util.Locale;
public final class AnaliseMensagem  {
    public final int caracteres,palavras,vogais;
    public final String invertido;
    public final boolean palindromo;
    public AnaliseMensagem(String texto) {
        String semEspacos=texto.replaceAll("\\s+","");
        caracteres=semEspacos.codePointCount(0,semEspacos.length());
        String limpo=texto.trim();
        palavras=limpo.isEmpty()?0:limpo.split("\\s+").length;
        String normal=Normalizer.normalize(texto,Normalizer.Form.NFD).replaceAll("\\p{M}","").toLowerCase(Locale.ROOT);
        int n=0;
        for(char c:normal.toCharArray())if("aeiou".indexOf(c)>=0)n++;
        vogais=n;
        invertido=new StringBuilder(texto).reverse().toString();
        String base=semEspacos.toLowerCase(Locale.ROOT);
        palindromo=base.equals(new StringBuilder(base).reverse().toString());
    }
    public static boolean valido(String texto) {
        return texto!=null && texto.trim().length()>=3;
    }
}

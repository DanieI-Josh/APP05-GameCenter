package br.edu.gamecenter;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;
public class RegrasTest  {
    @Test public void apelidos() {
        assertFalse(Navegacao.apelidoValido(null));
        assertFalse(Navegacao.apelidoValido("  ab  "));
        assertTrue(Navegacao.apelidoValido(" Josh "));
    }
    @Test public void scannerExemplo() {
        AnaliseMensagem a=new AnaliseMensagem("Ana");
        assertEquals(3,a.caracteres);
        assertEquals(1,a.palavras);
        assertEquals(2,a.vogais);
        assertEquals("anA",a.invertido);
        assertTrue(a.palindromo);
    }
    @Test public void scannerFrase() {
        AnaliseMensagem a=new AnaliseMensagem("A sacada da casa");
        assertTrue(a.palindromo);
        assertEquals(13,a.caracteres);
        assertEquals(4,a.palavras);
    }
    @Test public void scannerEspacosEQuebras() {
        AnaliseMensagem a=new AnaliseMensagem("  Olá\n  mundo  ");
        assertEquals(8,a.caracteres);
        assertEquals(2,a.palavras);
        assertEquals(4,a.vogais);
        assertFalse(a.palindromo);
    }
    @Test public void scannerNaoRemovePontuacao() {
        assertFalse(new AnaliseMensagem("Ana!").palindromo);
        assertFalse(AnaliseMensagem.valido("  a  "));
    }
    @Test public void todasJogadas() {
        int[][] tabela= {
            {
                0,-1,1
            }
            , {
                1,0,-1
            }
            , {
                -1,1,0
            }
        }
        ;
        for(int a=0;a<3;a++)for(int b=0;b<3;b++)assertEquals(tabela[a][b],RegrasArena.resultado(a,b));
    }
    @Test public void melhorDeTres() {
        assertFalse(RegrasArena.terminou(1,1));
        assertTrue(RegrasArena.terminou(2,0));
        assertTrue(RegrasArena.terminou(0,2));
    }
    @Test public void palpiteValido() {
        for(String p:new String[] {
            "","123","12345","1123","abcd","１２３４"," 1234"
        }
        )assertFalse(p,PartidaCofre.valida(p));
        assertTrue(PartidaCofre.valida("0427"));
    }
    @Test public void geracaoSemRepeticoes() {
        boolean comZero=false;
        for(int i=0;i<1000;i++) {
            String c=new PartidaCofre(new Random(i),0).getCodigo();
            assertTrue(PartidaCofre.valida(c));
            if(c.startsWith("0"))comZero=true;
        }
        assertTrue(comZero);
    }
    @Test public void pistasExemplo() {
        assertEquals(2,PartidaCofre.pista("4271",'4',0));
        assertEquals(2,PartidaCofre.pista("4271",'2',1));
        assertEquals(1,PartidaCofre.pista("4271",'1',2));
        assertEquals(0,PartidaCofre.pista("4271",'9',3));
    }
    @Test public void vitoriaEInvalidos() {
        PartidaCofre p=new PartidaCofre(new Random(1),1000);
        assertFalse(p.tentar("1111",2000));
        assertEquals(0,p.getTentativas().size());
        assertTrue(p.tentar(p.getCodigo(),5100));
        assertTrue(p.ganhou());
        assertEquals(4,p.segundos());
        assertFalse(p.tentar("1234",6000));
        assertEquals(1,p.getTentativas().size());
    }
    @Test public void derrotaOitoTentativas() {
        PartidaCofre p=new PartidaCofre(new Random(1),0);
        String errado=p.getCodigo().equals("1234")?"5678":"1234";
        for(int i=0;i<8;i++)assertTrue(p.tentar(errado,1000L*(i+1)));
        assertTrue(p.terminou());
        assertFalse(p.ganhou());
        assertFalse(p.tentar(p.getCodigo(),9000));
        assertEquals(8,p.segundos());
        assertEquals(8,p.getTentativas().size());
    }
    @Test public void copiaHistoricoNaoAlteraPartida() {
        PartidaCofre p=new PartidaCofre(new Random(1),0);
        p.tentar("1234",10);
        p.getTentativas().clear();
        assertEquals(1,p.getTentativas().size());
    }
    @Test public void estadoSerializavel()throws Exception {
        PartidaCofre p=new PartidaCofre(new Random(7),123);
        p.tentar("0123",500);
        ByteArrayOutputStream b=new ByteArrayOutputStream();
        new ObjectOutputStream(b).writeObject(p);
        PartidaCofre copia=(PartidaCofre)new ObjectInputStream(new ByteArrayInputStream(b.toByteArray())).readObject();
        assertEquals(p.getCodigo(),copia.getCodigo());
        assertEquals(p.getTentativas(),copia.getTentativas());
    }
    @Test public void sorteioFiltraCategoria() {
        Opcao filme=new Opcao("Filme A","Filmes"),comida=new Opcao("Pizza","Comidas");
        List<Opcao> itens=Arrays.asList(filme,comida);
        for(int i=0;i<100;i++)assertSame(comida,Sorteador.sortear(itens,"Comidas",new Random(i)));
        assertNull(Sorteador.sortear(itens,"Lugares",new Random()));
        assertNull(Sorteador.sortear(Collections.emptyList(),"Filmes",new Random()));
    }
}

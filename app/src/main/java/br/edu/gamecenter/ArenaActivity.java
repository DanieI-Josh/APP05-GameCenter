package br.edu.gamecenter;
import android.os.Bundle;
import android.media.MediaPlayer;
import java.util.Random;
import br.edu.gamecenter.databinding.ActivityArenaBinding;
public class ArenaActivity extends TelaActivity  {
    private ActivityArenaBinding binding;
    private MediaPlayer somVitoria,somDerrota,somEmpate;
    private final Random random=new Random();
    private int pontosJogador,pontosDispositivo,jogadaJogador=-1,jogadaDispositivo=-1;
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        binding=ActivityArenaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preparar(binding.getRoot());
        if(!receberApelido())return;
        somVitoria=MediaPlayer.create(this,R.raw.vitoria);
        somDerrota=MediaPlayer.create(this,R.raw.derrota);
        somEmpate=MediaPlayer.create(this,R.raw.sorteio);
        configurarRetorno(binding.buttonVoltar,binding.buttonEncerrar,"arena");
        if(state!=null) {
            pontosJogador=state.getInt("pontosJogador");
            pontosDispositivo=state.getInt("pontosDispositivo");
            jogadaJogador=state.getInt("jogadaJogador",-1);
            jogadaDispositivo=state.getInt("jogadaDispositivo",-1);
        }
        binding.buttonPedra.setOnClickListener(view->jogar(0));
        binding.buttonPapel.setOnClickListener(view->jogar(1));
        binding.buttonTesoura.setOnClickListener(view->jogar(2));
        binding.buttonNova.setOnClickListener(view-> {
            pontosJogador=0;pontosDispositivo=0;jogadaJogador=-1;jogadaDispositivo=-1;pausarSons();atualizar();
        }
        );
        atualizar();
    }
    private void jogar(int escolha) {
        if(RegrasArena.terminou(pontosJogador,pontosDispositivo))return;
        jogadaJogador=escolha;
        jogadaDispositivo=random.nextInt(3);
        int resultado=RegrasArena.resultado(escolha,jogadaDispositivo);
        if(resultado>0)pontosJogador++;
        else if(resultado<0)pontosDispositivo++;
        atualizar();
        pausarSons();
        Efeitos.tocar(resultado>0?somVitoria:resultado<0?somDerrota:somEmpate);
        Efeitos.mostrar(binding.imageJogador);
        Efeitos.mostrar(binding.imageDispositivo);
        Efeitos.mostrar(binding.textResultado);
    }
    private int nome(int jogada) {
        return new int[] {
            R.string.pedra,R.string.papel,R.string.tesoura
        }
        [jogada];
    }
    private int imagem(int jogada) {
        return new int[] {
            R.drawable.ic_pedra,R.drawable.ic_papel,R.drawable.ic_tesoura
        }
        [jogada];
    }
    private void atualizar() {
        binding.textPlacar.setText(getString(R.string.placar,pontosJogador,pontosDispositivo));
        boolean fim=RegrasArena.terminou(pontosJogador,pontosDispositivo);
        binding.buttonPedra.setEnabled(!fim);
        binding.buttonPapel.setEnabled(!fim);
        binding.buttonTesoura.setEnabled(!fim);
        if(jogadaJogador<0) {
            binding.textJogadas.setText(R.string.aguardando);
            binding.textResultado.setText(R.string.aguardando);
            binding.imageJogador.setImageResource(R.drawable.ic_game);
            binding.imageDispositivo.setImageResource(R.drawable.ic_game);
            binding.imageJogador.setContentDescription(getString(R.string.aguardando));
            binding.imageDispositivo.setContentDescription(getString(R.string.aguardando));
            return;
        }
        binding.imageJogador.setImageResource(imagem(jogadaJogador));
        binding.imageDispositivo.setImageResource(imagem(jogadaDispositivo));
        binding.imageJogador.setContentDescription(getString(nome(jogadaJogador)));
        binding.imageDispositivo.setContentDescription(getString(nome(jogadaDispositivo)));
        binding.textJogadas.setText(getString(R.string.jogadas,getString(nome(jogadaJogador)),getString(nome(jogadaDispositivo))));
        int resultado=RegrasArena.resultado(jogadaJogador,jogadaDispositivo);
        binding.textResultado.setText(fim?(pontosJogador>=2?R.string.vitoria_partida:R.string.derrota_partida):(resultado>0?R.string.vitoria_rodada:resultado<0?R.string.derrota_rodada:R.string.empate));
    }
    private void pausarSons() {
        Efeitos.pausar(somVitoria);
        Efeitos.pausar(somDerrota);
        Efeitos.pausar(somEmpate);
    }
    @Override protected void onPause() {
        pausarSons();
        super.onPause();
    }
    @Override protected void onDestroy() {
        if(somVitoria!=null) {
            somVitoria.release();
            somVitoria=null;
        }
        if(somDerrota!=null) {
            somDerrota.release();
            somDerrota=null;
        }
        if(somEmpate!=null) {
            somEmpate.release();
            somEmpate=null;
        }
        super.onDestroy();
    }
    @Override protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putInt("pontosJogador",pontosJogador);
        state.putInt("pontosDispositivo",pontosDispositivo);
        state.putInt("jogadaJogador",jogadaJogador);
        state.putInt("jogadaDispositivo",jogadaDispositivo);
    }
}

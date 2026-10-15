package br.edu.gamecenter;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.Random;
import br.edu.gamecenter.databinding.ActivityRandomizadorBinding;
public class RandomizadorActivity extends TelaActivity  {
    private ActivityRandomizadorBinding binding;
    private ArrayList<Opcao> opcoes;
    private OpcaoAdapter adapter;
    private MediaPlayer somSorteio;
    private boolean grade;
    private String resultado;
    private final Random random=new Random();
    @SuppressWarnings( {
        "unchecked","deprecation"
    }
    ) @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        binding=ActivityRandomizadorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preparar(binding.getRoot());
        if(!receberApelido())return;
        opcoes=new ArrayList<>();
        if(state!=null) {
            ArrayList<Opcao> salvas=(ArrayList<Opcao>)state.getSerializable("opcoes");
            if(salvas!=null)opcoes=salvas;
            grade=state.getBoolean("grade");
            resultado=state.getString("resultado");
        }
        else  {
            opcoes.add(new Opcao(getString(R.string.ex_filme),getString(R.string.cat_filmes)));
            opcoes.add(new Opcao(getString(R.string.ex_comida),getString(R.string.cat_comidas)));
            opcoes.add(new Opcao(getString(R.string.ex_lugar),getString(R.string.cat_lugares)));
            opcoes.add(new Opcao(getString(R.string.ex_desafio),getString(R.string.cat_desafios)));
        }
        ArrayAdapter<CharSequence> categorias=ArrayAdapter.createFromResource(this,R.array.categorias,android.R.layout.simple_spinner_item);
        categorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerCategoria.setAdapter(categorias);
        adapter=new OpcaoAdapter(opcoes,this::remover);
        binding.recyclerOpcoes.setAdapter(adapter);
        atualizarLayout();
        atualizarQuantidade();
        if(resultado!=null)binding.textSorteado.setText(resultado);
        somSorteio=MediaPlayer.create(this,R.raw.sorteio);
        configurarRetorno(binding.buttonVoltar,binding.buttonEncerrar,"randomizador");
        binding.buttonAdicionar.setOnClickListener(view->adicionar());
        binding.buttonSortear.setOnClickListener(view->sortear());
        binding.buttonAlternar.setOnClickListener(view-> {
            grade=!grade;atualizarLayout();
        }
        );
    }
    private void adicionar() {
        String valor=binding.editValor.getText().toString().trim();
        if(valor.isEmpty()) {
            binding.editValor.setError(getString(R.string.erro_valor));
            binding.editValor.requestFocus();
            return;
        }
        binding.editValor.setError(null);
        opcoes.add(new Opcao(valor,binding.spinnerCategoria.getSelectedItem().toString()));
        int posicao=opcoes.size()-1;
        adapter.notifyItemInserted(posicao);
        binding.recyclerOpcoes.scrollToPosition(posicao);
        binding.editValor.setText("");
        esconderTeclado();
        atualizarQuantidade();
    }
    private void remover(Opcao opcao) {
        int posicao=opcoes.indexOf(opcao);
        if(posicao<0)return;
        opcoes.remove(posicao);
        adapter.notifyItemRemoved(posicao);
        atualizarQuantidade();
        Toast.makeText(this,getString(R.string.removido,opcao.getValor()),Toast.LENGTH_SHORT).show();
    }
    private void sortear() {
        esconderTeclado();
        String categoria=binding.spinnerCategoria.getSelectedItem().toString();
        Opcao escolhida=Sorteador.sortear(opcoes,categoria,random);
        if(escolhida==null) {
            resultado=getString(R.string.categoria_vazia);
            binding.textSorteado.setText(resultado);
            return;
        }
        resultado=getString(R.string.sorteado,categoria,escolhida.getValor());
        binding.textSorteado.setText(resultado);
        Efeitos.mostrar(binding.textSorteado);
        Efeitos.tocar(somSorteio);
    }
    private void atualizarLayout() {
        binding.recyclerOpcoes.setLayoutManager(grade?new GridLayoutManager(this,2):new LinearLayoutManager(this));
        binding.buttonAlternar.setText(grade?R.string.exibir_lista:R.string.exibir_grade);
    }
    private void atualizarQuantidade() {
        binding.textQuantidade.setText(getString(R.string.total_itens,opcoes.size()));
        binding.textVazio.setVisibility(opcoes.isEmpty()?View.VISIBLE:View.GONE);
    }
    @Override protected void onPause() {
        Efeitos.pausar(somSorteio);
        super.onPause();
    }
    @Override protected void onDestroy() {
        if(somSorteio!=null) {
            somSorteio.release();
            somSorteio=null;
        }
        super.onDestroy();
    }
    @Override protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putSerializable("opcoes",opcoes);
        state.putBoolean("grade",grade);
        state.putString("resultado",resultado);
    }
}

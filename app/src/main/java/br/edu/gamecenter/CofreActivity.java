package br.edu.gamecenter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import java.util.Random;
import br.edu.gamecenter.databinding.ActivityCofreBinding;
public class CofreActivity extends TelaActivity  {
    private ActivityCofreBinding binding;
    private PartidaCofre partida;
    @SuppressWarnings("deprecation") @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        binding=ActivityCofreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preparar(binding.getRoot());
        if(!receberApelido())return;
        if(state!=null)partida=(PartidaCofre)state.getSerializable("partida");
        if(partida==null)partida=new PartidaCofre(new Random(),System.currentTimeMillis());
        configurarRetorno(binding.buttonVoltar,binding.buttonEncerrar,"cofre");
        binding.buttonTentar.setOnClickListener(view-> {
            String valor=binding.editPalpite.getText().toString().trim();if(!PartidaCofre.valida(valor)) {
                binding.editPalpite.setError(getString(R.string.erro_palpite));binding.editPalpite.requestFocus();return;
            }
            if(partida.tentar(valor,System.currentTimeMillis())) {
                binding.editPalpite.setText("");binding.editPalpite.setError(null);atualizar();if(partida.terminou())Efeitos.mostrar(binding.textResultado);
            }
        }
        );
        binding.buttonNova.setOnClickListener(view-> {
            partida=new PartidaCofre(new Random(),System.currentTimeMillis());binding.editPalpite.setText("");binding.editPalpite.setError(null);atualizar();
        }
        );
        atualizar();
    }
    private void atualizar() {
        binding.layoutHistorico.removeAllViews();
        int numero=0;
        int margem=getResources().getDimensionPixelSize(R.dimen.espaco);
        for(String tentativa:partida.getTentativas()) {
            TextView rotulo=new TextView(this);
            rotulo.setText(getString(R.string.numero_tentativa,++numero));
            rotulo.setTextColor(ContextCompat.getColor(this,R.color.texto));
            binding.layoutHistorico.addView(rotulo);
            LinearLayout linha=new LinearLayout(this);
            linha.setOrientation(LinearLayout.HORIZONTAL);
            linha.setPadding(0,0,0,margem);
            for(int i=0;i<4;i++) {
                int pista=PartidaCofre.pista(partida.getCodigo(),tentativa.charAt(i),i);
                TextView digito=new TextView(this);
                String valor=String.valueOf(tentativa.charAt(i));
                digito.setText(getString(new int[] {
                    R.string.celula_ausente,R.string.celula_deslocada,R.string.celula_correta
                }
                [pista],valor));
                digito.setTextSize(20);
                digito.setGravity(Gravity.CENTER);
                digito.setPadding(margem,margem,margem,margem);
                digito.setTextColor(ContextCompat.getColor(this,R.color.branco));
                digito.setBackgroundColor(ContextCompat.getColor(this,new int[] {
                    R.color.ausente,R.color.deslocado,R.color.correto
                }
                [pista]));
                digito.setContentDescription(getString(new int[] {
                    R.string.digito_ausente,R.string.digito_deslocado,R.string.digito_correto
                }
                [pista],valor));
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1);
                params.setMargins(2,2,2,2);
                linha.addView(digito,params);
            }
            binding.layoutHistorico.addView(linha);
        }
        binding.textHistoricoVazio.setVisibility(numero==0?View.VISIBLE:View.GONE);
        binding.textTentativas.setText(getString(R.string.tentativas,numero));
        boolean fim=partida.terminou();
        binding.buttonTentar.setEnabled(!fim);
        binding.editPalpite.setEnabled(!fim);
        if(fim) {
            long segundos=partida.segundos();
            binding.textResultado.setText(getString(R.string.cofre_fim,getString(partida.ganhou()?R.string.cofre_ganhou:R.string.cofre_perdeu),partida.getCodigo(),numero,segundos/60,segundos%60));
        }
        else binding.textResultado.setText(R.string.cofre_iniciado);
    }
    @Override protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putSerializable("partida",partida);
    }
}

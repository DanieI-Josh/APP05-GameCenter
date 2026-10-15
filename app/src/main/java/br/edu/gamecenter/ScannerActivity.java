package br.edu.gamecenter;
import android.os.Bundle;
import br.edu.gamecenter.databinding.ActivityScannerBinding;
public class ScannerActivity extends TelaActivity  {
    private ActivityScannerBinding binding;
    private String analisado;
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        binding=ActivityScannerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preparar(binding.getRoot());
        if(!receberApelido())return;
        configurarRetorno(binding.buttonVoltar,binding.buttonEncerrar,"scanner");
        if(state!=null) {
            analisado=state.getString("analisado");
            if(analisado!=null)mostrar(analisado,false);
        }
        binding.buttonAnalisar.setOnClickListener(view-> {
            String texto=binding.editMensagem.getText().toString();if(!AnaliseMensagem.valido(texto)) {
                binding.editMensagem.setError(getString(R.string.erro_texto));binding.editMensagem.requestFocus();return;
            }
            binding.editMensagem.setError(null);analisado=texto;mostrar(texto,true);
        }
        );
        binding.buttonLimpar.setOnClickListener(view-> {
            binding.editMensagem.setText("");binding.editMensagem.setError(null);analisado=null;binding.textResultado.setText(R.string.resultado_vazio);binding.editMensagem.requestFocus();
        }
        );
    }
    private void mostrar(String texto,boolean animar) {
        AnaliseMensagem a=new AnaliseMensagem(texto);
        binding.textResultado.setText(getString(R.string.resultado_scanner,a.caracteres,a.palavras,a.vogais,a.invertido,getString(a.palindromo?R.string.sim:R.string.nao)));
        if(animar)Efeitos.mostrar(binding.textResultado);
    }
    @Override protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putString("analisado",analisado);
    }
}

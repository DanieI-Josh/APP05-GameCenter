package br.edu.gamecenter;
import android.content.Intent;
import android.os.Bundle;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import br.edu.gamecenter.databinding.ActivityMainBinding;
public class MainActivity extends TelaActivity  {
    private ActivityMainBinding binding;
    private String ultimoModulo;
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preparar(binding.getRoot());
        if(state!=null)ultimoModulo=state.getString("ultimoModulo");
        mostrarUltimo();
        binding.buttonEntrar.setOnClickListener(view-> {
            String nome=binding.editApelido.getText().toString().trim(); if(!Navegacao.apelidoValido(nome)) {
                binding.editApelido.setError(getString(R.string.erro_apelido));binding.editApelido.requestFocus();return;
            }
            binding.editApelido.setError(null);apelido=nome;binding.buttonEntrar.setEnabled(false); binding.buttonEntrar.setScaleX(0.94f);binding.buttonEntrar.setScaleY(0.94f);binding.buttonEntrar.setAlpha(0.5f); binding.buttonEntrar.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(350).setListener(new AnimatorListenerAdapter() {
                private boolean cancelada; @Override public void onAnimationCancel(Animator animation) {
                    cancelada=true;
                }
                @Override public void onAnimationEnd(Animator animation) {
                    binding.buttonEntrar.animate().setListener(null);binding.buttonEntrar.setEnabled(true);if(!cancelada && !isFinishing())abrir(CentralActivity.class);
                }
            }
            ).start();
        }
        );
    }
    private void mostrarUltimo() {
        binding.textUltimoModulo.setText(Navegacao.moduloValido(ultimoModulo)?getString(R.string.ultimo_modulo,getString(Navegacao.tituloModulo(ultimoModulo))):getString(R.string.ultimo_vazio));
    }
    @Override protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        registrar("onNewIntent");
        setIntent(intent);
        String modulo=intent.getStringExtra(Navegacao.EXTRA_MODULO);
        if(Navegacao.moduloValido(modulo))ultimoModulo=modulo;
        mostrarUltimo();
    }
    @Override protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putString("ultimoModulo",ultimoModulo);
    }
    @Override protected void onPause() {
        binding.buttonEntrar.animate().cancel();
        super.onPause();
    }
}

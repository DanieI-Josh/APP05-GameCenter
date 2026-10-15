package br.edu.gamecenter;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import br.edu.gamecenter.databinding.ActivityCentralBinding;
public class CentralActivity extends TelaActivity  {
    private ActivityCentralBinding binding;
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        binding=ActivityCentralBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preparar(binding.getRoot());
        if(!receberApelido())return;
        binding.textSaudacao.setText(getString(R.string.ola,apelido));
        binding.buttonScanner.setOnClickListener(view->abrir(ScannerActivity.class));
        binding.buttonArena.setOnClickListener(view->abrir(ArenaActivity.class));
        binding.buttonCofre.setOnClickListener(view->abrir(CofreActivity.class));
        binding.buttonRandomizador.setOnClickListener(view->abrir(RandomizadorActivity.class));
        binding.buttonVoltar.setOnClickListener(view->finish());
        binding.imageScanner.setContentDescription(getString(R.string.modulo_desc,getString(R.string.scanner)));
        binding.imageArena.setContentDescription(getString(R.string.modulo_desc,getString(R.string.arena)));
        binding.imageCofre.setContentDescription(getString(R.string.modulo_desc,getString(R.string.cofre)));
        binding.imageRandomizador.setContentDescription(getString(R.string.modulo_desc,getString(R.string.randomizador)));
        binding.textTitulo.startAnimation(AnimationUtils.loadAnimation(this,R.anim.entrada));
    }
}

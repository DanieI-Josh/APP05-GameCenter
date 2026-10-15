package br.edu.gamecenter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
/** Comportamento compartilhado; classe abstrata, não é uma tela adicional. */
public abstract class TelaActivity extends AppCompatActivity  {
    protected String apelido;
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        registrar("onCreate");
    }
    protected void preparar(View raiz) {
        WindowCompat.setDecorFitsSystemWindows(getWindow(),false);
        int margem=getResources().getDimensionPixelSize(R.dimen.margem);
        ViewCompat.setOnApplyWindowInsetsListener(raiz,(view,insets)-> {
            Insets bordas=insets.getInsets(WindowInsetsCompat.Type.systemBars()|WindowInsetsCompat.Type.ime()|WindowInsetsCompat.Type.displayCutout()); view.setPadding(margem+bordas.left,margem+bordas.top,margem+bordas.right,margem+bordas.bottom);return insets;
        }
        );
        ViewCompat.requestApplyInsets(raiz);
    }
    protected boolean receberApelido() {
        apelido=getIntent().getStringExtra(Navegacao.EXTRA_APELIDO);
        if(!Navegacao.apelidoValido(apelido)) {
            Toast.makeText(this,R.string.dados_invalidos,Toast.LENGTH_SHORT).show();
            finish();
            return false;
        }
        apelido=apelido.trim();
        return true;
    }
    protected void configurarRetorno(View voltar,View encerrar,String modulo) {
        voltar.setOnClickListener(view->finish());
        encerrar.setOnClickListener(view-> {
            Intent intent=new Intent(this,MainActivity.class); intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP); intent.putExtra(Navegacao.EXTRA_MODULO,modulo);startActivity(intent);
        }
        );
    }
    protected void abrir(Class<?> destino) {
        esconderTeclado();
        Intent intent=new Intent(this,destino);
        intent.putExtra(Navegacao.EXTRA_APELIDO,apelido);
        startActivity(intent);
    }
    protected void esconderTeclado() {
        View foco=getCurrentFocus();
        if(foco!=null) {
            InputMethodManager teclado=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            if(teclado!=null)teclado.hideSoftInputFromWindow(foco.getWindowToken(),0);
            foco.clearFocus();
        }
    }
    protected void registrar(String evento) {
        Log.d("Ciclo"+getClass().getSimpleName(),evento);
    }
    @Override protected void onResume() {
        super.onResume();
        registrar("onResume");
    }
    @Override protected void onStop() {
        registrar("onStop");
        super.onStop();
    }
    @Override protected void onDestroy() {
        registrar("onDestroy");
        super.onDestroy();
    }
}

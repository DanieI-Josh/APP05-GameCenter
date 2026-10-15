package br.edu.gamecenter;
import android.media.MediaPlayer;
import android.view.View;
public final class Efeitos  {
    private Efeitos() {
    }
    public static void mostrar(View view) {
        view.animate().cancel();
        view.setAlpha(0.3f);
        view.setScaleX(0.94f);
        view.setScaleY(0.94f);
        view.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(400).start();
    }
    public static void tocar(MediaPlayer player) {
        if(player!=null) {
            player.pause();
            player.seekTo(0);
            player.start();
        }
    }
    public static void pausar(MediaPlayer player) {
        if(player!=null && player.isPlaying())player.pause();
    }
}

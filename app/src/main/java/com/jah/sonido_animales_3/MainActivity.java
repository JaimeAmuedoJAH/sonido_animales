package com.jah.sonido_animales_3;

import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    ImageView img1, img2, img3, img4, img5, img6, imgFondo;
    MaterialToolbar mtbMenu;
    int[] arrImagenes;
    ImageView[] arrImageViews;
    MediaPlayer mediaPlayer;
    SoundPool soundPool;
    int[] arrSonidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        setSupportActionBar(mtbMenu);
        asignarImagenes(1);
        img1.setOnClickListener(view -> reproducirSonido(arrSonidos[0]));
        img2.setOnClickListener(view -> reproducirSonido(arrSonidos[1]));
        img3.setOnClickListener(view -> reproducirSonido(arrSonidos[2]));
        img4.setOnClickListener(view -> reproducirSonido(arrSonidos[3]));
        img5.setOnClickListener(view -> reproducirSonido(arrSonidos[4]));
        img6.setOnClickListener(view -> reproducirSonido(arrSonidos[5]));
    }

    private void reproducirSonido(int sonido) {
        soundPool.play(sonido, 1, 1, 0, 0, 1);
    }

    private void asignarImagenes(int tipo) {
        if(tipo == 1){
            arrImagenes = new int[]{
                    R.drawable.cerdo, R.drawable.conejo, R.drawable.perro, R.drawable.gato, R.drawable.vaca, R.drawable.pollo};
            imgFondo.setImageResource(R.drawable.granja);
            arrSonidos = new int[]{
                    soundPool.load(this, R.raw.cerdo, 1),
                    soundPool.load(this, R.raw.conejo, 1),
                    soundPool.load(this, R.raw.perro, 1),
                    soundPool.load(this, R.raw.gato, 1),
                    soundPool.load(this, R.raw.vaca, 1),
                    soundPool.load(this, R.raw.pollo, 1)};
        }else if(tipo == 2){
            arrImagenes = new int[]{
                    R.drawable.elefante, R.drawable.hipopotamo, R.drawable.jirafa, R.drawable.leon, R.drawable.tigre, R.drawable.rinoceronte};
            imgFondo.setImageResource(R.drawable.selva);
            arrSonidos = new int[]{
                    soundPool.load(this, R.raw.elefante, 1),
                    soundPool.load(this, R.raw.hipopotamo, 1),
                    soundPool.load(this, R.raw.jirafa, 1),
                    soundPool.load(this, R.raw.leon, 1),
                    soundPool.load(this, R.raw.tigre, 1),
                    soundPool.load(this, R.raw.rinoceronte, 1)};
        }else {
            arrImagenes = new int[]{
                    R.drawable.paloma, R.drawable.loro, R.drawable.aguila, R.drawable.golondrina, R.drawable.carpintero, R.drawable.buho};
            imgFondo.setImageResource(R.drawable.cielo);
            arrSonidos = new int[]{
                    soundPool.load(this, R.raw.paloma, 1),
                    soundPool.load(this, R.raw.loro, 1),
                    soundPool.load(this, R.raw.aguila, 1),
                    soundPool.load(this, R.raw.golondrina, 1),
                    soundPool.load(this, R.raw.carpintero, 1),
                    soundPool.load(this, R.raw.buho, 1)};
        }

        for(int i = 0;i < arrImagenes.length;i++){
            arrImageViews[i].setImageResource(arrImagenes[i]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_domesticos){
            asignarImagenes(1);
        }else if(item.getItemId() == R.id.item_salvajes){
            asignarImagenes(2);
        }else if(item.getItemId() == R.id.item_aves){
            asignarImagenes(3);
        }else if(item.getItemId() == R.id.item_salir){
            createDialogSalir();
        }
        return false;
    }

    private void createDialogSalir() {
        new AlertDialog.Builder(this)
                .setTitle("Salir del programa")
                .setMessage("¿Quieres salir del programa?")
                .setNegativeButton("No", null)
                .setPositiveButton("Si", (dialogInterface, i) -> {
                    mediaPlayer.stop();
                    finishAffinity();
                })
                .create()
                .show();
    }

    private void initComponents() {
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        imgFondo = findViewById(R.id.imgFondo);
        mtbMenu = findViewById(R.id.mtbMenu);
        arrImageViews = new ImageView[]{img1, img2, img3, img4, img5, img6};
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.to_ashes_and_blood);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        soundPool = new SoundPool.Builder().setMaxStreams(6).build();
    }
}
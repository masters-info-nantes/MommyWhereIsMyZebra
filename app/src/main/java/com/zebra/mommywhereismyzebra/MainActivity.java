package com.zebra.mommywhereismyzebra;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Context;
import android.graphics.Paint;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.util.Random;

public class MainActivity extends Activity {

    private Button ouvrirVideo;
    private Button prendreVideo;

    private ImageButton crayon;
    private ImageButton gomme;
    private ImageButton couleur;

    private CustomView zoneDessin;
    private FrameLayout l;

    boolean listeAffiche;
    boolean boutonsAffiche;

    int couleurCourante;

    private HorizontalScrollView mesImages;
    private HorizontalScrollView mesboutons;

    private SeekBar taille;

    private MenuItem settings;


    private ImageButton afficherMesImages;
    private ImageButton afficherMesBoutons;

    private LinearLayout g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        couleurCourante = Color.BLACK;

        setContentView(R.layout.activity_main);

        ouvrirVideo = (Button) findViewById(R.id.ouvrirVideo);
        prendreVideo = (Button) findViewById(R.id.prendfeVideo);

        crayon = (ImageButton) findViewById(R.id.crayon);
        gomme = (ImageButton) findViewById(R.id.gomme);
        couleur = (ImageButton) findViewById(R.id.couleur);

        taille = (SeekBar)findViewById(R.id.taille);

        zoneDessin = (CustomView)findViewById(R.id.view);
        l = (FrameLayout)findViewById(R.id.layout);

        mesImages = (HorizontalScrollView)findViewById(R.id.maliste);
        mesboutons = (HorizontalScrollView)findViewById(R.id.mesBoutons);

        afficherMesImages = (ImageButton)findViewById(R.id.imageButton);
        afficherMesBoutons = (ImageButton)findViewById(R.id.affciherBoutons);

        listeAffiche = false;
        boutonsAffiche = false;

        View.OnClickListener cl = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ouvrirVideo.setVisibility(View.INVISIBLE);
                prendreVideo.setVisibility(View.INVISIBLE);

                crayon.setVisibility(View.VISIBLE);
                gomme.setVisibility(View.VISIBLE);
                couleur.setVisibility(View.VISIBLE);
                taille.setVisibility(View.VISIBLE);

                l.setVisibility(View.VISIBLE);
                zoneDessin.setVisibility(View.VISIBLE);
            }
        };

        View.OnClickListener cl2 = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent, 101);
            }
        };


        ouvrirVideo.setOnClickListener(cl);
        prendreVideo.setOnClickListener(cl2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        settings = (MenuItem)menu.findItem(R.id.action_settings);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void afficherliste(View v){
        if(listeAffiche){
            mesImages.setVisibility(View.INVISIBLE);
            afficherMesImages.setY(afficherMesImages.getY() + 210);
            v.invalidate();
            listeAffiche = false;
            afficherMesImages.setImageResource(R.mipmap.up);
            //@android:drawable/arrow_up_float
        }
        else {
            listeAffiche = true;
            mesImages.setVisibility(View.VISIBLE);
            afficherMesImages.setY(afficherMesImages.getY() - 210);
            v.invalidate();
            afficherMesImages.setImageResource(R.mipmap.down);

        }
    }

    public void afficherBoutons(View v){
        if(boutonsAffiche){
            mesboutons.setVisibility(View.INVISIBLE);
            afficherMesBoutons.setY(afficherMesBoutons.getY() - 120);
            v.invalidate();
            boutonsAffiche = false;
            afficherMesBoutons.setImageResource(R.mipmap.down);
            //@android:drawable/arrow_up_float
        }
        else {
            boutonsAffiche = true;
            mesboutons.setVisibility(View.VISIBLE);
            afficherMesBoutons.setY(afficherMesBoutons.getY() + 120);
            v.invalidate();
            afficherMesBoutons.setImageResource(R.mipmap.up);

        }
    }

    public void changerCouleur(View v) {

        ColorPickerDialog p = new ColorPickerDialog(this,new ColorPickerDialog.OnColorChangedListener() {
            @Override
            public void colorChanged(int color) {
                couleur.setBackgroundColor(color);
                zoneDessin.setColor(color);
                couleurCourante = color;
            }
        }, Color.BLACK);
        p.show();
    }

    public void utiliserCrayon(View v){
        zoneDessin.setColor(couleurCourante);
    }

    public void utiliserGomme(View v){
        zoneDessin.setColor(Color.TRANSPARENT);
    }

}

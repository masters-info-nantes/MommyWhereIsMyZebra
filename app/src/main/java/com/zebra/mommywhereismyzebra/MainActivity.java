package com.zebra.mommywhereismyzebra;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.graphics.Paint;
import android.widget.FrameLayout;

import java.util.Random;

public class MainActivity extends Activity {

    private Button ouvrirVideo;
    private Button prendreVideo;

    private Button crayon;
    private Button gomme;
    private Button couleur;
    private Button taille;

    private CustomView zoneDessin;
    private FrameLayout l;

    private MenuItem afficherDessin;
    private MenuItem afficherImage;
    private MenuItem afficherCalque;
    private MenuItem flecheDroite;
    private MenuItem flecheGauche;
    private MenuItem settings;
    private MenuItem oeil;
    private MenuItem undo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ouvrirVideo = (Button) findViewById(R.id.ouvrirVideo);
        prendreVideo = (Button) findViewById(R.id.prendfeVideo);

        crayon = (Button) findViewById(R.id.crayon);
        gomme = (Button) findViewById(R.id.gomme);
        couleur = (Button) findViewById(R.id.couleur);
        taille = (Button) findViewById(R.id.taille);

        zoneDessin = (CustomView)findViewById(R.id.view);
        l = (FrameLayout)findViewById(R.id.layout);


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

                afficherDessin.setVisible(true);
                afficherImage.setVisible(true);
                afficherCalque.setVisible(true);
                flecheGauche.setVisible(true);
                flecheDroite.setVisible(true);
                oeil.setVisible(true);
                undo.setVisible(true);
            }
        };


        ouvrirVideo.setOnClickListener(cl);
        prendreVideo.setOnClickListener(cl);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        afficherDessin = (MenuItem)menu.findItem(R.id.afficher_dessin);
        afficherCalque = (MenuItem)menu.findItem(R.id.afficher_pelures);
        afficherImage = (MenuItem)menu.findItem(R.id.afficher_image);
        flecheDroite = (MenuItem)menu.findItem(R.id.f_droite);
        flecheGauche = (MenuItem)menu.findItem(R.id.f_gauche);
        settings = (MenuItem)menu.findItem(R.id.action_settings);
        oeil = (MenuItem)menu.findItem(R.id.previsualisation_rapide);
        undo = (MenuItem)menu.findItem(R.id.undo);

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

}

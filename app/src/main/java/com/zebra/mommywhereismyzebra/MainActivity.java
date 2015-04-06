package com.zebra.mommywhereismyzebra;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ImageButton crayon;
    private ImageButton gomme;
    private ImageButton couleur;

    private CustomView zoneDessin;
    private FrameLayout layoutZoneDessin;

    private boolean listeAffiche;
    private boolean boutonsAffiche;

    private int couleurCourante;
    private int index;

    private HorizontalScrollView mesImages;
    private HorizontalScrollView mesboutons;

    private SeekBar taille;

    private ImageButton afficherDessin;
    private ImageButton afficherCalque;
    private ImageButton afficherImages;

    private ImageButton afficherMesImages;
    private ImageButton afficherMesBoutons;

    private LinearLayout g;
    private RelativeLayout imageFond;

    private AbsoluteLayout outils;
    private AbsoluteLayout travail;

    private List<ImageView> pelureOignons;

    private boolean modeDroitier = true;
    private int nbPeluresOignons = 5;
    ViewTailleCrayon affichageTailleCrayon;

    int nbImages = 10;
    int frequencePelures = 1;
    boolean afficherImage = false;
    boolean droitier = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pelureOignons = new ArrayList<ImageView>();
        couleurCourante = Color.BLACK;
        setContentView(R.layout.activity_main);

        crayon = (ImageButton) findViewById(R.id.crayon);
        gomme = (ImageButton) findViewById(R.id.gomme);
        couleur = (ImageButton) findViewById(R.id.couleur);

        outils = (AbsoluteLayout)findViewById(R.id.outils);
        travail = (AbsoluteLayout)findViewById(R.id.travail);


        afficherDessin = (ImageButton)findViewById(R.id.dessin);
        afficherDessin.setAlpha(0.5f);

        afficherCalque = (ImageButton)findViewById(R.id.calque);
        afficherCalque.setAlpha(0.5f);

        afficherImages = (ImageButton)findViewById(R.id.film);
        afficherImages.setAlpha(0.5f);

        taille = (SeekBar)findViewById(R.id.taille);
        affichageTailleCrayon = (ViewTailleCrayon)findViewById(R.id.view2);
        taille.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                zoneDessin.changeStrokeWidth(progress);
                affichageTailleCrayon.changeStrokeWidth(progress);
                affichageTailleCrayon.drawLine();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        zoneDessin = (CustomView)findViewById(R.id.view);
        layoutZoneDessin = (FrameLayout)findViewById(R.id.layoutDessin);

        mesImages = (HorizontalScrollView)findViewById(R.id.maliste);
        mesboutons = (HorizontalScrollView)findViewById(R.id.mesBoutons);

        index = 0;
        g = (LinearLayout)findViewById(R.id.layoutImages);
        afficherMesImages = (ImageButton)findViewById(R.id.imageButton);
        afficherMesBoutons = (ImageButton)findViewById(R.id.affciherBoutons);

        imageFond = (RelativeLayout)findViewById(R.id.imageFond);

        for(int i = 0; i < imageFond.getChildCount(); i++){
            ImageView v = (ImageView)imageFond.getChildAt(i);
            pelureOignons.add(v);
        }

        listeAffiche = false;
        boutonsAffiche = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify style_seek_bar parent activity in AndroidManifest.xml.
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

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setColor(couleurCourante);
        colorPicker.setCouleurChoisieListener(new ColorPicker.CouleurChoisieListener() {
            @Override
            public void CouleurChoisieListener(int newColor) {
                couleur.setBackgroundColor(newColor);
                zoneDessin.setColor(newColor);
                affichageTailleCrayon.setColor(newColor);
                affichageTailleCrayon.drawLine();
                couleurCourante = newColor;
            }
        });
        colorPicker.show(getFragmentManager(), "Color Picker");
    }

    public void utiliserCrayon(View v){
        zoneDessin.setColor(couleurCourante);
    }

    public void utiliserGomme(View v){
        zoneDessin.gomme();
    }

    public void changerImage(View v){
        //layoutZoneDessin.setBackground(((ImageView) v).getDrawable());

        Drawable temp = v.getBackground();
        Drawable c = temp.getConstantState().newDrawable();

        imageFond.setBackground(c);

        for(int i = 0; i<g.getChildCount(); i++){
            if(((ImageView) v).getId() == g.getChildAt(i).getId()){
                index = i;
            }
        }

        Drawable myDrawable = ((ImageView)g.getChildAt(index)).getDrawable();
        myDrawable = myDrawable.mutate();
        if(myDrawable != null) {
            Bitmap myLogo = ((BitmapDrawable) myDrawable).getBitmap();
            zoneDessin.setmBitmap(myLogo);
        }
        else{
            Bitmap myLogo = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
            zoneDessin.setmBitmap(myLogo);
        }
        changerPeluresOignon();
    }

    public void imageSuivante(View v){
        if(index < g.getChildCount()-1){
            index++;
            //layoutZoneDessin.setBackground(((ImageView)g.getChildAt(index)).getDrawable());
            Drawable myDrawable = ((ImageView)g.getChildAt(index)).getDrawable();

            if(myDrawable != null) {
                Bitmap myLogo = ((BitmapDrawable) myDrawable).getBitmap();
                zoneDessin.setmBitmap(myLogo);
            }
            else{
                Bitmap myLogo = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
                zoneDessin.setmBitmap(myLogo);
            }

            Drawable temp = g.getChildAt(index).getBackground();
            Drawable c = temp.getConstantState().newDrawable();

            imageFond.setBackground(c);
        }
        changerPeluresOignon();
    }

    public void imagePrecedente(View v){
        if(index >0){
            index--;
            Drawable myDrawable = ((ImageView)g.getChildAt(index)).getDrawable();
            if(myDrawable != null) {
                Bitmap myLogo = ((BitmapDrawable) myDrawable).getBitmap();
                zoneDessin.setmBitmap(myLogo);
            }
            else{
                Bitmap myLogo = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
                zoneDessin.setmBitmap(myLogo);
            }
            //layoutZoneDessin.setBackground(((ImageView)g.getChildAt(index)).getDrawable());
            Drawable temp = g.getChildAt(index).getBackground();
            Drawable c = temp.getConstantState().newDrawable();

            imageFond.setBackground(c);
        }
        changerPeluresOignon();
    }

    public void changerPeluresOignon(){
        int debut = index -1;
        int nb = nbPeluresOignons;
        float transp = 0.85f;
        for(int i = pelureOignons.size() - 1; i>=0; i--){
            if(debut>=0 && nb>0){
                Drawable temp = ((ImageView) g.getChildAt(debut)).getDrawable();
                Drawable c = temp.getConstantState().newDrawable();
                c.setAlpha(255);
                c = c.mutate();
                pelureOignons.get(i).setBackground(c);
                pelureOignons.get(i).setAlpha(transp);
                transp -= 0.1f;
                debut--;
                nb--;
            }
            else{
                pelureOignons.get(i).setBackground(null);
            }
        }
    }

    public void changerConfig(View v){

        Configurations conf = new Configurations();
        conf.setConf(this.nbPeluresOignons, this.nbImages, this.frequencePelures, this.afficherImage, this.droitier);
        conf.setConfigListener(new Configurations.ConfigChoisie() {
            @Override
            public void configChoisie(String nbPelures, int nbDernieresIages, String frequencePelures, boolean afficherImageFond, boolean droitier) {
                if(droitier){
                    outils.setX(travail.getWidth() + 1);
                    travail.setX(0);
                    modeDroitier = true;
                }
                else{
                    outils.setX(0);
                    travail.setX(outils.getWidth()+1);
                    modeDroitier = false;
                }
            }
        });

        conf.show(getFragmentManager(), "Configurations");


    }

    public void changerAffichageDessin(View v){
        if(zoneDessin.getVisibility() == View.INVISIBLE) {
            zoneDessin.setVisibility(View.VISIBLE);

            for(int i = 0; i < g.getChildCount(); i++){
                if(((ImageView)g.getChildAt(i)).getDrawable() != null){
                    ((ImageView)g.getChildAt(i)).getDrawable().setAlpha(255);
                }
            }
            afficherDessin.setAlpha(0.5f);
        }
        else{
            zoneDessin.setVisibility(View.INVISIBLE);
            for(int i = 0; i < g.getChildCount(); i++){
                if(((ImageView)g.getChildAt(i)).getDrawable() != null){
                    ((ImageView)g.getChildAt(i)).getDrawable().setAlpha(0);
                }
            }
            afficherDessin.setAlpha(1f);
        }
    }

    public void changerAffichageCalque(View V) {
        for (int i = 0; i < pelureOignons.size(); i++) {
            if (pelureOignons.get(i) != null) {
                if(pelureOignons.get(i).getVisibility() == View.VISIBLE) {
                    pelureOignons.get(i).setVisibility(View.INVISIBLE);
                    afficherCalque.setAlpha(1f);
                }
                else{
                    pelureOignons.get(i).setVisibility(View.VISIBLE);
                    afficherCalque.setAlpha(0.5f);
                }
            }
        }

    }

    public void afficherImagesFilm(View v){
        if(imageFond.getBackground().isVisible()) {
            imageFond.getBackground().setVisible(false, false);

            for (int i = 0; i < g.getChildCount(); i++) {
                g.getChildAt(i).getBackground().setAlpha(0);
            }
            afficherImages.setAlpha(1f);
        }
        else{
            imageFond.getBackground().setVisible(true, true);
            for(int i = 0; i < g.getChildCount(); i++){
                g.getChildAt(i).getBackground().setAlpha(255);
            }
            afficherImages.setAlpha(0.5f);
        }
    }
}

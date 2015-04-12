package com.zebra.mommywhereismyzebra;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity principale ou l'utilisateur interagit (dessin sur les images de la video
 * prealablement choisie).
 */
public class MainActivity extends Activity {


    private ImageButton couleur;
    private ImageButton afficherDessin;
    private ImageButton afficherCalque;
    private ImageButton afficherImages;

    //boutons qui servent a afficher les deux listes cachee
    private ImageButton afficherMesImages;
    private ImageButton afficherMesBoutons;

    private CustomView zoneDessin;

    private HorizontalScrollView mesImages;
    private HorizontalScrollView mesboutons;

    private LinearLayout layoutImages;
    private RelativeLayout imageFond;

    //zone avec les outils gomme, crayon etc ...
    private AbsoluteLayout outils;
    //zone ou l'on dessine
    private AbsoluteLayout travail;

    private List<ImageView> pelureOignons;

    private ViewTailleCrayon affichageTailleCrayon;

    private boolean modeDroitier = true;
    private boolean afficherImage = false;

    private int nbPeluresOignons = 5;
    private int nbImages = 10;
    private int freqPelures = 1;

    private int couleurCourante = Color.BLACK;
    private int indexImageRegardee = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        couleur = (ImageButton) findViewById(R.id.couleur);

        afficherDessin = (ImageButton)findViewById(R.id.dessin);
        afficherDessin.setAlpha(0.5f);

        afficherCalque = (ImageButton)findViewById(R.id.calque);
        afficherCalque.setAlpha(0.5f);

        afficherImages = (ImageButton)findViewById(R.id.film);
        afficherImages.setAlpha(0.5f);

        afficherMesImages = (ImageButton)findViewById(R.id.imageButton);

        afficherMesBoutons = (ImageButton)findViewById(R.id.affciherBoutons);

        zoneDessin = (CustomView)findViewById(R.id.view);

        mesImages = (HorizontalScrollView)findViewById(R.id.maliste);

        mesboutons = (HorizontalScrollView)findViewById(R.id.mesBoutons);

        SeekBar taille = (SeekBar) findViewById(R.id.taille);
        /**
         * Pour permettre de changer la taille de la ligne representant la taille du crayon
         */
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

        layoutImages = (LinearLayout)findViewById(R.id.layoutImages);

        imageFond = (RelativeLayout)findViewById(R.id.imageFond);

        outils = (AbsoluteLayout)findViewById(R.id.outils);

        travail = (AbsoluteLayout)findViewById(R.id.travail);

        affichageTailleCrayon = (ViewTailleCrayon)findViewById(R.id.view2);

        pelureOignons = new ArrayList<ImageView>();

        for(int i = 0; i < imageFond.getChildCount(); i++){
            ImageView iv = (ImageView)imageFond.getChildAt(i);
            pelureOignons.add(iv);
        }
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

    public void afficherListeImages(View v){
        if(mesImages.getVisibility() == View.VISIBLE){
            mesImages.setVisibility(View.INVISIBLE);
            afficherMesImages.setY(afficherMesImages.getY() + 210);
            v.invalidate();
            afficherMesImages.setImageResource(R.mipmap.up);
        }
        else {
            mesImages.setVisibility(View.VISIBLE);
            afficherMesImages.setY(afficherMesImages.getY() - 210);
            v.invalidate();
            afficherMesImages.setImageResource(R.mipmap.down);
        }
    }

    public void afficherBoutons(View v){
        if(mesboutons.getVisibility() == View.VISIBLE){
            mesboutons.setVisibility(View.INVISIBLE);
            afficherMesBoutons.setY(afficherMesBoutons.getY() - 120);
            v.invalidate();
            afficherMesBoutons.setImageResource(R.mipmap.down);
        }
        else {
            mesboutons.setVisibility(View.VISIBLE);
            afficherMesBoutons.setY(afficherMesBoutons.getY() + 120);
            v.invalidate();
            afficherMesBoutons.setImageResource(R.mipmap.up);

        }
    }

    public void changerCouleur(View v) {

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setColor(couleurCourante);
        colorPicker.setChoosenColorListener(new ColorPicker.ChoosenColorListener() {
            @Override
            public void ChoosenColorListener(int newColor) {
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

    /**
     * Methode qui permet de changer l'image et le dessin de la zone de dessin
     * quand l'utilisateur clique sur une des images de la liste.
     * @param v
     */
    public void changerImage(View v){

        //On change l'image affichee en fond (image de la video)
        Drawable temp = v.getBackground();
        Drawable nouvelleImageFond = temp.getConstantState().newDrawable();

        imageFond.setBackground(nouvelleImageFond);

        for(int i = 0; i< layoutImages.getChildCount(); i++){
            if( v.getId() == layoutImages.getChildAt(i).getId()){
                indexImageRegardee = i;
            }
        }

        //On change le dessin de la zone de dessin (si il y en a une, sinon on en cre une nouvelle
        Drawable myDrawable = ((ImageView) layoutImages.getChildAt(indexImageRegardee)).getDrawable();

        if(myDrawable != null) {
            myDrawable = myDrawable.mutate();

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
        if(indexImageRegardee < (layoutImages.getChildCount() - 1)){

            indexImageRegardee ++;

            Drawable myDrawable = ((ImageView) layoutImages.getChildAt(indexImageRegardee)).getDrawable();

            if(myDrawable != null) {
                myDrawable = myDrawable.getConstantState().newDrawable();

                Bitmap myLogo = ((BitmapDrawable) myDrawable).getBitmap();
                zoneDessin.setmBitmap(myLogo);
            }
            else{
                Bitmap myLogo = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
                zoneDessin.setmBitmap(myLogo);
            }

            Drawable temp = layoutImages.getChildAt(indexImageRegardee).getBackground();
            Drawable c = temp.getConstantState().newDrawable();

            imageFond.setBackground(c);
        }
        changerPeluresOignon();
    }

    public void imagePrecedente(View v){
        if(indexImageRegardee >0){

            indexImageRegardee --;
            Drawable myDrawable = ((ImageView) layoutImages.getChildAt(indexImageRegardee)).getDrawable();

            if(myDrawable != null) {
                myDrawable = myDrawable.getConstantState().newDrawable();

                Bitmap myLogo = ((BitmapDrawable) myDrawable).getBitmap();
                zoneDessin.setmBitmap(myLogo);
            }
            else{
                Bitmap myLogo = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
                zoneDessin.setmBitmap(myLogo);
            }

            Drawable temp = layoutImages.getChildAt(indexImageRegardee).getBackground();
            Drawable c = temp.getConstantState().newDrawable();

            imageFond.setBackground(c);
        }
        changerPeluresOignon();
    }

    public void changerPeluresOignon(){

        int debut = indexImageRegardee - 1;
        int nb = nbPeluresOignons;
        //transparence qui sera appliquee au pelure d'oignon, elle sera decrementee au fur et a mesure
        float transp = 0.85f;
        int i = pelureOignons.size() - 1;
        while(i >= 0){
            if(debut >= 0 && nb > 0){
                Drawable temp = ((ImageView) layoutImages.getChildAt(debut)).getDrawable();
                Drawable c = temp.getConstantState().newDrawable();
                c.setAlpha(255);
                c = c.mutate();
                pelureOignons.get(i).setBackground(c);
                pelureOignons.get(i).setAlpha(transp);
                transp -= 0.1f;
                debut = debut - freqPelures;
                nb--;
            }
            else{
                pelureOignons.get(i).setBackground(null);
            }
            i --;
        }
    }

    public void changerConfig(View v){

        Configurations conf = new Configurations();
        conf.setConf(this.nbPeluresOignons, this.nbImages, this.freqPelures, this.afficherImage, this.modeDroitier);
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

                nbPeluresOignons = Integer.parseInt(nbPelures);
                freqPelures = Integer.parseInt(frequencePelures);
                afficherImage = afficherImageFond;
                changerPeluresOignon();
            }
        });

        conf.show(getFragmentManager(), "Configurations");


    }

    public void changerAffichageDessin(View v){
        if(zoneDessin.getVisibility() == View.INVISIBLE) {
            zoneDessin.setVisibility(View.VISIBLE);

            for(int i = 0; i < layoutImages.getChildCount(); i++){
                if(((ImageView) layoutImages.getChildAt(i)).getDrawable() != null){
                    ((ImageView) layoutImages.getChildAt(i)).getDrawable().setAlpha(255);
                }
            }
            afficherDessin.setAlpha(0.5f);
        }
        else{
            zoneDessin.setVisibility(View.INVISIBLE);
            for(int i = 0; i < layoutImages.getChildCount(); i++){
                if(((ImageView) layoutImages.getChildAt(i)).getDrawable() != null){
                    ((ImageView) layoutImages.getChildAt(i)).getDrawable().setAlpha(0);
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

            for (int i = 0; i < layoutImages.getChildCount(); i++) {
                layoutImages.getChildAt(i).getBackground().setAlpha(0);
            }
            afficherImages.setAlpha(1f);
        }
        else{
            imageFond.getBackground().setVisible(true, true);
            for(int i = 0; i < layoutImages.getChildCount(); i++){
                layoutImages.getChildAt(i).getBackground().setAlpha(255);
            }
            afficherImages.setAlpha(0.5f);
        }
    }
}

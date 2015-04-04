package com.zebra.mommywhereismyzebra;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

    private MenuItem settings;


    private ImageButton afficherMesImages;
    private ImageButton afficherMesBoutons;

    private LinearLayout g;
    private RelativeLayout imageFond;

    private List<ImageView> pelureOignons;

    private int nbPeluresOignons = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pelureOignons = new ArrayList<ImageView>();
        couleurCourante = Color.BLACK;

        setContentView(R.layout.activity_main);

        crayon = (ImageButton) findViewById(R.id.crayon);
        gomme = (ImageButton) findViewById(R.id.gomme);
        couleur = (ImageButton) findViewById(R.id.couleur);

        taille = (SeekBar)findViewById(R.id.taille);

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
        zoneDessin.gomme();
    }

    public void changerImage(View v){
        //layoutZoneDessin.setBackground(((ImageView) v).getDrawable());
        imageFond.setBackground(((ImageView) v).getBackground());

        for(int i = 0; i<g.getChildCount(); i++){
            if(((ImageView) v).getId() == g.getChildAt(i).getId()){
                index = i;
            }
        }

        Drawable myDrawable = ((ImageView)g.getChildAt(index)).getDrawable();
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
            imageFond.setBackground(((ImageView) g.getChildAt(index)).getBackground());
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
            imageFond.setBackground(((ImageView) g.getChildAt(index)).getBackground());
        }
        changerPeluresOignon();
    }

    public void changerPeluresOignon(){
        int debut = index -1;
        int nb = nbPeluresOignons;
        float transp = 0.85f;
        for(int i = pelureOignons.size() - 1; i>=0; i--){
            if(debut>=0 && nb>0){
                pelureOignons.get(i).setBackground(((ImageView) g.getChildAt(debut)).getDrawable());
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
}

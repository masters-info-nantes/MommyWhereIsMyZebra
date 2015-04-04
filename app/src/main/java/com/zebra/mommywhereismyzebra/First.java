package com.zebra.mommywhereismyzebra;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class First extends Activity {

    private Button ouvrirVideo;
    private Button prendreVideo;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        ouvrirVideo = (Button) findViewById(R.id.ouvrirVideo);
        prendreVideo = (Button) findViewById(R.id.prendreVideo);

        View.OnClickListener cl = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                fileIntent.setType("video/*");
                startActivityForResult(fileIntent, 10);
            }
        };

        ouvrirVideo.setOnClickListener(cl);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 101 || requestCode == 10){

            Intent myIntent = new Intent(this, MainActivity.class);
            startActivityForResult(myIntent, 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
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

    public void captureVideo(View v){
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, 101);
    }
}

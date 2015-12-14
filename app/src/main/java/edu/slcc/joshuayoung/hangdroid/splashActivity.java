package edu.slcc.joshuayoung.hangdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Handler;
import java.util.logging.LogRecord;

public class splashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //expand the splash

        //new thread
        Runnable wait3seconds = new Runnable() {
            public void run() {
                nextActivity();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(wait3seconds, 3000);


    }

    public void nextActivity(){
        //start next activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}

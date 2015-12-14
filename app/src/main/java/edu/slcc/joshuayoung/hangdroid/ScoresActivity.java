package edu.slcc.joshuayoung.hangdroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ScoresActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        //find preferences
        SharedPreferences preferences = getSharedPreferences("WORD_SCORES", MODE_PRIVATE);
        //read preferences
        String scores = preferences.getString("SCORES", "NO SCORES");
        //get the textview for scores
        TextView textView = (TextView) findViewById(R.id.textViewScores);
        //put scores in textview
        textView.setText(scores);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_over, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_main_menu:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.action_new_game:
                startActivity(new Intent(this,GameActivity.class));
                return true;

            case R.id.action_scores:
                startActivity(new Intent(this,ScoresActivity.class));
                return true;


        }

        return super.onOptionsItemSelected(item);
    }

}

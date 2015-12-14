package edu.slcc.joshuayoung.hangdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameOverActivity extends Activity {

    private int playerPoints=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        //defaults if data does not come with intent
        int points = getIntent().getIntExtra("PointsID", 0);

        TextView textView = (TextView) findViewById(R.id.textViewPoints);
        textView.setText(String.valueOf(points));

        playerPoints= points;

    }

    public void saveScore(View view) {

        //set up to store preferences
        SharedPreferences preferences = getSharedPreferences("WORD_SCORES", Context.MODE_PRIVATE);
        //get name from game over xml
        EditText editText = (EditText) findViewById(R.id.editTextName);
        //set it to a string
        String name = editText.getText().toString();

        //start the preference editor
        SharedPreferences.Editor editor = preferences.edit();
        //get previous scores using the key
        String previousScores = preferences.getString("SCORES", "");
        Log.d ("MYLOG", "Previous Scores: " + previousScores);

        //Key = Scores, Value= concatinated String
        editor.putString("SCORES", name + " " + playerPoints + " POINTS\n" + previousScores);
        //save buffer
        editor.commit();

        //Name X Points \n Name2 Y POINTSsd

        Toast.makeText(this, "Score Saved", Toast.LENGTH_SHORT).show();
        editText.setText("");
        //finish();

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

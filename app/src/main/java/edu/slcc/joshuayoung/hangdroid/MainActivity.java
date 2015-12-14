package edu.slcc.joshuayoung.hangdroid;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Configuration configuration = getResources().getConfiguration();

        if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            hideSidePanel();
        }

        fragmentTransaction.commit();
    }



    private void hideSidePanel() {
        View sidepanel = findViewById(R.id.portrait_fragment);
        if (sidepanel.getVisibility() == View.VISIBLE) {
            sidepanel.setVisibility(View.GONE);
        }
    }

    public void startSinglePlayerGame(View view) {

        //explicit intent sends a message to start an activity
        Intent intent = new Intent(this, GameActivity.class);

        startActivity(intent);
    }

    public void startMultiPlayerGame(View view) {

        //explicit intent sends a message to start an activity
        Intent intent = new Intent(this, MultiplayerActivity.class);
        startActivity(intent);
    }

    public void openScores(View view) {
        //update scores xml
        Intent intent = new Intent(this, ScoresActivity.class);
        startActivity(intent);
    }

    public void startTextPlayerGame(View view) {

        Intent intent = new Intent(this, TextActivity.class);
        startActivity(intent);
    }

    public  void StartContactsActivity (View view) {

        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }



    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back. ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


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



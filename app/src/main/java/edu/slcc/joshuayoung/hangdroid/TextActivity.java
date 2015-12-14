package edu.slcc.joshuayoung.hangdroid;

import android.app.Activity;
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

public class TextActivity extends Activity {


    private SharedPreferences preferences;
    private EditText editText;
    private TextView textView;
    private String textWord;
    private String friendPhone;
    private String texterPhone;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        //get text message from shared preferences
        preferences = getSharedPreferences("TEXT_MSGS", MODE_PRIVATE);

        friendPhone = getIntent().getStringExtra("Phone");

        Log.d("MYLOG", "Friend: " + friendPhone);




        //read preferences
        getTextFromPref();

    }

    public void setTextMessage (View view) { getTextFromPref();}

    public void getTextFromPref()
    {
        //get message from preferences
        //preferences = getSharedPreferences("TEXT_MSGS" , MODE_PRIVATE);
        //read preferences
        textWord = preferences.getString("TextedWord", "NO WORD");//NO WORD if preferences not found
        texterPhone = preferences.getString("TexterPhone", "NO PHONE");
        textView = (TextView) findViewById(R.id.editTextWord);

        //get the textview for texted word
        boolean phone = true;
        boolean word = true;
        boolean friend = true;
        if (textWord== "NO WORD") word = false;
        if (texterPhone== "NO PHONE") phone = false;
        if (friendPhone== null) friend = false;

        if (!friend && word) {
            textView.setText(textWord);
            textWord = "";
            texterPhone = "";
            return;
        }

        if (word && phone){
            if (friendPhone.equals(texterPhone)) {
                textView.setText(textWord);
                textWord ="";
                texterPhone = "";
            }else{
                Toast.makeText(this, "No Text From Selected Friend", Toast.LENGTH_LONG).show();
            }
            return;
        }
        if (!word) {
            Toast.makeText(this, "No Text Recieved", Toast.LENGTH_LONG).show();
        }
    }

    public void  playMultiPlayerGame(View view) {
        //connect to xml
        //get word and cast word to a string
        String textWord = textView.getText().toString();
        if (textWord.length() > 0) {

            //clear field for next word
            textView.setText("");
            //clear word from shared preferences
            preferences.edit().remove("TextedWord").commit();
            Log.d("MYLOG", "Removed Texted Word" + textView);
            //create intent
            Intent intent = new Intent(this, GameMultiActivity.class);
            //send word with intent
            intent.putExtra("GUESS_WORD", textWord);
            //intent.putExtra("GUESS_WORD", wordToGuess);
            //start activity
            startActivity(intent);

        } else {
            Toast.makeText(this, "No Word Found - Try GET NEW TEXT", Toast.LENGTH_LONG).show();
        }
    }
}

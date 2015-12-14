package edu.slcc.joshuayoung.hangdroid;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ContactsActivity extends ListActivity {

    // declare ListView and cursor attributes
    ListView listView;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);


        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, null,null,null);
        startManagingCursor(cursor);

        final String[] Texter = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

        int[] item = {android.R.id.text1, android.R.id.text2};


        SimpleCursorAdapter listadapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,cursor, Texter, item, 0);

        setListAdapter(listadapter);

        listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectPhone = ((TextView)(listView.findViewById(android.R.id.text2))).getText().toString();

                Log.d("MYLOG", "onClick: "+ position + "/" + id + "/" + selectPhone);

                //create intent
                Intent intent = new Intent(ContactsActivity.this, TextActivity.class);
                //send phone with intent
                intent.putExtra("Phone", selectPhone);
                //start activity
                startActivity(intent);

            }


        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
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

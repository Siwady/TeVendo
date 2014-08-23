package com.example.siwady.tevendo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.siwady.tevendo.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MyArticles extends Activity {

    ListView Items;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_articles);
        Items=(ListView) findViewById(R.id.lv_Items);
        Intent i = getIntent();
        Email = i.getStringExtra ("Email");
        GetItems();
    }

    private void GetItems() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Article");
        query.whereEqualTo("Email", Email);
        query.setLimit(10);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> ItemsList, ParseException e) {
                ItemsArrayAdapter a=new ItemsArrayAdapter(getApplicationContext(),ItemsList.toArray(new ParseObject[ItemsList.size()]));
                Items.setAdapter(a);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_articles, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.siwady.tevendo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siwady.tevendo.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class UserPage extends Activity {

    String Email;
    TextView MyArticles;
    TextView UserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        Intent i = getIntent();
        Email = i.getStringExtra ("Email");
        MyArticles=(TextView)findViewById(R.id.tv_MyArticles);
        UserName=(TextView) findViewById(R.id.tv_User);
        UserName.setText(getUserName());
        MyArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent to_MyArticles = new Intent(UserPage.this,MyArticles.class);
                    to_MyArticles.putExtra("Email",Email);
                    startActivity(to_MyArticles);
            }
        });
    }

    private String getUserName() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("Email", Email);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, com.parse.ParseException e) {
                if (parseObject != null) {
                    UserName.setText(parseObject.get("UserName").toString());
                }
            }
        });
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_page, menu);
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

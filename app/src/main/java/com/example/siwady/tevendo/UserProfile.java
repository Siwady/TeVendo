package com.example.siwady.tevendo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siwady.tevendo.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class UserProfile extends Activity {

    TextView UserName;
    TextView UserEmail;
    TextView Nationality;
    ParseImageView UserImage;
    String Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        UserName= (TextView) findViewById(R.id.tv_UserName);
        UserEmail= (TextView) findViewById(R.id.tv_Email);
        Nationality= (TextView) findViewById(R.id.tv_Nationality);
        UserImage= (ParseImageView) findViewById(R.id.iv_UserImage);
        Intent i = getIntent();
        Email = i.getStringExtra ("Email");
        fill();
    }

    private void fill() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("Email",Email);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, com.parse.ParseException e) {

                if (parseObject != null) {
                    UserName.setText(parseObject.get("UserName").toString());
                    UserEmail.setText(Email);
                    //Nationality.setText(parseObject.get("Nationality").toString());

                    UserImage.setParseFile(parseObject.getParseFile("UserProfileImage"));
                    UserImage.loadInBackground();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile, menu);
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

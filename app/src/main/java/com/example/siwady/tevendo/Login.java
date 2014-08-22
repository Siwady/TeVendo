package com.example.siwady.tevendo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.example.siwady.tevendo.R;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.List;

public class Login extends Activity {

    EditText Email;
    EditText Password;
    Button bt_Login;
    Button bt_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Parse.initialize(this, "vblw3rKI00FJvasKaloXxgyb2SPj016V5baVNpJz", "e5IDoupZ3KAszADguskQsVwnP3iwXKMdAaC8i1DX");
        Email = (EditText) findViewById(R.id.et_Email);
        Password = (EditText) findViewById(R.id.et_Password);
        bt_Login = (Button) findViewById(R.id.btLogin);
        bt_Register=(Button) findViewById(R.id.bt_Register);

        bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Register");
                query.whereEqualTo("Email", Email.getText().toString());
                query.whereGreaterThan("Password",Password.getText().toString());
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, com.parse.ParseException e) {

                        if (parseObject == null) {
                            Toast.makeText(Login.this,"Login succesful", Toast.LENGTH_LONG).show();
                            Intent to_login = new Intent("android.intent.action.HOME");
                            finish();
                            startActivity(to_login);
                        } else {
                            Toast.makeText(Login.this,"Login fail", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        bt_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent("com.example.siwady.tevendo.REGISTER");
                startActivity(register);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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

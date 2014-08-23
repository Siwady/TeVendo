package com.example.siwady.tevendo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.siwady.tevendo.R;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.Parse;
import com.parse.ParseAnalytics;

import java.text.ParseException;

public class Register extends Activity {

    EditText UserName;
    EditText Email;
    EditText Password;
    Button bt_Register;
    Boolean exist=false;
    float x1,x2,y1,y2;

    public boolean onTouchEvent(MotionEvent TouchEvent)
    {
        switch (TouchEvent.getAction())
        {

            case MotionEvent.ACTION_DOWN:
            {
                x1 = TouchEvent.getX();
                y1 = TouchEvent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = TouchEvent.getX();
                y2 = TouchEvent.getY();


                if (x1 < x2)
                {
                    //izquierda a derecha
                    Intent to_login= new Intent(Register.this,Login.class);
                    finish();
                    startActivity(to_login);
                }


                if (x1 > x2)
                {
                    //derecha a derecha

                }


                if (y1 < y2)
                {
                    //arriba a abajo
                }


                if (y1 > y2)
                {
                    //abajo a arriba
                }
                break;
            }
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Parse.initialize(this, "vblw3rKI00FJvasKaloXxgyb2SPj016V5baVNpJz", "e5IDoupZ3KAszADguskQsVwnP3iwXKMdAaC8i1DX");
        UserName=(EditText) findViewById(R.id.et_UserName);
        Email = (EditText) findViewById(R.id.et_Email);
        Password = (EditText) findViewById(R.id.et_Password);
        bt_Register = (Button) findViewById(R.id.bt_Register);

        bt_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VerifyEmail() && VerifyEmailExist())
                {
                    ParseObject register = new ParseObject("Register");
                    register.put("UserName", UserName.getText().toString());
                    register.put("Email",Email.getText().toString());
                    register.put("Password",Password.getText().toString());
                    register.saveInBackground();
                    Intent to_home = new Intent(Register.this,Home.class);
                    finish();
                    startActivity(to_home);
                }
            }
        });
    }

    private boolean VerifyEmailExist() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Register");
        query.whereEqualTo("Email", Email.getText().toString());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, com.parse.ParseException e) {

                if (parseObject == null) {
                    Toast.makeText(Register.this,"Si se puede registrar", Toast.LENGTH_LONG).show();
                    exist=true;
                } else {
                    Toast.makeText(Register.this,"No se puede registrar", Toast.LENGTH_LONG).show();
                    exist=false;
                }
            }
        });
        return exist;
    }

    private boolean VerifyEmail() {

        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
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

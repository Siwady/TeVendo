package com.example.siwady.tevendo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.siwady.tevendo.R;

public class Login extends Activity {

    EditText Email;
    EditText Password;
    Button bt_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = (EditText) findViewById(R.id.et_Email);
        Password = (EditText) findViewById(R.id.et_Password);
        bt_Login = (Button) findViewById(R.id.btLogin);

        bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // aca va lo de mandar los datos a la base de datos.
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

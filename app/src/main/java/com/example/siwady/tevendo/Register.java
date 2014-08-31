package com.example.siwady.tevendo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.siwady.tevendo.R;
import com.parse.GetCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.Parse;
import com.parse.ParseAnalytics;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;

public class Register extends Activity {

    private static final int SELECT_PICTURE = 1;
    EditText UserName;
    EditText Email;
    EditText Password;
    Button bt_Register;
    Boolean exist=false;
    ImageView UserImage;
    float x1,x2,y1,y2;
    private String selectedImagePath;

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
        UserName=(EditText) findViewById(R.id.et_UserName);
        Email = (EditText) findViewById(R.id.et_Email);
        Password = (EditText) findViewById(R.id.et_Password);
        bt_Register = (Button) findViewById(R.id.bt_Register);
        UserImage=(ImageView) findViewById(R.id.iv_UserImage);
        UserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(i,"Select Picture"), SELECT_PICTURE);
            }
        });

        bt_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VerifyEmail() && VerifyEmailExist())
                {
                    Bitmap image = ((BitmapDrawable)UserImage.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] data = stream.toByteArray();
                    ParseFile imgFile = new ParseFile ("ProfileImage.png", data);
                    ParseObject register = new ParseObject("User");
                    register.put("UserName", UserName.getText().toString());
                    register.put("Email",Email.getText().toString());
                    register.put("Password",Password.getText().toString());
                    register.put("UserProfileImage",imgFile);
                    register.saveInBackground();
                    Intent to_home = new Intent(Register.this,Login.class);
                    finish();
                    startActivity(to_home);
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                UserImage.setImageURI(selectedImageUri);
            }
        }
    }

    private String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private boolean VerifyEmailExist() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
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

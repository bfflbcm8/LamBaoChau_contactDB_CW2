package com.example.lambaochaucontactdatabasecw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mname,mdob,memail;
    Button btsave,btdetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mname = findViewById(R.id.txtName);
        mdob = findViewById(R.id.txtDoB);
        memail = findViewById(R.id.txtEmail);

        btsave = findViewById(R.id.btsave);
        btdetail = findViewById(R.id.btDetail);

        btdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //link open list view app
                viewdetailt();
            }
        });

        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertMain();
            }
        });
    }
    public void insertMain(){
        try {
            String name = mname.getText().toString();
            String dob = mdob.getText().toString();
            String email = memail.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("ContactDb", Context.MODE_PRIVATE,null);
            db.execSQL("Create table if Not EXISTS SaveTB(id INTEGER primary key autoincrement,name Varchar,dob Varchar,email Varchar)");

            String sql = "insert into SaveTB(name,dob,email)values(?,?,?)";
            SQLiteStatement statement= db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,dob);
            statement.bindString(3,email);
            statement.execute();
            Toast.makeText(this,"Saved Successfully",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this,"Save Failed!",Toast.LENGTH_LONG).show();
        }
    }
    public  void viewdetailt(){
        Intent notifyIntent = new Intent(this,ViewActivity.class);
        startActivity(notifyIntent);
    }
}
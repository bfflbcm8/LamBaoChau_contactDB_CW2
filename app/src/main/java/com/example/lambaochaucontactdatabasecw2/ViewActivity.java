package com.example.lambaochaucontactdatabasecw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ViewActivity extends AppCompatActivity {

    ListView vlist;
    ArrayList<String> vtitle = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SQLiteDatabase db = openOrCreateDatabase("ContactDb", Context.MODE_PRIVATE,null);

        vlist = findViewById(R.id.listView);
        final Cursor cs = db.rawQuery("select * from SaveTB",null);
        int id = cs.getColumnIndex("id");
        int name = cs.getColumnIndex("name");
        int dob = cs.getColumnIndex("dob");
        int email = cs.getColumnIndex("email");
        vtitle.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,vtitle);
        vlist.setAdapter(arrayAdapter);

        final ArrayList<Customer> custo = new ArrayList<Customer>();

        if(cs.moveToFirst()){
            do{
                Customer ctm = new Customer();
                ctm.id = cs.getString(id);
                ctm.name = cs.getString(name);
                ctm.dob = cs.getString(dob);
                ctm.email = cs.getString(email);

                custo.add(ctm);

                vtitle.add(cs.getString(id) + " \t " + cs.getString(name) + " \t "  + cs.getString(dob) + " \t " + cs.getString(email));
            }while (cs.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            vlist.invalidateViews();
        }
    }
}
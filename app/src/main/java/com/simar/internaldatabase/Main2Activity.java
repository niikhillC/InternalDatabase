package com.simar.internaldatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    Button next,prev,update,delete;
    SQLiteDatabase db;

    Cursor c;
    String qry = "SELECT * FROM users;";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        e1 = findViewById(R.id.editText5);
        e2 = findViewById(R.id.editText6);
        e3 = findViewById(R.id.editText7);
        e4 = findViewById(R.id.editText8);
        next = findViewById(R.id.button3);
        prev = findViewById(R.id.button4);
        update = findViewById(R.id.button5);
        delete = findViewById(R.id.button6);

        db = openOrCreateDatabase("Mydb",MODE_PRIVATE,null);

       c = db.rawQuery(qry,null);
       c.moveToFirst();
       showdata();

       next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(!c.isLast())
               {
                   c.moveToNext();
                   showdata();
               }
               else
                   Toast.makeText(Main2Activity.this, "Last Record", Toast.LENGTH_SHORT).show();
           }
       });

       prev.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (!c.isFirst())
               {
                   c.moveToPrevious();
                   showdata();
               }
               else
                   Toast.makeText(Main2Activity.this, "First Record", Toast.LENGTH_SHORT).show();
           }
       });
       update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String idd = e1.getText().toString();
               String s1 = e2.getText().toString();
               String s2 = e3.getText().toString();
               String s3 = e4.getText().toString();
               String up_query = "UPDATE users SET username = '"+s1+"',password='"+s2+"',destination='"+s3+"' WHERE id='"+idd+"';";
                db.execSQL(up_query);
               c = db.rawQuery(qry,null);
               c.moveToFirst();
               showdata();
           }
       });

       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (c!=null)
               {

               }
           }
       });
    }
    public void showdata()
    {
        String a1 = c.getString(0);
        String b1 = c.getString(1);
        String c1 = c.getString(2);
        String d1 = c.getString(3);

        e1.setText(a1);
        e2.setText(b1);
        e3.setText(c1);
        e4.setText(d1);
    }
}

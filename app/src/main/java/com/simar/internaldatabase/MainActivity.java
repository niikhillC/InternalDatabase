package com.simar.internaldatabase;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3,et4;
    Button b1,b2;

    SQLiteDatabase db;
    String table_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.editText);
        et2 = findViewById(R.id.editText2);
        et3 = findViewById(R.id.editText3);
        et4 = findViewById(R.id.editText4);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);

        db = openOrCreateDatabase("Mydb",MODE_PRIVATE,null);
        table_query = "CREATE TABLE IF NOT EXISTS users(id INTEGER  PRIMARY KEY AUTOINCREMENT ,username VARCHAR(50),password VARCHAR(50),designation VARCHAR(50));";
        db.execSQL(table_query);

         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String s1 = et1.getText().toString();
                 String s2 = et2.getText().toString();
                 String s3 = et3.getText().toString();
                 String s4 = et4.getText().toString();

                 if (s1.equals("")||s2.equals("")||s3.equals("")||s4.equals(""))
                 {
                     Toast.makeText(MainActivity.this, "Fields cannot be blank ", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if (s2.equals(s3))
                 {
                     String insert_query = "INSERT INTO users(username,password,designation) VALUES('"+s1+"','"+s2+"','"+s4+"'); ";
                     db.execSQL(insert_query);
                     Toast.makeText(MainActivity.this, "Value Inserted Successfully", Toast.LENGTH_SHORT).show();
                     et1.setText("");
                     et2.setText("");
                     et3.setText("");
                     et4.setText("");
                 }
             }
         });
         b2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(MainActivity.this,Main2Activity.class);
                 startActivity(i);
             }
         });
    }
}

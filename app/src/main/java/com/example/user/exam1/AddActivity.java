package com.example.user.exam1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    EditText eedt_title,eedt_description;
    Button bbtnSave,bbtnCancle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        init();

        bbtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        bbtnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });

    }
    private void init(){
        eedt_title = (EditText) findViewById(R.id.edt_title);
        eedt_description=(EditText) findViewById(R.id.edt_description);

        bbtnSave = (Button) findViewById(R.id.btnSave);
        bbtnCancle = (Button) findViewById(R.id.btnCancle);

    }

    private void insertData(){
        String title = eedt_title.getText().toString();
        String description = eedt_description.getText().toString();

        if(!title.isEmpty()||!description.isEmpty()){
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.insertUser(title,description);
            Intent intent = new Intent(AddActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            final AlertDialog ald = new AlertDialog.Builder(AddActivity.this).create();
            ald.setTitle("Alert");
            ald.setMessage("Please input Title and Description");
            ald.setButton(ald.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            ald.show();
            Toast.makeText(getApplicationContext(),"Please input data",Toast.LENGTH_SHORT);

        }
    }
}
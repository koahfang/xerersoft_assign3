package com.example.user.exam1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateAndDeleteActivity extends AppCompatActivity {
    EditText edt_title,edt_description;
    Button btnUpdate,btnCancle,btnDelete;

    private String user_id;
    private String user_title;
    private String user_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete);

        init();

        final DatabaseHelper helper = new DatabaseHelper(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edt_title.getText().toString();
                String description = edt_description.getText().toString();

                helper.updateUser(user_id,title,description);
                finish();
            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.deleteUser(user_id);
                finish();
            }
        });
    }

    private void init(){
        edt_title = (EditText) findViewById(R.id.eedt_title);
        edt_description = (EditText) findViewById(R.id.eedt_description);

        btnUpdate = (Button) findViewById(R.id.bbtnSave);
        btnCancle = (Button) findViewById(R.id.bbtnCancle);
        btnDelete = (Button) findViewById(R.id.bbtnDelete);

        user_id = getIntent().getStringExtra("user_id");
        user_title = getIntent().getStringExtra("user_title");
        user_description = getIntent().getStringExtra("user_description");

        edt_title.setText(user_title);
        edt_description.setText(user_description);

    }
}

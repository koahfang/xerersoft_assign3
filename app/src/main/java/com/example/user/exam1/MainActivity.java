package com.example.user.exam1;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this,AddActivity.class);
               startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        showAllData();
    }


    public void showAllData(){
        final DatabaseHelper helper = new DatabaseHelper(this);
        final Cursor cursor = helper.SelectAllData();
        SQLiteDatabase db;
        ListView listView = (ListView) findViewById(R.id.list_item);
        SimpleCursorAdapter adapter;

        adapter = new SimpleCursorAdapter(MainActivity.this,R.layout.list_item,cursor,
                new String[]{"user_title","user_description"},new int[] {R.id.showTitle});

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String id = cursor.getString(cursor.getColumnIndex("user_id"));
                String title = cursor.getString(cursor.getColumnIndex("user_title"));
                String description = cursor.getString(cursor.getColumnIndex("user_description"));

                Toast.makeText(getApplicationContext(),"Select user_id:"+id,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this,UpdateAndDeleteActivity.class);
                intent.putExtra("user_id",id);
                intent.putExtra("user_title",title);
                intent.putExtra("user_description",description);
                startActivity(intent);
            }
        });

        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

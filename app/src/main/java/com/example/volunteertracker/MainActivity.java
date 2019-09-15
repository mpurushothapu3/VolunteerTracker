package com.example.volunteertracker;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickNew();
            }
        });

        //RecyclerView

        ArrayList<Event> InputEvents = getEvents();
        Log.e("Logs",(new Integer(InputEvents.size())).toString());
//        InputEvents.add(new Event("Mile Run","10","VIBHA"));
//        InputEvents.add(new Event("Volunteering at the Shelter","6000","Sawnee Shelter"));
//        InputEvents.add(new Event("Mile Run","10","VIBHA"));
//        InputEvents.add(new Event("Mile Run","10","VIBHA"));
//        InputEvents.add(new Event("Mile Run","10","VIBHA"));
//        InputEvents.add(new Event("Mile Run","10","VIBHA"));
//        InputEvents.add(new Event("Mile Run","10","VIBHA"));
//        InputEvents.add(new Event("Mile Run","10","VIBHA"));
//        InputEvents.add(new Event("Mile Run","10","VIBHA"));
//        InputEvents.add(new Event("Mile Run","10","VIBHA"));
//        InputEvents.add(new Event("Mile Run","10","VIBHA"));

        recyclerView = (RecyclerView) findViewById(R.id.EventsRecyclerView);
        recyclerView.setHasFixedSize(true);

        EventAdapter adapter = new EventAdapter(InputEvents);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                1);
        recyclerView.addItemDecoration(dividerItemDecoration);


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

    public void clickNew(){
        Intent intent = new Intent(this, NewEvent.class);
        startActivity(intent);

    }

    public ArrayList<Event> getEvents(){
        ArrayList<Event> events = new ArrayList<Event>();
        File [] files = getFilesDir().listFiles();
        for(File file: files){
            try{
                FileInputStream fis = getApplicationContext().openFileInput(file.getName());
                ObjectInputStream is = new ObjectInputStream(fis);
                Event event = (Event) is.readObject();
                is.close();
                fis.close();
                events.add(event);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        return events;
    }


}

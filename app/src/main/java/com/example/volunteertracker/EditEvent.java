package com.example.volunteertracker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

public class EditEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        Toolbar toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setTitle("Edit Event");
        setSupportActionBar(toolbar);

        String mJsonObject = getIntent().getStringExtra("InputEvent");
        Log.e("JSON",mJsonObject);
        Event event = (new Gson().fromJson(mJsonObject,Event.class));
        ((EditText)findViewById(R.id.InputName)).setText(event.getName());
        ((EditText)findViewById(R.id.InputBeneficiary)).setText(event.getBeneficiary());
        ((EditText)findViewById(R.id.InputHours)).setText(event.getHours());
        ((EditText)findViewById(R.id.InputDate)).setText(event.getDate().toString());
        ((EditText)findViewById(R.id.InputNotes)).setText(event.getNotes());
        ((EditText)findViewById(R.id.InputStartTime)).setText(event.getStartTime().toString());
        ((EditText)findViewById(R.id.InputEndTime)).setText(event.getEndTime().toString());
        ((EditText)findViewById(R.id.InputLocation)).setText(event.getLocation());
        ((EditText)findViewById(R.id.InputSupervisor)).setText(event.getSupervisor());



    }

}

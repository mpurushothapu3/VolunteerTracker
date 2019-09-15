package com.example.volunteertracker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NewEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = getApplicationContext();
        setContentView(R.layout.activity_new_event);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //New Event Handling


        ArrayList<String> BenArray = new ArrayList<String>();
        File file = new File(context.getFilesDir(), "BeneficiariesAdapter");
        try {

            if (!file.exists()) {
                FileOutputStream fos = new FileOutputStream("BeneficiariesAdapter");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(new ArrayList<String>());
                fos.close();
                oos.close();
            } else {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                ArrayList<String> array = (ArrayList<String>) ois.readObject();
                BenArray = (ArrayList<String>) array.clone();
                fis.close();
                ois.close();
            }
        }

            catch(Exception e){
                e.printStackTrace();
            }


        final ArrayList<String> BenAdapter = (ArrayList<String>) BenArray.clone();


        //final ArrayList<String> Bens = (ArrayList<String>) BenArray.clone();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,BenAdapter);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv =  (AutoCompleteTextView)findViewById(R.id.InputBeneficiary);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView


        final EditText StartDate = (EditText) findViewById(R.id.InputDate);
        StartDate.setText((new SimpleDateFormat("MM/dd/yyyy")).format(new Date()));
        StartDate.setShowSoftInputOnFocus(false);

        StartDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEvent.ACTION_UP == event.getAction()) {
                    v.performClick();
                    hideKeyboard();
                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    // date picker dialog
                    DatePickerDialog picker = new DatePickerDialog(NewEvent.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    StartDate.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                                }
                            }, year, month, day);
                    picker.show();

                }
                return false;
            }

        });

        ((EditText)(findViewById(R.id.InputStartTime))).setShowSoftInputOnFocus(false);
        final EditText StartTime = findViewById(R.id.InputStartTime);
        StartTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEvent.ACTION_UP == event.getAction()) {
                    v.performClick();
                    hideKeyboard();
                    Calendar calendar = Calendar.getInstance();
                    int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                    int currentMinute = calendar.get(Calendar.MINUTE);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(NewEvent.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                            String side;
                            if(hourOfDay == 0) {
                                side = "AM";
                                hourOfDay = 12;
                            }
                            else if(hourOfDay > 12){
                                hourOfDay = hourOfDay - 12;
                                side = "PM";
                            }
                            else
                                side = "AM";

                            StartTime.setText(String.format("%02d:%02d", hourOfDay, minutes)+" " + side);

                        }
                    }, currentHour, currentMinute, false);
                    timePickerDialog.show();
                }
                return false;
            }
        });

        ((EditText)(findViewById(R.id.InputEndTime))).setShowSoftInputOnFocus(false);
        final EditText EndTime = findViewById(R.id.InputEndTime);
        EndTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEvent.ACTION_UP == event.getAction()) {
                    v.performClick();
                    hideKeyboard();
                    Calendar calendar = Calendar.getInstance();
                    int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                    int currentMinute = calendar.get(Calendar.MINUTE);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(NewEvent.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                            String side;
                            if(hourOfDay == 0) {
                                side = "AM";
                                hourOfDay = 12;
                            }
                            else if(hourOfDay > 12){
                                hourOfDay = hourOfDay - 12;
                                side = "PM";
                            }
                            else
                                side = "AM";

                            EndTime.setText(String.format("%02d:%02d", hourOfDay, minutes)+" " + side);

                        }
                    }, currentHour, currentMinute, false);
                    timePickerDialog.show();
                }
                return false;
            }
        });



        Button button = findViewById(R.id.SaveEvent);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //FileOutputStream file = getApplicationContext().openFileOutput("Events", MODE_PRIVATE);;

                String InputName = ((EditText)findViewById(R.id.InputName)).getText().toString();
                String InputHours = ((EditText)findViewById(R.id.InputHours)).getText().toString();
                String InputBeneficiary = ((EditText)findViewById(R.id.InputBeneficiary)).getText().toString();
                String InputDate = ((EditText)findViewById(R.id.InputDate)).getText().toString();

                if(InputName.equals("")){
                    Toast.makeText(context, "Please Enter an Event Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(InputHours.equals("")){
                    Toast.makeText(context, "Please Enter the Number of Hours", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(InputBeneficiary.equals("")){
                    Toast.makeText(context, "Please Enter an Organization", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(InputDate.equals("")){
                    Toast.makeText(context, "Please Enter a Date", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Add Time Check



               try {
                   Event event = new Event(InputName, InputHours, InputBeneficiary, (Date) (new SimpleDateFormat("MM/dd/yyyy").parse(InputDate)));
                   FileOutputStream fileOut = openFileOutput(InputName+".ser", Context.MODE_PRIVATE);
                   ObjectOutputStream out = new ObjectOutputStream(fileOut);
                   out.writeObject(event);
                   out.close();
                   fileOut.close();

                  if(!(BenAdapter.contains(InputBeneficiary))){
                      File file = new File(context.getFilesDir().toString(),  "BeneficiariesAdapter");
                      FileOutputStream fileOutB = new FileOutputStream(file, false);
                      ObjectOutputStream outB = new ObjectOutputStream(fileOutB);
                      ArrayList<String> BenAdapterOut = (ArrayList<String>) BenAdapter.clone();
                      BenAdapterOut.add(InputBeneficiary);
                      outB.writeObject(BenAdapterOut);
                      outB.close();
                      fileOutB.close();

                  }
                  Toast.makeText(context,"Serialized data is saved in " + InputName + ".ser",Toast.LENGTH_LONG).show();
                  returnToMain();
              }
              catch(Exception e){
                  e.getStackTrace();
                  Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
              }

            }

        });


    }
    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    public void returnToMain(){ Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);}
}

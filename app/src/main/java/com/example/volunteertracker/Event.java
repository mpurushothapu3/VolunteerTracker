package com.example.volunteertracker;

import android.graphics.Bitmap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event implements Serializable {
    private String Name;
    private String Hours;
    private String Beneficiary;
    private Date Date;
    private String Notes;
    private String Location;
    private String Supervisor;
    //private Bitmap Signature;
    private Time StartTime;
    private Time EndTime;

    public Event(String InputName, String InputHours, String InputBeneficiary, Date InputDate, String InputNotes, Time startTime, Time endTime, String location, String supervisor){
        Name = InputName;
        Hours = InputHours;
        Beneficiary = InputBeneficiary;
        Date = InputDate;
        Notes = InputNotes;
        Location = location;
        Supervisor = supervisor;
        //Signature = Bitmap.createBitmap(55, 55, Bitmap.Config.ARGB_8888);
        StartTime = startTime;
        EndTime = endTime;
    }

    public Event(String InputName, String InputHours, String InputBeneficiary, Date InputDate){
        Name = InputName;
        Hours = InputHours;
        Beneficiary = InputBeneficiary;
        Date = InputDate;
        Notes = "";
        Location = "";
        Supervisor = "";
        //Signature = Bitmap.createBitmap(55, 55, Bitmap.Config.ARGB_8888);
        StartTime = new Time(0);
        EndTime = new Time(0);
    }

    public Event(String InputName, String InputHours, String InputBeneficiary){
        Name = InputName;
        Hours = InputHours;
        Beneficiary = InputBeneficiary;
        Date = new Date(1000000000L);
        Notes = "";
        Location = "";
        Supervisor = "";
        //Signature = Bitmap.createBitmap(55, 55, Bitmap.Config.ARGB_8888);
        StartTime = new Time(0);
        EndTime = new Time(0);
    }

    public Event(String InputName, String InputHours, String InputBeneficiary, Date InputDate, String InputNotes, Time startTime, Time endTime, String location, String supervisor, Bitmap signature){
        Name = InputName;
        Hours = InputHours;
        Beneficiary = InputBeneficiary;
        Date = InputDate;
        Notes = InputNotes;
        Location = location;
        Supervisor = supervisor;
        //Signature = signature;
        StartTime = startTime;
        EndTime = endTime;
    }

    public String getName(){
        return Name;
    }

    public String getHours(){
        return Hours;
    }

    public String getBeneficiary() {return Beneficiary;}

    public Date getDate() {return Date;}

    public String getNotes() {return Notes;}

    public Time getStartTime() {return StartTime;}

    public Time getEndTime() {return EndTime;}

    public String getLocation() {return Location;}

    public String getSupervisor() {return Supervisor;}

    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException
    {
        Name = aInputStream.readUTF();
        Hours = aInputStream.readUTF();
        Beneficiary = aInputStream.readUTF();
        Date = (Date) aInputStream.readObject();
        Notes = aInputStream.readUTF();
        Location = aInputStream.readUTF();
        StartTime = (Time) aInputStream.readObject();
        EndTime = (Time) aInputStream.readObject();
        Supervisor = aInputStream.readUTF();
        //Signature = (Bitmap) aInputStream.readObject();


    }

    private void writeObject(ObjectOutputStream aOutputStream) throws IOException
    {
        aOutputStream.writeUTF(Name);
        aOutputStream.writeUTF(Hours);
        aOutputStream.writeUTF(Beneficiary);
        aOutputStream.writeObject(Date);
        aOutputStream.writeUTF(Notes);
        aOutputStream.writeUTF(Location);
        aOutputStream.writeObject(StartTime);
        aOutputStream.writeObject(EndTime);
        aOutputStream.writeUTF(Supervisor);
        //aOutputStream.writeObject(Signature);
    }
}

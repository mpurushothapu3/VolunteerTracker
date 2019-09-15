package com.example.volunteertracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView NameText;
        public TextView HoursText;
        public TextView OrgText;
        public TextView DateText;

        public ViewHolder(View itemView) {
            super(itemView);

            NameText = (TextView) itemView.findViewById(R.id.NameHolder);
            HoursText = (TextView) itemView.findViewById(R.id.HoursHolder);
            OrgText = (TextView) itemView.findViewById(R.id.BeneficiaryHolder);
            DateText = (TextView) itemView.findViewById(R.id.DateHolder);
        }
    }


    private ArrayList<Event> Events;

    public EventAdapter(ArrayList<Event> InputEvents){
        Events = InputEvents;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View eventView = inflater.inflate(R.layout.item_event, parent, false);
        ViewHolder vh = new ViewHolder(eventView);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        final Context context = viewHolder.itemView.getContext();

        // Get the data model based on position
        final Event event = Events.get(position);

        // Set item views based on your views and data model
        viewHolder.NameText.setText(event.getName());
        viewHolder.HoursText.setText(event.getHours() + " Hours");
        viewHolder.OrgText.setText(event.getBeneficiary());
        Calendar c = Calendar.getInstance();
        c.setTime(event.getDate());
        viewHolder.DateText.setText(event.getDate().toString().substring(4,event.getDate().toString().indexOf(":")-3)+" " + (c.YEAR+1969));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,EditEvent.class);
                intent.putExtra("InputEvent", new Gson().toJson(event));
                context.startActivity(intent);
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Toast.makeText(ComplexRecyclerViewAdapter.this, "Item no: "+ position, Toast.LENGTH_LONG).show;
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle(event.getName());
                alert.setMessage("What do you want to do with this event?");
                // Make an "OK" button to save the name
                alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {



                        // Put it into memory (don't forget to commit!)
//                        SharedPreferences.Editor e = mSharedPreferences.edit();
//                        e.putString(PREF_NAME, inputName);
//                        e.commit();

                        // Welcome the new user
//                        Toast.makeText(getApplicationContext(), "Welcome, " + inputName + "!", Toast.LENGTH_LONG).show();
                    }
                });

                alert.setNeutralButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

                // Make a "Cancel" button
                // that simply dismisses the alert
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {}
                });

                alert.show();
                return true;
            }
        });

    }


    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return Events.size();
    }


}

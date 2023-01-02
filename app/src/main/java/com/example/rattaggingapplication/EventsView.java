package com.example.rattaggingapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rattaggingapplication.databinding.EventsViewBinding;
import com.example.rattaggingapplication.db.DbManager;
import com.example.rattaggingapplication.db.tables.FeedEvents;
import com.example.rattaggingapplication.db.tables.FeedUsers;
import com.example.rattaggingapplication.register.Event;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.rattaggingapplication.MainActivity.eventid;
public class EventsView extends Fragment {
    private int userid = MainActivity.userid;
    private EventsViewBinding binding;
    private DbManager dbManager;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = EventsViewBinding .inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbManager = new DbManager(this.getContext());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        binding.date.setText(dtf.format(now));
        binding.confirmEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventName = binding.eventname.getText().toString();
                if (!(eventName.isEmpty())){
                    Event event = new Event();
                    event.setDate(binding.date.toString());
                    event.setName(eventName);
                    event.setuserId(userid);
                    dbManager.addOneEvent(event);
                    Cursor lastRow = dbManager.getLastRowId(FeedEvents.FeedEntryEvents.TABLE_NAME);
                    lastRow.moveToFirst();
                    MainActivity.eventid = lastRow.getInt(0);
                    NavHostFragment.findNavController(EventsView.this)
                            .navigate(R.id.action_EventsViewNav_to_TagsViewNav);
                }
                else{
                    Snackbar.make(view, "Event name is empty", Snackbar.LENGTH_LONG).show();
                }

            }
        });
        binding.showEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EventsView.this)
                        .navigate(R.id.action_EventsViewNav_to_EventsListNav);
            }
        });
        binding.logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EventsView.this)
                        .navigate(R.id.action_EventsViewNav_to_LoginView);
            }
        });
        binding.addRatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EventsView.this)
                        .navigate(R.id.action_EventsViewNav_to_RatsRegisterViewNav);
            }
        });

    }

}

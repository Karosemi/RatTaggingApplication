package com.example.rattaggingapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rattaggingapplication.databinding.EventsListBinding;
import com.example.rattaggingapplication.db.DbManager;
import com.example.rattaggingapplication.db.tables.FeedEmotions;
import com.example.rattaggingapplication.db.tables.FeedEvents;

import static com.example.rattaggingapplication.MainActivity.userid;


public class EventsList extends Fragment {
    private EventsListBinding binding;
    private DbManager dbManager;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = EventsListBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] columnsToReturn = { FeedEvents.FeedEntryEvents._ID, FeedEvents.FeedEntryEvents.COLUMN_NAME_NAME};
        String[] selectionArgs = {String.valueOf(userid )};
//        SQLiteDatabase readableDb = sqlDataBaseHandler.getReadableDatabase();
        dbManager = new DbManager(this.getContext());
        Cursor eventsCursor = dbManager.getValuesFromTable(FeedEvents.FeedEntryEvents.TABLE_NAME, columnsToReturn, FeedEvents.FeedEntryEvents.COLUMN_NAME_USER_ID,
                selectionArgs);


        SimpleCursorAdapter eventssadapter =
                new SimpleCursorAdapter(this.getContext(), android.R.layout.simple_list_item_multiple_choice, eventsCursor,
                        new String[] {FeedEmotions.FeedEntryEmotions.COLUMN_NAME_NAME}, new int[] {android.R.id.text1}, 0);
        binding.eventsList.setAdapter(eventssadapter);
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EventsList.this)
                        .navigate(R.id.action_EventsListNav_to_EventsViewNav);
            }
        });


    }
}

package com.example.rattaggingapplication;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import static com.example.rattaggingapplication.MainActivity.sqlDataBaseHandler;
import static com.example.rattaggingapplication.MainActivity.userid;

import com.example.rattaggingapplication.databinding.TagsViewBinding;
import com.example.rattaggingapplication.db.tables.FeedAccess;
import com.example.rattaggingapplication.db.tables.FeedEmotions;
import com.example.rattaggingapplication.db.tables.FeedEvents;
import com.example.rattaggingapplication.db.tables.FeedRats;
import com.example.rattaggingapplication.db.tables.FeedTags;
import com.example.rattaggingapplication.db.tables.FeedUsers;
import com.example.rattaggingapplication.register.Tag;

import java.util.ArrayList;

public class TagsView extends Fragment {
    private TagsViewBinding binding;
    private SparseBooleanArray sp;
    private int tagidx = 0;
    private int eventid = MainActivity.eventid;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = TagsViewBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getActivity();

        binding.fileNo.setText(String.valueOf(tagidx));
        String[] columnsToReturn = { FeedRats.FeedEntryRats._ID, FeedRats.FeedEntryRats.COLUMN_NAME_NAME};
        String[] selectionArgs = {String.valueOf(userid )};
        Cursor ratsCursor = sqlDataBaseHandler.getValuesFromTable(FeedRats.FeedEntryRats.TABLE_NAME, columnsToReturn, FeedRats.FeedEntryRats.COLUMN_NAME_USER_ID,
                selectionArgs);


        SimpleCursorAdapter ratsadapter =
                new SimpleCursorAdapter(this.getContext(), android.R.layout.simple_list_item_multiple_choice, ratsCursor,
                        new String[] {FeedEmotions.FeedEntryEmotions.COLUMN_NAME_NAME}, new int[] {android.R.id.text1}, 0);

        binding.ratTagsListView.setAdapter(ratsadapter);



        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(context,
                R.array.rat_emotion_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.emotionsSpinner.setAdapter(spinnerAdapter);

        binding.ratTagsListView.setChoiceMode(binding.ratTagsListView.CHOICE_MODE_MULTIPLE);
        binding.ratTagsListView.setItemsCanFocus(false);
        binding.ratTagsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter, View view, int pos, long arg3){
                sp=binding.ratTagsListView.getCheckedItemPositions();
            }
        });
        binding.showTagsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TagsView.this)
                        .navigate(R.id.action_TagsViewNav_to_TagsListNav);
            }
        });
        binding.addTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileNo = binding.fileNo.getText().toString();
                String description = binding.description.getText().toString();
                String emotions =  binding.emotionsSpinner.getSelectedItem().toString();
                System.out.println(emotions);
                String[] columnsToReturn = {FeedEmotions.FeedEntryEmotions._ID};
                String[] selectionArgs = {emotions};

                Cursor resultCursor = sqlDataBaseHandler.getValuesFromTable(FeedEmotions.FeedEntryEmotions.TABLE_NAME, columnsToReturn, FeedEmotions.FeedEntryEmotions.COLUMN_NAME_NAME,
                        selectionArgs);
                resultCursor.moveToFirst();
                int emotionId = resultCursor.getInt(0);
                Tag newtag = new Tag();
                newtag.setDescription(description);
                newtag.setFilename(fileNo);
                newtag.setEmotions(emotionId);
                newtag.setEventId(eventid);
                sqlDataBaseHandler.addOneTag(newtag);
                tagidx += 1;
                binding.fileNo.setText(String.valueOf(tagidx));
                Cursor lastRow = sqlDataBaseHandler.getLastRowId(FeedTags.FeedEntryTags.TABLE_NAME);
                lastRow.moveToFirst();
                int tagId =  lastRow.getInt(0);
                String[] ratsColumnsToReturn = {FeedRats.FeedEntryRats._ID};

                Cursor ratresultCursor1;
                String[] values = {};
                sp=binding.ratTagsListView.getCheckedItemPositions();



                Cursor ratItem;
                for(int i=0;i<sp.size();i++){

                    ratItem = (Cursor) ratsadapter.getItem(sp.keyAt(i));
                    ratItem.moveToFirst();

                    values = new String[]{String.valueOf(userid), ratItem.getString(1)};

                    ratresultCursor1 = sqlDataBaseHandler.getTwoValuesFromTable(FeedRats.FeedEntryRats.TABLE_NAME,
                            ratsColumnsToReturn, FeedRats.FeedEntryRats.COLUMN_NAME_USER_ID,
                            FeedRats.FeedEntryRats.COLUMN_NAME_NAME, values);
                    for (int k=0;k<ratresultCursor1.getCount();k++){
                        ratresultCursor1.moveToFirst();
                        int ratId = ratresultCursor1.getInt(k);
                        sqlDataBaseHandler.addRatEvent(ratId, tagId);
                    }}

                }}

        );}



    }


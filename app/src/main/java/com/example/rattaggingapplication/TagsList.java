package com.example.rattaggingapplication;

import static com.example.rattaggingapplication.MainActivity.sqlDataBaseHandler;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rattaggingapplication.databinding.TagsListBinding;
import com.example.rattaggingapplication.db.tables.FeedEmotions;
import com.example.rattaggingapplication.db.tables.FeedTags;
import com.example.rattaggingapplication.db.tables.FeedUsers;

public class TagsList extends Fragment {
    private TagsListBinding binding;
    private int eventid = MainActivity.eventid;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = TagsListBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] columnsToReturn = {FeedTags.FeedEntryTags._ID,FeedTags.FeedEntryTags.COLUMN_NAME_FILENAME, FeedTags.FeedEntryTags.COLUMN_NAME_EMOTIONS};
        String[] selectionArgs = {String.valueOf(eventid) };
        Cursor tagCursor = sqlDataBaseHandler.getValuesFromTable(FeedTags.FeedEntryTags.TABLE_NAME, columnsToReturn, FeedTags.FeedEntryTags.COLUMN_NAME_EVENT_ID,
                selectionArgs);
        SimpleCursorAdapter tagsadapter =
                new SimpleCursorAdapter(this.getContext(), android.R.layout.simple_spinner_item, tagCursor,
                        new String[] {FeedTags.FeedEntryTags.COLUMN_NAME_FILENAME, FeedTags.FeedEntryTags.COLUMN_NAME_EMOTIONS}, new int[] {android.R.id.text1}, 0);
        binding.tagsListView.setAdapter(tagsadapter);
//        binding.tags.setAdapter(tagsadapter);
//        Cursor tagsCursor = sqlDataBaseHandler.getDataFromTable(FeedTags.FeedEntryEmotions.TABLE_NAME);
        binding.saveTagListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TagsList.this)
                        .navigate(R.id.action_TagsListNav_to_EventsViewNav);
            }
        });
        binding.tagsListExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TagsList.this)
                        .navigate(R.id.action_TagsListNav_to_EventsViewNav);
            }
        });
        binding.tagListBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TagsList.this)
                        .navigate(R.id.action_TagsListNav_to_EventsViewNav);
            }
        });

    }
}

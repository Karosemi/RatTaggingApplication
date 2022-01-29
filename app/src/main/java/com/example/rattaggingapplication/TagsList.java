package com.example.rattaggingapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rattaggingapplication.databinding.TagsListBinding;

public class TagsList extends Fragment {
    private TagsListBinding binding;
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

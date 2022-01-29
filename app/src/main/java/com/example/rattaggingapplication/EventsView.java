package com.example.rattaggingapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rattaggingapplication.databinding.EventsViewBinding;

public class EventsView extends Fragment {

    private EventsViewBinding binding;
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
        binding.confirmEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EventsView.this)
                        .navigate(R.id.action_EventsViewNav_to_TagsViewNav);
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

    }

}

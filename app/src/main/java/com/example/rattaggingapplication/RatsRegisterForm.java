package com.example.rattaggingapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rattaggingapplication.databinding.RatsRegisterFormBinding;

public class RatsRegisterForm extends Fragment {

    private RatsRegisterFormBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = RatsRegisterFormBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ratRegContainer.setVisibility(View.INVISIBLE);
        binding.addRatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.ratRegContainer.setVisibility(View.VISIBLE);
                binding.ratRegSave.setOnClickListener( new View.OnClickListener(){
                    @Override
                    public  void onClick(View view){
                        binding.ratRegContainer.setVisibility(View.INVISIBLE);
                    }
                });
            }}
        );


        binding.ratsRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RatsRegisterForm.this)
                        .navigate(R.id.action_RatsRegisterFormNav_to_EventsViewNav);
            }
        });
    }
}

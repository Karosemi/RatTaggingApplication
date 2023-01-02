package com.example.rattaggingapplication;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rattaggingapplication.databinding.RatRegisterViewBinding;
import com.example.rattaggingapplication.db.DbManager;
import com.example.rattaggingapplication.register.Rat;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RatsRegisterView extends Fragment {

    private RatRegisterViewBinding binding;
    private DbManager dbManager;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = RatRegisterViewBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int userid = MainActivity.userid;
        dbManager = new DbManager(this.getContext());
        hideRatRegisterWindow();
        Context context = getActivity();
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(context,
                R.array.rat_sex_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ratRegSpinner.setAdapter(spinnerAdapter);
        ArrayList<String> listItems = new ArrayList<String>();
        ArrayList<Rat> ratsList = new ArrayList<Rat>();
        ArrayAdapter<String> ratAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listItems);
        binding.ratsList.setAdapter(ratAdapter);

        binding.addratButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showRatRegisterWindow();
                binding.addratButton.setVisibility(View.INVISIBLE);


                binding.ratRegExit.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        hideRatRegisterWindow();
                        clearRegisterWindow();
                        binding.addratButton.setVisibility(View.VISIBLE);
                    }
                });

                binding.ratRegSave.setOnClickListener( new View.OnClickListener(){
                    @Override
                    public  void onClick(View view){
                        Editable ratName = binding.ratRegName.getText();
                        Editable ratDateBirth = binding.ratRegBirth.getText();
                        String ratSex = binding.ratRegSpinner.getSelectedItem().toString();
                        if (ratName.toString().isEmpty()){
                            Snackbar.make(view, "Add rat name", Snackbar.LENGTH_LONG).show();
                        }
                        else{
                            Rat newRat = new Rat();
                            newRat.setName(ratName.toString());
                            newRat.setDateOfBirth(ratDateBirth.toString());
                            newRat.setRatSex(ratSex);
                            newRat.setuserId(userid);
                            ratsList.add(newRat);
                            listItems.add(ratName.toString());
                            ratAdapter.notifyDataSetChanged();
                            hideRatRegisterWindow();
                            clearRegisterWindow();
                            binding.addratButton.setVisibility(View.VISIBLE);

                        }


                    }
                });
            }}
        );


        binding.ratsRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(ratsList.isEmpty())){
                    for (int i = 0; i < ratsList.size(); i++){
                        dbManager.addOneRat(ratsList.get(i));
                    }
                }

                NavHostFragment.findNavController(RatsRegisterView.this)
                        .navigate(R.id.action_RatsRegisterFormNav_to_EventsViewNav);
            }
        });
    }


    private void showRatRegisterWindow(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        binding.ratRegBirth.setText(dtf.format(now));
        changeVisibilityRegisterWindow("Visible"); }


    private void hideRatRegisterWindow(){
        changeVisibilityRegisterWindow("Gone");
}

    private void changeVisibilityRegisterWindow( String visibility){
        int new_visibility = View.GONE;
        if (visibility.equals("Visible")){
            new_visibility = View.VISIBLE;
        }
        else if(visibility.equals("Invisible")){
            new_visibility = View.INVISIBLE;
        }

        binding.ratRegContainer.setVisibility(new_visibility);
        binding.ratRegFieldSex.setVisibility(new_visibility);
        binding.ratRegSexText.setVisibility(new_visibility);
        binding.ratRegFieldBirth.setVisibility(new_visibility);
        binding.ratRegBirthText.setVisibility(new_visibility);
        binding.ratRegNameText.setVisibility(new_visibility);
        binding.ratRegFieldName.setVisibility(new_visibility);
        binding.ratRegName.setVisibility(new_visibility);
        binding.ratRegBirth.setVisibility(new_visibility);
        binding.ratRegSpinner.setVisibility(new_visibility);
        binding.ratRegSave.setVisibility(new_visibility);
        binding.ratRegExit.setVisibility(new_visibility);
    }




    private void clearRegisterWindow(){
        binding.ratRegName.setText("");
        binding.ratRegBirth.setText("");
    }

}

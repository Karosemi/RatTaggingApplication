package com.example.rattaggingapplication;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rattaggingapplication.databinding.RatRegisterViewBinding;
import com.example.rattaggingapplication.MainActivity;

import java.util.ArrayList;
import java.util.Objects;


public class RatsRegisterView extends Fragment {

    private RatRegisterViewBinding binding;
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
        hideRatRegisterWindow();
        Context context = getActivity();
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(context,
                R.array.rat_sex_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ratRegSpinner.setAdapter(spinnerAdapter);
        ArrayList<String> listItems = new ArrayList<String>();
        ArrayAdapter<String> ratAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listItems);
        binding.ratsList.setAdapter(ratAdapter);
//        list = (ListView) findViewById(R.id.listView);
//        arrayList = new ArrayList<String>();

        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data
//        ArrayAdapter<String> ratsAdapater = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

        // Here, you set the data in your ListView
//        list.setAdapter(adapter);
//        ListView ratslistView = binding.ratsList;
//        binding.ratRegContainer.setVisibility(View.INVISIBLE);
        binding.addratButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showRatRegisterWindow();
//                binding.ratRegContainer.setVisibility(View.VISIBLE);
                binding.addratButton.setVisibility(View.INVISIBLE);
                binding.ratRegSave.setOnClickListener( new View.OnClickListener(){
                    @Override
                    public  void onClick(View view){
//                        binding.ratRegContainer.setVisibility(View.INVISIBLE);
                        Editable ratName = binding.ratRegName.getText();
                        Editable ratDateBirth = binding.ratRegBirth.getText();
                        String ratSex = binding.ratRegSpinner.getSelectedItem().toString();

//                        binding.ratsList.new.
//                        String final_data = ratName
                        listItems.add(ratName + ", "+ratDateBirth+", "+ratSex);
                        ratAdapter.notifyDataSetChanged();
//                        ratAdapter.add();
                        hideRatRegisterWindow();
                        clearRegisterWindow();
//                        getActivity().getWindow().setSoftInputMode(
//                                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        binding.addratButton.setVisibility(View.VISIBLE);
                    }
                });
            }}
        );


        binding.ratsRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RatsRegisterView.this)
                        .navigate(R.id.action_RatsRegisterFormNav_to_EventsViewNav);
            }
        });
    }


    private void showRatRegisterWindow(){
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

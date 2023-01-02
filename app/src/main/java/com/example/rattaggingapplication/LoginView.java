package com.example.rattaggingapplication;

import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rattaggingapplication.databinding.EventsListBinding;
import com.example.rattaggingapplication.databinding.LoginViewBinding;
import com.example.rattaggingapplication.db.DbManager;
import com.example.rattaggingapplication.db.tables.FeedAccess;
import com.example.rattaggingapplication.db.tables.FeedUsers;
import com.google.android.material.snackbar.Snackbar;


public class LoginView extends Fragment {

    private LoginViewBinding binding;
    private DbManager dbManager;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = LoginViewBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        dbManager = new DbManager(this.getContext());
        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final Button registerButton = binding.loginRegister;
        super.onViewCreated(view, savedInstanceState);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String[] columnsToReturn = {FeedUsers.FeedEntryUsers.COLUMN_NAME_PASSWORD};
                String[] selectionArgs = { username };
                Cursor resultCursor = dbManager.getValuesFromTable(FeedUsers.FeedEntryUsers.TABLE_NAME, columnsToReturn, FeedUsers.FeedEntryUsers.COLUMN_NAME_USERNAME,
                        selectionArgs);
                if (resultCursor.getCount()>0){
                    resultCursor.moveToFirst();
                    String dbPassword = resultCursor.getString(0);
                    if (dbPassword.equals(password)){
                        String[] columnToReturn = {FeedUsers.FeedEntryUsers._ID};
                        Cursor idCursor = dbManager.getValuesFromTable(FeedUsers.FeedEntryUsers.TABLE_NAME, columnToReturn, FeedUsers.FeedEntryUsers.COLUMN_NAME_USERNAME,
                                selectionArgs);
                        idCursor.moveToFirst();
                        int userId = idCursor.getInt(0);
                        MainActivity.userid = userId;
                        System.out.println("zalogowany");
                        System.out.println(userId);
                        NavHostFragment.findNavController(LoginView.this)
                                .navigate(R.id.action_LoginView_to_EventsViewNav);
                    }
                    else {
                        Snackbar.make(view, "Username or password is incorrect", Snackbar.LENGTH_LONG).show();
                    }
                }
                else {
                    Snackbar.make(view, "Username or password is incorrect", Snackbar.LENGTH_LONG).show();
                }


            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginView.this)
                            .navigate(R.id.action_LoginView_to_RegisterForm);

            }
        });


    }


}
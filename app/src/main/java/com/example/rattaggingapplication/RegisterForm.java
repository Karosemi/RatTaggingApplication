package com.example.rattaggingapplication;

import static android.content.Context.USER_SERVICE;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.rattaggingapplication.databinding.RegisterFormBinding;
import com.example.rattaggingapplication.db.tables.FeedUsers;
import com.example.rattaggingapplication.register.User;
import com.google.android.material.snackbar.Snackbar;
import static com.example.rattaggingapplication.MainActivity.sqlDataBaseHandler;

public class RegisterForm extends Fragment {

    private RegisterFormBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = RegisterFormBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.regUsername.getText().toString();
                String password = binding.regPassword.getText().toString();
                String confirmPassword = binding.regConfirmPassword.getText().toString();
                String firstName = binding.regFirstname.getText().toString();
                String lastName = binding.regLastname.getText().toString();
                String email = binding.regEmail.getText().toString();
                String phone = binding.regPhone.getText().toString();
                String city = binding.regCity.getText().toString();
                if (username.isEmpty() | password.isEmpty() | confirmPassword.isEmpty() | email.isEmpty()| firstName.isEmpty()|lastName.isEmpty()){
                    Snackbar.make(view, "Required fields are empty", Snackbar.LENGTH_LONG).show();
                }
                else if (!password.equals(confirmPassword)){
                    Snackbar.make(view, "Passwords are different", Snackbar.LENGTH_LONG).show();
                }
                else {
                    String[] columnsToReturn = {FeedUsers.FeedEntryUsers.COLUMN_NAME_USERNAME};
                    String[] selectionArgs = { username };
                    String[] nextSelectionArgs = { email };
                    Cursor nameCursor = sqlDataBaseHandler.getValuesFromTable(FeedUsers.FeedEntryUsers.TABLE_NAME, columnsToReturn, FeedUsers.FeedEntryUsers.COLUMN_NAME_USERNAME,
                            selectionArgs);
                    Cursor mailCursor = sqlDataBaseHandler.getValuesFromTable(FeedUsers.FeedEntryUsers.TABLE_NAME, columnsToReturn, FeedUsers.FeedEntryUsers.COLUMN_NAME_USERNAME,
                            nextSelectionArgs);
                    if ((nameCursor.getCount()==0)&(mailCursor.getCount()==0) ){
                        User newUser = new User();
                        newUser.setUsername(username);
                        newUser.setPassword(password);
                        newUser.setFirstName(firstName);
                        newUser.setLastName(lastName);
                        newUser.setEmail(email);
                        newUser.setPhone(phone);
                        newUser.setCity(city);
                        sqlDataBaseHandler.addStandardUser(newUser);
                        String[] columnToReturn = {FeedUsers.FeedEntryUsers._ID};
//                        String[] nextSelectionArgs = { email };
                        Cursor idCursor = sqlDataBaseHandler.getValuesFromTable(FeedUsers.FeedEntryUsers.TABLE_NAME, columnToReturn, FeedUsers.FeedEntryUsers.COLUMN_NAME_USERNAME,
                                selectionArgs);
                        idCursor.moveToFirst();
                        int userId = idCursor.getInt(0);
                        MainActivity.userid = userId;
                        System.out.println("zalogowany");
                        System.out.println(userId);
                        NavHostFragment.findNavController(RegisterForm.this)
                                .navigate(R.id.action_RegisterView_to_RatsRegisterView);
                    }
                    else if (nameCursor.getCount()==0){
                        Snackbar.make(view, "User already exists", Snackbar.LENGTH_LONG).show();
                    }
                    else{
                        Snackbar.make(view, "Email already exists", Snackbar.LENGTH_LONG).show();
                    }
                }

            }
        });
    }


}

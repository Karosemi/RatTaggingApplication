package com.example.rattaggingapplication;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.rattaggingapplication.databinding.LoginViewBinding;


public class LoginView extends AppCompatActivity {

    private LoginViewBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = LoginViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavController navController = Navigation.findNavController(this, R.id.LoginViewNav);


        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;

        //TODO get string and on click login for now got to register view




        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadingProgressBar.setVisibility(View.VISIBLE);
                String username = usernameEditText.getText().toString();
                String password =passwordEditText.getText().toString();
               if  (!((username.isEmpty()) | (password.isEmpty()))){
                    navController.navigate(R.id.action_LoginView_to_RegisterForm);
               }
               }

        });
    }

}
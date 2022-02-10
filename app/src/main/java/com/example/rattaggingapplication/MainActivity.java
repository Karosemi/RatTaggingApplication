package com.example.rattaggingapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.rattaggingapplication.databinding.MainActivityBinding;
import com.example.rattaggingapplication.databinding.RatRegisterViewBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private MainActivityBinding binding;
    private RatRegisterViewBinding binding_b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
    private void closeKeyboard() {
        try{ View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm;
            imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
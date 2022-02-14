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
import com.example.rattaggingapplication.db.SqlDataBaseHandler;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    public static int userid;
    public static int eventid;
    private MainActivityBinding binding;
    public static SqlDataBaseHandler sqlDataBaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userid = -1;
        eventid = -1;
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sqlDataBaseHandler = new SqlDataBaseHandler(this);
        int oldVersion = 0;
        int newVersion = 1;
        sqlDataBaseHandler.onUpgrade(sqlDataBaseHandler.getWritableDatabase(), oldVersion, newVersion);

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
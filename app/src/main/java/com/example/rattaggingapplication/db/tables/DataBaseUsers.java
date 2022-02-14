package com.example.rattaggingapplication.db.tables;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import com.example.rattaggingapplication.R;
import com.example.rattaggingapplication.db.SqlDataBaseHandler;
import com.example.rattaggingapplication.register.User;

public class DataBaseUsers {


    private User createUserAdmin(){

        User adminUser = new User();
        String userAdminName = "admin";
        String userAdminPassword = "adm01";
        adminUser.setUsername(userAdminName);
        adminUser.setPassword(userAdminPassword);
        adminUser.setCity("");
        adminUser.setEmail("");
        adminUser.setPhone("");
        adminUser.setFeedBack(0);
        adminUser.setFirstName("");
        adminUser.setLastName("");
        return adminUser;

    }
    public User getUserAdmin(){
        return createUserAdmin();
    }



}

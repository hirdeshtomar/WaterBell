package com.mobile.waterapp.parselogin;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by hito on 10-05-2015.
 */
public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "TRZxJMiyaORkXLIDNvdjqBOaQ5FgPwqwpD63Bxz5", "sITcenJtDLN0JAEskyjOLXEODaASrW3bbY7C3e9A"); // Application ID and client key from parse

        ParseUser.enableAutomaticUser();
        ParseACL defaultAcl = new ParseACL();

        defaultAcl.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultAcl, true);
    }
}

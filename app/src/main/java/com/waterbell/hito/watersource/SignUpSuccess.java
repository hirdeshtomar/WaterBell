package com.waterbell.hito.watersource;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobile.waterapp.search.SearchSuppliers;
import com.parse.ParseUser;


public class SignUpSuccess extends ActionBarActivity {

    Button btnLogInContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_success);

        ParseUser currentUser  = ParseUser.getCurrentUser();

        String struser = currentUser.getUsername().toString();

        TextView txtUser = (TextView) findViewById(R.id.captionRegSuccessSmall);
        txtUser.setText("You are logged in as" + txtUser);
        btnLogInContinue = (Button) findViewById(R.id.btnLogInContinue);

        btnLogInContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("SIGN UP Success", "Inside OnClickListener");
                goToSearchSuppliers(v);


            }
        });
    }



    public void goToSearchSuppliers(View view) {

        Intent intent = new Intent(this, SearchSuppliers.class);
        startActivity(intent);

    }

}

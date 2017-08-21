package com.waterbell.hito.watersource;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.mobile.waterapp.search.SearchSuppliers;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;



public class Login extends ActionBarActivity {

    Button btnSignUp, btnSignIn;
    EditText pwdEdTxt, userIdEdTxt;
    TextView pwdResetTxt;
    String username, password;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSignUp =  (Button) findViewById(R.id.btnSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        pwdEdTxt = (EditText)  findViewById(R.id.pwdEdTxt);
        userIdEdTxt = (EditText)  findViewById(R.id.userIdEdTxt);
        pwdResetTxt = (TextView) findViewById(R.id.captionPwdReset);

        pwdResetTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Password Reset Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
                if (activeNetwork != null && activeNetwork.isConnected()) {

                    goToSignUp(v);

                } else {

                    Toast.makeText(getApplicationContext(), "Network Unavailable. Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
                if (activeNetwork != null && activeNetwork.isConnected()) {

                    pd = ProgressDialog.show(Login.this, "Signing You In", "Please wait...", true, true);
                    username = userIdEdTxt.getText().toString();
                    password =  pwdEdTxt.getText().toString();

                    ParseUser.logInInBackground(username, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            pd.dismiss();
                            if(user != null){

                                Intent intent = new Intent(Login.this, SearchSuppliers.class);
                                startActivity(intent);
                                finish();

                            }
                            else{

                                Toast.makeText(getApplicationContext(), "Invalid Username or Password.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "Network Unavailable. Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
                    return;
                }




            }
        });


    }

    public void goToSignUp(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void goToSearchSuppliers(View view) {

        Intent intent = new Intent(this, SearchSuppliers.class);
        startActivity(intent);

    }

}

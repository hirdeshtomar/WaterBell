package com.waterbell.hito.watersource;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.waterapp.search.SearchSuppliers;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignUp extends ActionBarActivity {

    Button btnNewAct;

    String username, password, cnfPassword;
    EditText usernameEdTxt, passwordExTxt, cnfPasswordEdTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnNewAct = (Button) findViewById(R.id.btnNewAct);
        usernameEdTxt = (EditText) findViewById(R.id.txtEmailId);
        passwordExTxt = (EditText) findViewById(R.id.txtPwd);
        cnfPasswordEdTxt = (EditText) findViewById(R.id.txtCnfPwd);

        btnNewAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = usernameEdTxt.getText().toString();
                password = passwordExTxt.getText().toString();
                cnfPassword = cnfPasswordEdTxt.getText().toString();

                if((username == "") || (password == "") || (cnfPassword == ""))
                {
                    Toast.makeText(getApplicationContext(), "Please complete the sign up form", Toast.LENGTH_SHORT).show();
                }
                else{

                    ParseUser user = new ParseUser();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(username);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {

                            if(e==null) {

                                Toast.makeText(getApplicationContext(), "Successfully signed up", Toast.LENGTH_SHORT).show();

                            }

                            else {

                                Toast.makeText(getApplicationContext(), "Sign Up Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }


                goToSignUpSuccess(v);

            }
        });
    }

    public void goToSignUpSuccess(View view) {
        Intent intent = new Intent(this, SignUpSuccess.class);
        startActivity(intent);
    }

    public void goToSearchSuppliers(View view) {

        Intent intent = new Intent(this, SearchSuppliers.class);
        startActivity(intent);

    }

}

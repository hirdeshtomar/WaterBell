package com.waterbell.hito.watersource;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddWaterSupplier extends ActionBarActivity {

    EditText nameTxt, locationTxt, cityTxt, stateTxt, emailIdTxt, contactNumberTxt, addressTxt;

    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_water_supplier);

        submitBtn = (Button) findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerSupplierDetails();
                Intent intent = new Intent(getApplicationContext(), RegistrationSuccess.class);

            }
        });

    }

    public void registerSupplierDetails(){

        nameTxt = (EditText) findViewById(R.id.name);
        locationTxt = (EditText) findViewById(R.id.location);
        cityTxt = (EditText) findViewById(R.id.city);
        stateTxt = (EditText) findViewById(R.id.state);
        emailIdTxt = (EditText)findViewById(R.id.emailId);
        contactNumberTxt = (EditText) findViewById(R.id.contactNumber);
        addressTxt = (EditText) findViewById(R.id.address);

        WaterSupplier waterSupplier = new WaterSupplier(nameTxt.getText().toString(),
                    locationTxt.getText().toString(),
                    cityTxt.getText().toString(),
                    stateTxt.getText().toString(),
                    emailIdTxt.getText().toString(),
                    contactNumberTxt.getText().toString(),
                    addressTxt.getText().toString());

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d("Insert:", "Inserting...");
        db.addWaterSupplier(waterSupplier);


    }




}

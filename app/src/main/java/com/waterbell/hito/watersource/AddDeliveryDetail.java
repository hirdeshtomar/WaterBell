package com.waterbell.hito.watersource;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobile.waterapp.http.RestService;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;


public class AddDeliveryDetail extends ActionBarActivity {

    Button  saveAndContinue;
    EditText name, mobile, houseStreet,area;
    Spinner city;
    private ProgressDialog pd;
    String[] params = new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__delivery__detail);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String amount = getIntent().getStringExtra("amount");
       // String houseStreet = getIntent().getStringExtra("houseStreet");
     //   String area = getIntent().getStringExtra("area");
     //   String city = getIntent().getStringExtra("city");
        final String bisleriCount = getIntent().getStringExtra("bisleriCount");
        final String normalBottleCount = getIntent().getStringExtra("normalBottleCount");


        saveAndContinue = (Button) findViewById(R.id.saveAndContinue);
        name = (EditText) findViewById(R.id.customerName);
        mobile = (EditText) findViewById(R.id.phoneNum);
        houseStreet = (EditText) findViewById(R.id.houseStreet);
        area = (EditText) findViewById(R.id.locality);
        city = (Spinner) findViewById(R.id.citySpinner);


        saveAndContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String strName = name.getText().toString();
                if (strName.matches("")) {
                    Toast.makeText(getApplicationContext(), "You did not enter a username", Toast.LENGTH_SHORT).show();
                    return;
                }

                String strMobile = mobile.getText().toString();
                if (strMobile.matches("")) {
                    Toast.makeText(getApplicationContext(), "You did not enter a mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }

                String strHouse = houseStreet.getText().toString();
                if (strHouse.matches("")) {
                    Toast.makeText(getApplicationContext(), "You did not enter a house street", Toast.LENGTH_SHORT).show();
                    return;
                }

                String strArea = area.getText().toString();
                if (strArea.matches("")) {
                    Toast.makeText(getApplicationContext(), "You did not enter a locality", Toast.LENGTH_SHORT).show();
                    return;
                }

                PostCustomerTask mytask = new PostCustomerTask();
               // mytask.execute("http://54.148.218.169:8080/com.mobile.waterapp.rest/api/addCustomer");
                mytask.execute("http://54.148.218.169:8080/com.mobile.waterapp.rest/api/addCustomer/createCustomer");

                pd = ProgressDialog.show(AddDeliveryDetail.this, "Please Wait...", "Adding Your Delivery Details", true, true);
                Intent intent = new Intent(getApplicationContext(), ConfirmOrder.class);
                intent.putExtra("name" , name.getText().toString());
                intent.putExtra("mobile", mobile.getText().toString());
                intent.putExtra("city", String.valueOf(city.getSelectedItem()));
                intent.putExtra("houseStreet" , houseStreet.getText().toString());
                intent.putExtra("normalBottleCount" , normalBottleCount);
                intent.putExtra("bisleriCount", bisleriCount);
                intent.putExtra("amount", amount);
                intent.putExtra("area", area.getText().toString());

                startActivity(intent);
            }
        });
    }


    private class PostCustomerTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls) {
            String content = null;
            try {
                params[0] = name.getText().toString();
                params[1] = mobile.getText().toString();
                params[2] = houseStreet.getText().toString();
                params[3] = area.getText().toString();
                params[4] = String.valueOf(city.getSelectedItem());
                params[5] = "Karnataka";
                params[6] = "India";

                content = RestService.callPOSTURL(urls[0], params);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return content;
        }

        protected void onPostExecute(String result) {
            pd.dismiss();
            Log.d("RESULT_ADD_DELIVERY", result);

        }
    }
}

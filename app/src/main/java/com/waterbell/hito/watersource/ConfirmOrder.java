package com.waterbell.hito.watersource;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobile.waterapp.http.RestService;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;


public class ConfirmOrder extends ActionBarActivity {

    TextView orderSummary, costSummary, hsValue, areaValue, cityValue;
    Button confirmOrderBtn;
    String[] params = new String[6];
    private ProgressDialog pd;
    String amount, bisleriCount, normalBottleCount, mobile , email;
    String orderMessage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        amount = getIntent().getStringExtra("amount");
        bisleriCount = getIntent().getStringExtra("bisleriCount");
        normalBottleCount = getIntent().getStringExtra("normalBottleCount");
        mobile = getIntent().getStringExtra("mobile");


        final String area = getIntent().getStringExtra("area");
        final String name = getIntent().getStringExtra("name");

        final String city = getIntent().getStringExtra("city");
        final String houseStreet = getIntent().getStringExtra("houseStreet");

        hsValue = (TextView) findViewById(R.id.hsValue);
        areaValue = (TextView) findViewById(R.id.areaValue);
        cityValue = (TextView) findViewById(R.id.cityValue);

        costSummary = (TextView) findViewById(R.id.costSummary);
        confirmOrderBtn = (Button) findViewById(R.id.confirmOrderBtn);
        hsValue.setText(houseStreet);
        areaValue.setText(area);
        cityValue.setText(city);
        costSummary.setText("Rs. " + amount);

        confirmOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(bisleriCount != "0" && normalBottleCount != "0") {

                     orderMessage = "Order+Received+BottleType:Bisleri+Quantity:+" + bisleriCount + "+Nixie+Quantity:+" + normalBottleCount +"+Customer+Name:+" + name + "+Address+" + houseStreet + ",+" + area + "+" + city + "+Phone:+" + mobile ;

                } else if(bisleriCount == "0"){
                     orderMessage = "Order+Received+BottleType:Nixie+Quantity:+" + normalBottleCount +"+Customer+Name:+" + name + "+Address+" + houseStreet + ",+" + area + "+" + city + "+Phone:+" + mobile ;

                } else if(normalBottleCount == "0"){

                     orderMessage = "Order+Received+BottleType:Bisleri+Quantity:+" + bisleriCount +"+Customer+Name:+" + name + "+Address+" + houseStreet + ",+" + area + "+" + city + "+Phone:+" + mobile ;

                }


                PostOrderTask myOrderTask = new PostOrderTask();
                // myOrderTask.execute("http://54.148.218.169:8080/com.mobile.waterapp.rest/api/addOrder");
                myOrderTask.execute("http://54.148.218.169:8080/com.mobile.waterapp.rest/api/addCustomer/createOrder");
                pd = ProgressDialog.show(ConfirmOrder.this, "Please Wait...", "Adding Your Order Details", true, true);
                orderMessage = orderMessage.replaceAll(" ", "+");

                SendMessageTask mytask = new SendMessageTask();
                mytask.execute("http://54.148.218.169:8080/com.mobile.waterapp.rest/api/sendMessage/toSupplier");
                //mytask.execute("https://rest.nexmo.com/sms/json?api_key=956c2201&api_secret=db827a52&from=NEXMO&to=91" + mobile + "&text=" + orderMessage);
                Intent intent = new Intent(getApplicationContext(), PaymentSuccess.class);
                startActivity(intent);
            }
        });
    }

    private class SendMessageTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls) {

            params[0] = orderMessage;
          //  String content = RestService.readJSONFeed(urls[0]);
            String content = null;
            try {
                content = RestService.callPOSTURL(urls[0], params);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return content;
        }
        protected void onPostExecute(String result) {


        }
    }

    private class PostOrderTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls) {
            String content = null;
            try {
                ParseUser currentUser = ParseUser.getCurrentUser();
                email = currentUser.getEmail();
                params[0] = "Bisleri, Nixie";
                params[1] = "80.00, 50.00";
                params[2] = "B: " + bisleriCount + ", N: " + normalBottleCount;
                params[3] = amount;
                params[4] = mobile;
                params[5] = email;
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
            Log.d("RESULT_CONFIRM_ORDER", result);
        }
    }

}

package com.waterbell.hito.watersource;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;

import com.mobile.waterapp.entities.CustomerOrders;
import com.mobile.waterapp.http.RestService;
import com.mobile.waterapp.search.utils.adapters.CustomerOrderListAdapter;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class YourOrders extends Activity {

    String[] params = new String[1];
    String email;

    public List<CustomerOrders> customerOrders = new ArrayList<CustomerOrders>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_orders);

        ParseUser currentUser = ParseUser.getCurrentUser();
        email = currentUser.getEmail();

        GetOrdersTask getOrders = new GetOrdersTask();
        getOrders.execute("http://54.148.218.169:8080/com.mobile.waterapp.rest/api/addCustomer/getOrders");
    }


    public void displayOrders(String result) throws JSONException {

        ListView ordersList = (ListView) findViewById(R.id.orders_listview);

        JSONArray items = new JSONArray(result);
        if(items.length()==0){
            customerOrders.clear();

        } else {
            customerOrders.clear();
            for(int i=0;i<items.length();i++){

                JSONObject obj = items.getJSONObject(i);
                String order_id = obj.getString("ORDER_ID");
                String product = obj.getString("PRODUCT_ID");
                String price = obj.getString("PRICE");
                String qty = obj.getString("QUANTITY");
                String total_amt = obj.getString("TOTAL_AMOUNT");
                String order_date= obj.getString("BILL_DATE");
                String email_id = obj.getString("EMAIL_ID");

                CustomerOrders order = new CustomerOrders();
                order.setOrderId(order_id);
                order.setAmount(total_amt);
                order.setOrderDate(order_date);
                order.setQty(qty);
                order.setProduct(product);

                customerOrders.add(i,order);
            }
        }

        CustomerOrderListAdapter adapter = new CustomerOrderListAdapter(this, customerOrders);
        ordersList.setAdapter(adapter);


    }

    private class GetOrdersTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            params[0] = email;

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

            try {
                displayOrders(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}

package com.waterbell.hito.watersource;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.waterapp.search.utils.adapters.ProductListAdapter;


public class AddItems extends ActionBarActivity {

    TextView shopTag, shopName, bisleriCount, normalWaterCount, totalCost;
    Button plusBtn, minusBtn, normalPlusBtn, normalMinusBtn, addCartBtn;
    EditText houseStreet, area, city;

     int countBisleriBottle = 0;
     int countNormalBottle = 0;
     float amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String supName = getIntent().getStringExtra("SupplierName");
        String supAddress = getIntent().getStringExtra("Address");
        ListView prodList = (ListView) findViewById(R.id.prodList);
        ProductListAdapter productAdapter = new ProductListAdapter(this);
        prodList.setAdapter(productAdapter);


        prodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                long viewId = view.getId();
                long btnId = id;

                Log.d("POSITION", Integer.toString(position));

                if (position == 0) {
                    if (btnId == 1) {
                        addCartBtn.setEnabled(true);
                        countBisleriBottle = countBisleriBottle + 1;
                        amount = 80 * countBisleriBottle + 50 * countNormalBottle;
                        totalCost.setText("Rs. " + Float.toString(amount));

                    } else if (btnId == 2) {

                        if (countBisleriBottle > 0) {
                            countBisleriBottle = countBisleriBottle - 1;
                            amount = 80 * countBisleriBottle + 50 * countNormalBottle;
                            totalCost.setText("Rs. " + Float.toString(amount));
                            if(countNormalBottle == 0 && countBisleriBottle == 0){
                                addCartBtn.setEnabled(false);
                            }
                        }
                    }
                }

                if (position == 1) {

                    if (btnId == 1) {

                        addCartBtn.setEnabled(true);
                        countNormalBottle = countNormalBottle + 1;
                        amount = 80 * countBisleriBottle + 50 * countNormalBottle;
                        totalCost.setText("Rs. " + Float.toString(amount));

                    } else if (btnId == 2) {

                        if (countNormalBottle > 0) {
                            countNormalBottle = countNormalBottle - 1;
                            amount = 80 * countBisleriBottle + 50 * countNormalBottle;
                            totalCost.setText("Rs. " + Float.toString(amount));

                            if(countNormalBottle == 0 && countBisleriBottle == 0){
                                addCartBtn.setEnabled(false);
                            }
                        }
                    }
                }
            }
        });

        shopTag = (TextView) findViewById(R.id.shopTag);
        shopName = (TextView) findViewById(R.id.shopName);
        totalCost = (TextView) findViewById(R.id.totalCost);
        addCartBtn = (Button) findViewById(R.id.addCartBtn);
        amount = 80 * countBisleriBottle + 50 * countNormalBottle;
        totalCost.setText("Rs. " + Float.toString(amount));

        addCartBtn.setEnabled(false);


        addCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), ConfirmOrder.class);
                Intent intent = new Intent(getApplicationContext(), AddDeliveryDetail.class);
                intent.putExtra("amount", Float.toString(amount));
                intent.putExtra("bisleriCount", Integer.toString(countBisleriBottle));
                intent.putExtra("normalBottleCount", Integer.toString(countNormalBottle));
                startActivity(intent);

            }
        });

        shopTag.setText("Supplier Name : " + supName);
        shopName.setText("Location : " + supAddress);
    }


}

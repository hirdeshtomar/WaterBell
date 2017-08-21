package com.waterbell.hito.watersource;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class showSuppliersActivity extends ActionBarActivity  {
    private Runnable viewOrders;
    public List<WaterSupplier> waterSuppliers = new ArrayList<WaterSupplier>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_suppliers);
        final ListView suppliersListView = (ListView) findViewById(R.id.supplierList);
        String result = getIntent().getStringExtra("result");

        try {
            JSONArray items = new JSONArray(result);
                for (int i = 0; i < items.length(); i++) {
                JSONObject sup = items.getJSONObject(i);
                String shopName = sup.getString("SHOP_NAME");
                String address = sup.getString("ADDRESS");
                WaterSupplier ws = new WaterSupplier();
                ws.set_address(address);
                ws.set_name(shopName);
                waterSuppliers.add(i, ws);
            }
            SupplierAdapter adapter = new SupplierAdapter(this, waterSuppliers);
            suppliersListView.setAdapter(adapter);
        }catch (Exception e) {
            Log.d("ReadWeatherJSONFeedTask", e.getLocalizedMessage());
        }

        suppliersListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 WaterSupplier ws = (WaterSupplier) parent.getItemAtPosition(position);
               Log.d("WS_NAME",   ws.get_name().toString());
                Log.d("WS_NAME",   ws.get_address().toString());





            }
        });
    }
}

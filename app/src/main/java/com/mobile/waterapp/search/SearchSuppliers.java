package com.mobile.waterapp.search;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.waterapp.search.utils.adapters.NavigationDrawerListAdapter;
import com.waterbell.hito.watersource.AddItems;
import com.waterbell.hito.watersource.Login;
import com.waterbell.hito.watersource.R;
import com.waterbell.hito.watersource.SupplierAdapter;
import com.waterbell.hito.watersource.WaterSupplier;
import com.mobile.waterapp.http.RestService;
import com.mobile.waterapp.search.utils.adapters.SearchOptionAdapter;
import com.mobile.waterapp.utils.ui.keyboard.KeyboardUtil;
import com.parse.ParseUser;
import com.waterbell.hito.watersource.YourOrders;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchSuppliers extends ActionBarActivity {


    public List<WaterSupplier> waterSuppliers = new ArrayList<WaterSupplier>();
    SearchView search;
    String[] popularCitiesArray = {

            "Sarjapur Road", "HSR  Layout Sec 1",
            "HSR  Layout Sec 2",
            "HSR  Layout Sec 3", "Whitefield", "Bannerghatta Road", "Electronic City", "HSR Layout", "Jayanagar", "JP Nagar", "Hosur Road", "Indira Nagar", "Koramangala", "Yelahanka", "Hesaraghatta Main Road", "Devanahalli", "Marathahalli", "Hebbal", "Kanakapura Road", "Anekal", "Hennur Road", "C V Raman Nagar", "Kalyan Nagar", "RT Nagar", "Jalahalli", "HRBR Layout", "BTM Layout", "Ramamurthy Nagar", "Brooke Field", "Jakkur", "Dollars Colony", "Abbigere", "Rajanukunte", "G M Palya", "Kundalahalli", "Madiwala", "Fraser Town", "Chandapur", "Gottigere", "Basavanagar", "Nagarbhavi", "Bellandur", "Hoskote", "Chamarajpet", "Richards Town", "Lavelle Road", "Kodigehalli", "Chikkajala", "Hanumanth Nagar", "Akshaya Nagar", "Thanisandra", "Sarjapur", "Hegde Nagar", "Jigani Industrial Area", "Mathikere", "Rest House Road", "Begur Road", "Rajajinagar", "MG Road", "HBR Layout", "Banaswadi", "Uttarahalli", "Airport Road", "Thippasandra", "Banashankari", "Bagalur", "Horamavu", "KR Puram", "Bommanahalli", "OMBR Layout", "Mysore Road", "Silkboard", "RMV Extension Stage", "Old Madras Road", "Kasturi Nagar", "Richmond Road", "Vidyaranyapura", "Mahadevapura", "Rajarajeshwari Nagar", "Malleshwaram", "AECS Layout", "Defence Colony", "Kanaka Nagar", "Hulimavu", "Thyagaraj Nagar", "Basaveshwaranagar", "Airport Area", "Kumaraswamy Layout", "Sanjay Nagar", "Hoskote", "Kudlu Gate", "RBI Layout", "Palace Road", "Hoodi Village", "Millers Road", "Huskur", "Vijaya Bank Layout", "Shanti Nagar", "Hebbal Kempapura", "Tippasandra", "Naganathapura", "Yeshwantpur", "Jeevan Bima Nagar", "Cox Town", "Ulsoor", "Benson Town", "ITPL", "Doddaballapur", "Kaggadaspura", "Nagwar", "Attibele", "Padmanabhanagar", "Vijayanagar", "Kengeri", "Peenya", "Magadi Road", "Bangalore", "NelaMangala", "Sahakar Nagar", "Dodballapur Road", "Outer Ring Road", "Vigyan Nagar", "Basavanagudi", "Mallesh Palaya", "Domlur", "Old Airport Road", "Bellary Road", "Sadaramangala", "Anjanapura", "Majestic", "Vasanth Nagar", "Wilson Garden", "ISRO Layout", "HMT Layout", "Nagawara", "Doddaballapur Road", "Central Silk Board", "Nandi Hills", "Ganganagar", "Bommasandra", "Indraprastha",
    };
    private ProgressDialog pd;

    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mTitle;
    private ActionBarDrawerToggle mDrawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_suppliers);

//***********************************************************

        mTitle = "test";

        mPlanetTitles = new String[]{"MyOrders", "Log out"};
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
       // mDrawerList.setAdapter(new ArrayAdapter<String>(this,
         //       R.layout.drawer_item_list, mPlanetTitles));

       NavigationDrawerListAdapter navAdapter = new NavigationDrawerListAdapter(this);
        mDrawerList.setAdapter(navAdapter);

        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
               // getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
             //   getActionBar().setTitle(mTitle);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);




    //***************************************************************






       /* ActionBar actionBar = getSupportActionBar(); Commented to hide Custom Action Bar on 16th June
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView iconTxt = (TextView) mCustomView.findViewById(R.id.icon_text);*/

        TextView searchTxt = (TextView) findViewById(R.id.captionSearchTxt);
        Typeface robotoThin = Typeface.createFromAsset(getAssets(), getString(R.string.typeface_robotoThin));
        Typeface robotoBold = Typeface.createFromAsset(getAssets(), getString(R.string.typeface_robotoBold));
        Typeface porkys = Typeface.createFromAsset(getAssets(), getString(R.string.typeface_porkys));
        searchTxt.setTypeface(robotoBold);
        /*
        iconTxt.setTypeface(porkys);
        actionBar.setCustomView(mCustomView);
        actionBar.setDisplayShowCustomEnabled(true);*/

        final AutoCompleteTextView auto_city_list = (AutoCompleteTextView) findViewById(R.id.auto_city_names);
        final TextView captionWaterSup = (TextView) findViewById(R.id.captionWaterSup);
        captionWaterSup.setVisibility(View.GONE);

        SearchOptionAdapter searchOptionAdapter = new SearchOptionAdapter(this);
        ArrayAdapter<String> cityArrayAdapater = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, popularCitiesArray);


        auto_city_list.setAdapter(cityArrayAdapater);
        auto_city_list.setThreshold(1);
        auto_city_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                auto_city_list.clearFocus();
                String selection = (String) parent.getItemAtPosition(position);
                selection = selection.replaceAll(" ", "%20");

                final ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
                if (activeNetwork != null && activeNetwork.isConnected()) {
                    // notify user you are online

                    ReadWeatherJSONFeedTask mytask = new ReadWeatherJSONFeedTask();
                    mytask.execute("http://54.148.218.169:8080/com.mobile.waterapp.rest/api/v1/suppliers/" + selection);
                    pd = ProgressDialog.show(SearchSuppliers.this, "Please Wait...", "Fetching Suppliers in your area", true, true);
                    captionWaterSup.setVisibility(View.VISIBLE);

                } else {
                    // notify user you are not online

                    Toast.makeText(getApplicationContext(), "Network Unavailable. Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }


//************************************************************

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {
        Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        //setTitle(mPlanetTitles[position]);
       if(position == 0)
           onLogOut();
        else if(position == 1){
           Intent intent = new Intent(getApplicationContext(), YourOrders.class);
           startActivity(intent);
       }
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

  //******************************************************************************


    public void updateDisplay(String result) {
        ListView popularCities = (ListView) findViewById(R.id.PopularCitiesList);
        try {
            JSONArray items = new JSONArray(result);
            if (items.length() == 0) {
                waterSuppliers.clear();
                SupplierAdapter adapter = new SupplierAdapter(this, waterSuppliers);
                popularCities.setAdapter(adapter);

                new AlertDialog.Builder(this)
                        .setTitle("Sorry")
                        .setMessage("Currently WaterBell is not operational in your area. We will reach there soon.")
                        .setCancelable(true)
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            } else {
                waterSuppliers.clear();
                for (int i = 0; i < items.length(); i++) {
                    JSONObject sup = items.getJSONObject(i);
                    String shopName = sup.getString("SHOP_NAME");
                    String address = sup.getString("ADDRESS");
                    WaterSupplier ws = new WaterSupplier();
                    ws.set_address(address);
                    ws.set_name(shopName);
                    waterSuppliers.add(i, ws);
                }

                KeyboardUtil.hideKeyboard(SearchSuppliers.this);
                SupplierAdapter adapter = new SupplierAdapter(this, waterSuppliers);
                popularCities.setAdapter(adapter);

            }

        } catch (Exception e) {
            Log.d("ReadWeatherJSONFeedTask", e.getLocalizedMessage());
        }

        popularCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WaterSupplier ws = (WaterSupplier) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), AddItems.class);
                intent.putExtra("SupplierName", ws.get_name().toString());
                intent.putExtra("Address", ws.get_address().toString());
                startActivity(intent);

            }
        });
    }

    private class ReadWeatherJSONFeedTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls) {
            String content = RestService.readJSONFeed(urls[0]);
            return content;
        }

        protected void onPostExecute(String result) {
            pd.dismiss();
            updateDisplay(result);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_suppliers, menu);

      //  ParseUser currentUser = ParseUser.getCurrentUser();
      //  String username = currentUser.getUsername().toString();
      //  MenuItem menuItem = menu.findItem(R.id.userIdLogin);
       // menuItem.setTitle("Sign out");

        return true;
    }

    public String onLogOut(){


        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Do you wish to Log Out?")
                .setCancelable(true)
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getApplicationContext(), "onLogOut", Toast.LENGTH_SHORT).show();
                        ParseUser currentUser = ParseUser.getCurrentUser();
                        currentUser.logOut();
                        String username = currentUser.getUsername().toString();
                        Toast.makeText(getApplicationContext(), "User is logged out, username is:  " + username, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), Login.class);

                        startActivity(intent);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();




        return("ok");
    }
}

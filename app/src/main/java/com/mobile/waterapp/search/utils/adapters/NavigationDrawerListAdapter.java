package com.mobile.waterapp.search.utils.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.waterbell.hito.watersource.R;

/**
 * Created by hito on 25-07-2015.
 */
public class NavigationDrawerListAdapter extends BaseAdapter {

    LayoutInflater inflater;

    public NavigationDrawerListAdapter(Activity context) {

        super();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        if(convertView == null)
            vi = inflater.inflate(R.layout.drawer_item_list, null);
        TextView rowTextView = (TextView) vi.findViewById(R.id.rowText);
        ImageView rowImage = (ImageView) vi.findViewById(R.id.rowIcon);
        if(position == 0) {
            rowTextView.setText("Log out");
            rowImage.setImageResource(R.drawable.logout_icon);
        } else if(position == 1){
            rowTextView.setText("My Orders");
            rowImage.setImageResource(R.drawable.logout_icon);
        }




        return vi;

    }


}

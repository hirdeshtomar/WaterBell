package com.mobile.waterapp.search.utils.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.waterbell.hito.watersource.R;

/**
 * Created by hito on 16-02-2015.
 */
public class SearchOptionAdapter extends BaseAdapter {

    LayoutInflater inflater;


    String[] searchOptions = {"Near My Location", "POPULAR OPTIONS"};
    public SearchOptionAdapter(Activity context){
        super();
        Log.d("IN SUP ADAPTER", "IN ADAPTER CONSTRUCTOR");
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return searchOptions.length;
    }

    @Override
    public Object getItem(int position){
        return null;
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        String city = searchOptions[position];

        View vi = convertView;
        if(convertView == null)
            vi = inflater.inflate(R.layout.searchoptions, null);
        TextView cityView = (TextView) vi.findViewById(R.id.searchOptions);

        cityView.setText(city);

        return vi;
    }
}



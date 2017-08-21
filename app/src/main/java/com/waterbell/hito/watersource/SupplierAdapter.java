package com.waterbell.hito.watersource;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hito on 30-01-2015.
 */
public class SupplierAdapter extends BaseAdapter{
    LayoutInflater inflater;
    List<WaterSupplier> waterSuppliers;
    public SupplierAdapter(Activity context, List<WaterSupplier> waterSuppliers){
        super();
        Log.d("IN SUP ADAPTER", "IN ADAPTER CONSTRUCTOR");
        this.waterSuppliers = waterSuppliers;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return waterSuppliers.size();
    }

    @Override
    public Object getItem(int position){
        return waterSuppliers.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        WaterSupplier ws = waterSuppliers.get(position);
        View vi = convertView;
        if(convertView == null)
        vi = inflater.inflate(R.layout.row, null);
        TextView topText = (TextView) vi.findViewById(R.id.toptext);
        TextView bottomText = (TextView) vi.findViewById(R.id.bottomtext);
        topText.setText(ws._name.toString());
        bottomText.setText(ws._address.toString());

        return vi;
    }

}

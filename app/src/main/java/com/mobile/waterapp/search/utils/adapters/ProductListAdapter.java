package com.mobile.waterapp.search.utils.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.waterbell.hito.watersource.R;

/**
 * Created by hito on 24-05-2015.
 */
public class ProductListAdapter extends BaseAdapter {

    LayoutInflater inflater;

    int bisleriCount = 0;

    public ProductListAdapter(Activity context) {

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
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final ViewHolder holder;
        int i=1;
        View vi = convertView;


        if (convertView == null) {

            vi = inflater.inflate(R.layout.product_list, null);
            holder = new ViewHolder();
            holder.prodTitle = (TextView) vi.findViewById(R.id.productTitle);
            holder.prodDesc = (TextView) vi.findViewById(R.id.productDesc);
            holder.prodPrice = (TextView) vi.findViewById(R.id.price);
            holder.quantity = (EditText) vi.findViewById(R.id.quantity);
            holder.plus = (Button) vi.findViewById(R.id.addBtn);
            holder.minus = (Button) vi.findViewById(R.id.subtractBtn);
            holder.bottleImg = (ImageView) vi.findViewById(R.id.icon);
            holder.plus.setId(i);
            holder.minus.setId(i);
            i++;

            vi.setTag(holder);


        }

        else{
            holder=(ViewHolder) convertView.getTag();
        }

        Log.d("Position", Integer.toString(position));

        if(position == 0) {
            holder.prodTitle.setText("Bisleri");
            holder.prodDesc.setText("20 Litres, Packaged bisleri water");
            holder.prodPrice.setText("Rs. 80.00");
            holder.quantity.setText(String.valueOf(0));
            holder.bottleImg.setImageResource(R.drawable.bisleri);

        }

        else if (position == 1) {

            holder.prodTitle.setText("Nixie ");
            holder.prodDesc.setText("20 Litres, Packaged normal water");
            holder.prodPrice.setText("Rs. 50.00");
            holder.quantity.setText(String.valueOf(0));
            holder.bottleImg.setImageResource(R.drawable.normal);

        }
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = holder.quantity.getText().toString();
                int num = Integer.parseInt(value);
                num = num + 1;
                holder.quantity.setText(String.valueOf(num));

                Log.d("PLUS", "Inside Plus Button");

                        ((ListView) parent).performItemClick(v, position, 1);

            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = holder.quantity.getText().toString();
                int num = Integer.parseInt(value);
                if(num != 0) {
                    num = num - 1;
                    ((ListView) parent).performItemClick(v, position, 2);

                }

                Log.d("MINUS", "Inside Plus Button");
                holder.quantity.setText(String.valueOf(num));
            }
        });

        return vi;
    }

    static class ViewHolder {

        ImageView bottleImg;
        TextView prodTitle;
        TextView prodDesc;
        TextView prodPrice;
        Button plus;
        Button minus;
        EditText quantity;

    }
}

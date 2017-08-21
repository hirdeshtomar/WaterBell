package com.mobile.waterapp.search.utils.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobile.waterapp.entities.CustomerOrders;
import com.waterbell.hito.watersource.R;

import java.util.List;

/**
 * Created by Dell 15 on 01-11-2015.
 */
public class CustomerOrderListAdapter extends BaseAdapter {

    LayoutInflater inflater;
    List<CustomerOrders> customerOrders;

    public CustomerOrderListAdapter(Activity context, List<CustomerOrders> customerOrders){
        super();
        this.customerOrders = customerOrders;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return customerOrders.size();
    }

    @Override
    public Object getItem(int position) {
        return customerOrders.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        CustomerOrders order = customerOrders.get(position);
        View vi = convertView;
        if(convertView == null)
            vi = inflater.inflate(R.layout.customer_orders_list, null);
        TextView orderDate = (TextView) vi.findViewById(R.id.orderDate);
        TextView orderDetail = (TextView) vi.findViewById(R.id.orderDetail);
        TextView shipAddress = (TextView) vi.findViewById(R.id.shipAddress);

        orderDate.setText(order.getOrderDate());
        orderDetail.setText(order.getProduct() + " Quantity: " + order.getQty() + " Amount: " + order.getAmount() );

        return vi;
    }
}

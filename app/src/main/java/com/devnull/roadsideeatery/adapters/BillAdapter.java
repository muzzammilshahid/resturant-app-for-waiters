package com.devnull.roadsideeatery.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.devnull.roadsideeatery.R;
import com.devnull.roadsideeatery.db.ItemData;

import java.util.ArrayList;

public class BillAdapter extends ArrayAdapter<ItemData> {

    private ArrayList<ItemData> itemDataArrayList;
    private Activity context;

    public BillAdapter(Activity context, ArrayList<ItemData> itemDataArrayList) {
        super(context, R.layout.delegate_bill);
        this.itemDataArrayList = itemDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = context.getLayoutInflater().inflate(R.layout.delegate_bill, parent,
                    false);
            viewHolder = new ViewHolder();
            viewHolder.titleTextView = convertView.findViewById(R.id.title);
            viewHolder.amountTextView = convertView.findViewById(R.id.amount);
            convertView.setTag(viewHolder);
            Log.i("TAG", " creating new");
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            Log.i("TAG", " using old one");
        }

        ItemData product = itemDataArrayList.get(position);

        viewHolder.titleTextView.setText(product.getItemName());
        viewHolder.amountTextView.setText(product.getQuantity() + " x " + product.getPrice());
        convertView.setFocusable(false);
        return convertView;
    }

    @Override
    public int getCount() {
        return itemDataArrayList.size();
    }

    @Nullable
    @Override
    public ItemData getItem(int position) {
        return itemDataArrayList.get(position);
    }

    class ViewHolder {
        TextView titleTextView;
        TextView amountTextView;
    }
}

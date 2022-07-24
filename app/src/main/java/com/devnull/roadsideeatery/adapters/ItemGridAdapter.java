package com.devnull.roadsideeatery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.devnull.roadsideeatery.R;
import com.devnull.roadsideeatery.db.ItemData;

import java.util.ArrayList;

public class ItemGridAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ItemData> itemDataArrayList;

    public ItemGridAdapter(Context applicationContext, ArrayList<ItemData> productArrayList) {
        this.context = applicationContext;
        this.itemDataArrayList = productArrayList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return itemDataArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.delegate_product, null);

        TextView titleTextview = view.findViewById(R.id.title_textview);
        TextView descriptionTextView = view.findViewById(R.id.description_textview);
        TextView priceTextview = view.findViewById(R.id.price_textview);
        TextView quantityTextview = view.findViewById(R.id.quantity_textview);
        ImageButton addImageButton = view.findViewById(R.id.add_image_button);
        ImageButton removeImageButton = view.findViewById(R.id.remove_image_button);

        ItemData itemData = itemDataArrayList.get(i);
        titleTextview.setText(itemData.getItemName());
        priceTextview.setText("Rs. " + itemData.getPrice());
        descriptionTextView.setText(itemData.getDescription());

        addImageButton.setOnClickListener(view1 -> {
            itemData.setQuantity(Integer.parseInt(quantityTextview.getText().toString().trim()) + 1);
            quantityTextview.setText(itemData.getQuantity() + "");
        });

        removeImageButton.setOnClickListener(view1 -> {
            if (Integer.parseInt(quantityTextview.getText().toString().trim()) > 0) {
                itemData.setQuantity(Integer.parseInt(quantityTextview.getText().toString().trim()) - 1);
                quantityTextview.setText(itemData.getQuantity() + "");
            }
        });

        return view;
    }
}

package com.devnull.roadsideeatery.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.devnull.roadsideeatery.R;
import com.devnull.roadsideeatery.adapters.ItemGridAdapter;
import com.devnull.roadsideeatery.databinding.FragmentHomeBinding;
import com.devnull.roadsideeatery.db.ItemData;
import com.devnull.roadsideeatery.db.RoomDB;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ArrayList<ItemData> itemDataArrayList = new ArrayList<>();
    private ArrayList<ItemData> selectedItemArrayList = new ArrayList<>();
    private ItemGridAdapter itemGridAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemGridAdapter = new ItemGridAdapter(requireActivity(), itemDataArrayList);
        binding.simpleGridView.setAdapter(itemGridAdapter);

        itemDataArrayList.clear();
        selectedItemArrayList.clear();

        itemDataArrayList.addAll(RoomDB.getInstance(requireContext()).mainDao().getAll());
        itemGridAdapter.notifyDataSetChanged();

        binding.orderButton.setOnClickListener(view1 -> {
            for (int i = 0; i < itemDataArrayList.size(); i++) {
                ItemData product = itemDataArrayList.get(i);
                if (product.getQuantity() > 0) {
                    selectedItemArrayList.add(product);
                }
            }

            if (selectedItemArrayList.size() > 0) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("products", selectedItemArrayList);
                Fragment frag1;
                frag1 = new BillFragment();
                frag1.setArguments(bundle);
                FragmentTransaction transaction1 = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.frame, frag1).addToBackStack(frag1.getClass().getName());
                transaction1.commit();
            } else {
                Toast.makeText(requireActivity(), "Please select products to order", Toast.LENGTH_SHORT).show();
            }

        });

        binding.searchInChats.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                itemDataArrayList.clear();
                List<ItemData> secondList = RoomDB.getInstance(requireContext()).mainDao().getAll();
                for (int j = 0; j < secondList.size(); j++) {
                    ItemData itemData = secondList.get(j);
                    if (itemData.getItemName().contains(charSequence)) {
                        itemDataArrayList.add(itemData);
                    }
                }
                itemGridAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
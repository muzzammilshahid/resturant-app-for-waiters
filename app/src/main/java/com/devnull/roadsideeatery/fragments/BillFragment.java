package com.devnull.roadsideeatery.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.devnull.roadsideeatery.R;
import com.devnull.roadsideeatery.adapters.BillAdapter;
import com.devnull.roadsideeatery.databinding.FragmentBillBinding;
import com.devnull.roadsideeatery.db.ItemData;

import java.util.ArrayList;

public class BillFragment extends Fragment {

    private FragmentBillBinding binding;
    private int total = 0;
    private ArrayList<ItemData> arrayListItemData = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBillBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        arrayListItemData = (ArrayList<ItemData>) bundle.getSerializable("products");

        BillAdapter billAdapter = new BillAdapter(requireActivity(), arrayListItemData);

        binding.selectedItemsList.setAdapter(billAdapter);

        for (int i = 0; i < arrayListItemData.size(); i++) {
            ItemData product = arrayListItemData.get(i);
            total = total + (product.getPrice() * product.getQuantity());
        }

        binding.totalTextview.setText("Rs. " + total);
        binding.confirmButton.setOnClickListener(view1 -> {
            Fragment frag1;
            frag1 = new HomeFragment();
            FragmentTransaction transaction1 = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction1.replace(R.id.frame, frag1);
            transaction1.commit();
        });

    }
}
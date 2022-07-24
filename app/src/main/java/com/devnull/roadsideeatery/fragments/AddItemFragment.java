package com.devnull.roadsideeatery.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.devnull.roadsideeatery.databinding.FragmentAddItemBinding;
import com.devnull.roadsideeatery.db.ItemData;
import com.devnull.roadsideeatery.db.RoomDB;

public class AddItemFragment extends Fragment {

    private FragmentAddItemBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddItemBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addItem.setOnClickListener(view1 -> {
            if (binding.textItemName.getEditText().getText().toString().trim().isEmpty()) {
                binding.textItemName.setError("Please enter the item name");
            } else if (binding.textItemPrice.getEditText().getText().toString().trim().isEmpty()) {
                binding.textItemPrice.setError("Please enter the price of the item");
            } else {
                ItemData itemData = new ItemData();
                itemData.setItemName(binding.textItemName.getEditText().getText().toString().trim());
                itemData.setDescription(binding.textItemDescription.getEditText().getText().toString().trim());
                itemData.setPrice(Integer.parseInt(binding.textItemPrice.getEditText().getText().toString().trim()));
                RoomDB.getInstance(getContext()).mainDao().insert(itemData);

                binding.textItemName.getEditText().getText().clear();
                binding.textItemDescription.getEditText().getText().clear();
                binding.textItemPrice.getEditText().getText().clear();
                binding.textItemName.getEditText().clearFocus();
                binding.textItemDescription.getEditText().clearFocus();
                binding.textItemPrice.getEditText().clearFocus();
                View view2 = requireActivity().getCurrentFocus();
                if (view2 != null) {
                    InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
    }
}
package com.devnull.roadsideeatery;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.devnull.roadsideeatery.databinding.ActivityMainBinding;
import com.devnull.roadsideeatery.fragments.AddItemFragment;
import com.devnull.roadsideeatery.fragments.HistoryFragment;
import com.devnull.roadsideeatery.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imageView = findViewById(R.id.menu_imageview);
        imageView.setOnClickListener(view -> binding.drawerLayout.openDrawer(GravityCompat.START));

        LinearLayout homeLinearLayout = findViewById(R.id.linear_home);
        LinearLayout addIItemLinearLayout = findViewById(R.id.linear_add_item);

        Fragment frag;
        frag = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, frag);
        transaction.commit();
        binding.drawerLayout.closeDrawers();

        homeLinearLayout.setOnClickListener(view -> {
            Fragment frag1;
            frag1 = new HomeFragment();

            FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
            transaction1.replace(R.id.frame, frag1);
            transaction1.commit();
            binding.drawerLayout.closeDrawers();
        });

        addIItemLinearLayout.setOnClickListener(view -> {
            Fragment frag1;
            frag1 = new AddItemFragment();

            FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
            transaction1.replace(R.id.frame, frag1).addToBackStack(frag1.getClass().getName());
            transaction1.commit();
            binding.drawerLayout.closeDrawers();
        });
    }
}
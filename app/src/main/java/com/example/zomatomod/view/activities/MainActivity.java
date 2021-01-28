package com.example.zomatomod.view.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import com.example.zomatomod.R;
import com.example.zomatomod.databinding.ActivityMainBinding;
import com.example.zomatomod.repository.ApiClient;
import com.example.zomatomod.repository.ApiInterface;
import com.example.zomatomod.repository.PreferenceConfig;
import com.example.zomatomod.view.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    public static PreferenceConfig preferenceConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        preferenceConfig = new PreferenceConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_container, homeFragment).addToBackStack(null).commit();

    }
}
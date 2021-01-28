package com.example.zomatomod.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatomod.R;
import com.example.zomatomod.databinding.FragmentHomeBinding;
import com.example.zomatomod.model.LocationDetailsModel;
import com.example.zomatomod.model.RestaurantArrayModel;
import com.example.zomatomod.view.activities.MainActivity;
import com.example.zomatomod.view.adapters.RestaurantRecyclerViewAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    TextView locationName;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    RelativeLayout locationLayout, searchLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentHomeBinding fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        locationName = fragmentHomeBinding.locationName;
        recyclerView = fragmentHomeBinding.restaurantRv;
        locationLayout = fragmentHomeBinding.locationLayout;
        searchLayout = fragmentHomeBinding.searchLayout;

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        locationLayout.setOnClickListener(view -> {
            LocationListFragment locationListFragment = new LocationListFragment();
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.main_container, locationListFragment).addToBackStack(null).commit();

        });

        searchLayout.setOnClickListener(view -> {
            SearchFoodFragment searchFoodFragment = new SearchFoodFragment();
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.main_container, searchFoodFragment).addToBackStack(null).commit();

        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        getLocationDetails();

        return fragmentHomeBinding.getRoot();
    }

    private void getLocationDetails() {

        Call<LocationDetailsModel> call = MainActivity.apiInterface.getLocationDetails(MainActivity.preferenceConfig.readEntityId(), MainActivity.preferenceConfig.readEntityType());

        call.enqueue(new Callback<LocationDetailsModel>() {
            @Override
            public void onResponse(Call<LocationDetailsModel> call, Response<LocationDetailsModel> response) {

                locationName.setText(response.body().getLocationModel().getCityName());
                ArrayList<RestaurantArrayModel> restaurantArrayModelArrayList = response.body().getRestaurantModelArrayList();
                RestaurantRecyclerViewAdapter adapter = new RestaurantRecyclerViewAdapter(restaurantArrayModelArrayList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<LocationDetailsModel> call, Throwable t) {

                System.out.println(t);
            }
        });
    }
}
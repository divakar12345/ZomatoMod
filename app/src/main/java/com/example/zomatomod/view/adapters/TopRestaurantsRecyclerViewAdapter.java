package com.example.zomatomod.view.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatomod.R;
import com.example.zomatomod.databinding.RestaurantnameCardBinding;
import com.example.zomatomod.model.RestaurantArrayModel;
import com.example.zomatomod.model.RestaurantModel;
import com.example.zomatomod.view.fragment.RestaurantFragment;

import java.util.ArrayList;

public class TopRestaurantsRecyclerViewAdapter extends RecyclerView.Adapter<TopRestaurantsRecyclerViewAdapter.ViewHolder> {
    ArrayList<RestaurantArrayModel> restaurantModelArrayList;

    public TopRestaurantsRecyclerViewAdapter(ArrayList<RestaurantArrayModel> restaurantModelArrayList) {
        this.restaurantModelArrayList = restaurantModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RestaurantnameCardBinding restaurantnameCardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.restaurantname_card, parent, false);
        return new ViewHolder(restaurantnameCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RestaurantModel currentRestaurant = restaurantModelArrayList.get(position).getRestaurantModel();

        holder.restaurantName.setText(currentRestaurant.getRestaurantName());

        Bundle args = new Bundle();
        args.putString("restaurant_name", currentRestaurant.getRestaurantName());
        args.putInt("restaurant_id", currentRestaurant.getRestaurantId());
        args.putString("cuisines", currentRestaurant.getCuisines());
        args.putString("user_rating", Float.toString(currentRestaurant.getUserRatingModel().getAggregateRating()));
        args.putString("all_reviews_count", Integer.toString(currentRestaurant.getReviewCount()));

        holder.parentLayout.setOnClickListener(view -> {

            AppCompatActivity activity = (AppCompatActivity) view.getContext();

            final InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            RestaurantFragment restaurantFragment = new RestaurantFragment();
            restaurantFragment.setArguments(args);
            activity.getSupportFragmentManager().beginTransaction().add(R.id.main_container, restaurantFragment).addToBackStack(null).commit();
        });
    }

    @Override
    public int getItemCount() {
        return restaurantModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RestaurantnameCardBinding binding;
        TextView restaurantName;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull RestaurantnameCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            restaurantName = binding.restaurantName;
            parentLayout = binding.parentLayout;
        }
    }
}


package com.example.zomatomod.view.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zomatomod.R;
import com.example.zomatomod.databinding.RestaurantCardBinding;
import com.example.zomatomod.model.RestaurantArrayModel;
import com.example.zomatomod.model.RestaurantModel;
import com.example.zomatomod.view.fragment.RestaurantFragment;

import java.util.ArrayList;

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.ViewHolder> {
    ArrayList<RestaurantArrayModel> restaurantModelArrayList;

    public RestaurantRecyclerViewAdapter(ArrayList<RestaurantArrayModel> restaurantModelArrayList) {
        this.restaurantModelArrayList = restaurantModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RestaurantCardBinding restaurantCardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.restaurant_card, parent, false);
        return new ViewHolder(restaurantCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RestaurantModel currentRestaurant = restaurantModelArrayList.get(position).getRestaurantModel();

        holder.restaurantName.setText(currentRestaurant.getRestaurantName());
        holder.cuisines.setText(currentRestaurant.getCuisines());
        Glide.with(holder.featureImage.getContext())
                .load(currentRestaurant.getFeaturedImage())
                .centerCrop()
                .override(2000, 630)
                .into(holder.featureImage);

        Bundle args = new Bundle();
        args.putString("restaurant_name", currentRestaurant.getRestaurantName());
        args.putInt("restaurant_id", currentRestaurant.getRestaurantId());
        args.putString("cuisines", currentRestaurant.getCuisines());
        args.putString("user_rating", Float.toString(currentRestaurant.getUserRatingModel().getAggregateRating()));
        args.putString("all_reviews_count", Integer.toString(currentRestaurant.getReviewCount()));

        holder.parentLayout.setOnClickListener(view -> {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
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
        RestaurantCardBinding binding;
        ImageView featureImage;
        TextView restaurantName, cuisines;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull RestaurantCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            featureImage = binding.featureImage;
            restaurantName = binding.restaurantName;
            cuisines = binding.cuisines;
            parentLayout = binding.parentLayout;
        }
    }
}

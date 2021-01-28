package com.example.zomatomod.view.adapters;

import android.content.Context;
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
import com.example.zomatomod.databinding.LocationCardBinding;
import com.example.zomatomod.model.SuggestedLocationModel;
import com.example.zomatomod.view.activities.MainActivity;
import com.example.zomatomod.view.fragment.HomeFragment;

import java.util.ArrayList;

public class LocationListRecyclerViewAdapter extends RecyclerView.Adapter<LocationListRecyclerViewAdapter.ViewHolder> {
    ArrayList<SuggestedLocationModel> suggestedLocationModelArrayList;

    public LocationListRecyclerViewAdapter(ArrayList<SuggestedLocationModel> suggestedLocationModelArrayList) {
        this.suggestedLocationModelArrayList = suggestedLocationModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LocationCardBinding locationCardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.location_card, parent, false);
        return new ViewHolder(locationCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SuggestedLocationModel currentLocation = suggestedLocationModelArrayList.get(position);

        holder.cityName.setText(currentLocation.getCityName());

        holder.parentLayout.setOnClickListener(view -> {

            MainActivity.preferenceConfig.writeEntityId(currentLocation.getEntityId());
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            final InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            HomeFragment homeFragment = new HomeFragment();
            activity.getSupportFragmentManager().beginTransaction().add(R.id.main_container, homeFragment).addToBackStack(null).commit();
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        });
    }

    @Override
    public int getItemCount() {
        return suggestedLocationModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LocationCardBinding binding;
        TextView cityName;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull LocationCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            cityName = binding.cityName;
            parentLayout = binding.parentLayout;
        }
    }
}


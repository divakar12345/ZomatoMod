package com.example.zomatomod.view.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatomod.R;
import com.example.zomatomod.databinding.MenuCardBinding;
import com.example.zomatomod.model.DishDetailsModel;
import com.example.zomatomod.model.DishesModel;

import java.util.ArrayList;

public class DailyMenuRecyclerViewAdapter extends RecyclerView.Adapter<DailyMenuRecyclerViewAdapter.ViewHolder> {
    ArrayList<DishesModel> dishesModelArrayList;

    public DailyMenuRecyclerViewAdapter(ArrayList<DishesModel> dishesModelArrayList) {
        this.dishesModelArrayList = dishesModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MenuCardBinding menuCardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.menu_card, parent, false);
        return new ViewHolder(menuCardBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DishDetailsModel currentReview = dishesModelArrayList.get(position).getDishDetailsModel();

        holder.dishName.setText(currentReview.getDishName());

    }

    @Override
    public int getItemCount() {
        return dishesModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MenuCardBinding binding;
        TextView dishName;

        public ViewHolder(@NonNull MenuCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            dishName = binding.dishName;
        }
    }
}

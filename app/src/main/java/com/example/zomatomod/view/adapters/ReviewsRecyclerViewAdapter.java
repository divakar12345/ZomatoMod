package com.example.zomatomod.view.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatomod.R;
import com.example.zomatomod.databinding.ReviewsCardBinding;
import com.example.zomatomod.model.UserReviewModel;
import com.example.zomatomod.model.UserReviewsArrayModel;

import java.util.ArrayList;

public class ReviewsRecyclerViewAdapter extends RecyclerView.Adapter<ReviewsRecyclerViewAdapter.ViewHolder> {
    ArrayList<UserReviewsArrayModel> reviewsArrayModelArrayList;

    public ReviewsRecyclerViewAdapter(ArrayList<UserReviewsArrayModel> reviewsArrayModelArrayList) {
        this.reviewsArrayModelArrayList = reviewsArrayModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ReviewsCardBinding reviewsCardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.reviews_card, parent, false);
        return new ViewHolder(reviewsCardBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserReviewModel currentReview = reviewsArrayModelArrayList.get(position).getUserReviewModel();

        holder.userName.setText(currentReview.getUserModel().getUserName());
        holder.userRating.setText(Integer.toString(currentReview.getUserRating()));
        holder.userReview.setText(currentReview.getReviewText());

    }

    @Override
    public int getItemCount() {
        return reviewsArrayModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ReviewsCardBinding binding;
        TextView userName, userRating, userReview;

        public ViewHolder(@NonNull ReviewsCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            userName = binding.userName;
            userRating = binding.rating;
            userReview = binding.reviewText;
        }
    }
}

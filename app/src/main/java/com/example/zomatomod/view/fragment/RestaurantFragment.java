package com.example.zomatomod.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatomod.R;
import com.example.zomatomod.databinding.FragmentRestaurantBinding;
import com.example.zomatomod.model.DailyMenuArrayModel;
import com.example.zomatomod.model.DishesModel;
import com.example.zomatomod.model.ReviewBodyModel;
import com.example.zomatomod.model.UserReviewsArrayModel;
import com.example.zomatomod.view.activities.MainActivity;
import com.example.zomatomod.view.adapters.DailyMenuRecyclerViewAdapter;
import com.example.zomatomod.view.adapters.ReviewsRecyclerViewAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantFragment extends Fragment {

    public RestaurantFragment() {
        // Required empty public constructor
    }

    private ImageView backButton;
    private TextView restaurantName, userRating, cuisines, reviewCount, noMenu;
    LinearLayoutManager linearLayoutManagerReview, linearLayoutManagerMenu;
    RecyclerView recyclerViewReview, recyclerViewMenu;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentRestaurantBinding fragmentRestaurantBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_restaurant, container, false);

        backButton = fragmentRestaurantBinding.backButton;
        restaurantName = fragmentRestaurantBinding.restaurantName;
        userRating = fragmentRestaurantBinding.userRating;
        cuisines = fragmentRestaurantBinding.cuisines;
        reviewCount = fragmentRestaurantBinding.reviewCount;
        recyclerViewReview = fragmentRestaurantBinding.reviewsRv;
        recyclerViewMenu = fragmentRestaurantBinding.menuRv;
        noMenu = fragmentRestaurantBinding.noMenu;

        linearLayoutManagerReview = new LinearLayoutManager(getContext());
        linearLayoutManagerMenu = new LinearLayoutManager(getContext());

        recyclerViewReview.setLayoutManager(linearLayoutManagerReview);
        recyclerViewMenu.setLayoutManager(linearLayoutManagerMenu);

        restaurantName.setText(getArguments().getString("restaurant_name"));
        cuisines.setText(getArguments().getString("cuisines"));
        userRating.setText(getArguments().getString("user_rating"));
        reviewCount.setText("(" + getArguments().getString("all_reviews_count") + " Reviews)");

        getMenulist();

        getReviews();

        backButton.setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().beginTransaction().remove(RestaurantFragment.this).commit();
        });

        return fragmentRestaurantBinding.getRoot();
    }

    private void getMenulist() {

        Call<DailyMenuArrayModel> call = MainActivity.apiInterface.getMenuList(getArguments().getInt("restaurant_id"));

        call.enqueue(new Callback<DailyMenuArrayModel>() {
            @Override
            public void onResponse(Call<DailyMenuArrayModel> call, Response<DailyMenuArrayModel> response) {

                try {
                    ArrayList<DishesModel> dailyMenuModelArrayList = response.body().getDailyMenuModelArrayList().get(0).getDishesArrayModel().getDishesModelArrayList();
                    DailyMenuRecyclerViewAdapter adapter = new DailyMenuRecyclerViewAdapter(dailyMenuModelArrayList);
                    recyclerViewMenu.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } catch (NullPointerException e) {

                    System.out.println(e);
                    noMenu.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<DailyMenuArrayModel> call, Throwable t) {

                System.out.println(t);
            }
        });

    }

    private void getReviews() {

        Call<ReviewBodyModel> call = MainActivity.apiInterface.getReviews(getArguments().getInt("restaurant_id"));

        call.enqueue(new Callback<ReviewBodyModel>() {
            @Override
            public void onResponse(Call<ReviewBodyModel> call, Response<ReviewBodyModel> response) {

                ArrayList<UserReviewsArrayModel> userReviewsArrayModels = response.body().getUserReviewsModelArrayList();
                ReviewsRecyclerViewAdapter reviewsRecyclerViewAdapter = new ReviewsRecyclerViewAdapter(userReviewsArrayModels);
                recyclerViewReview.setAdapter(reviewsRecyclerViewAdapter);
                reviewsRecyclerViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ReviewBodyModel> call, Throwable t) {

                System.out.println(t);
            }
        });
    }
}
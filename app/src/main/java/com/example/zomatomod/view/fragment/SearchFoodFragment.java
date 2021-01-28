package com.example.zomatomod.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatomod.R;
import com.example.zomatomod.databinding.FragmentSearchFoodBinding;
import com.example.zomatomod.model.SearchModel;
import com.example.zomatomod.view.activities.MainActivity;
import com.example.zomatomod.view.adapters.TopRestaurantsRecyclerViewAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFoodFragment extends Fragment {

    public SearchFoodFragment() {
        // Required empty public constructor
    }

    private EditText foodEdittext;
    private RecyclerView foodRv;
    private LinearLayoutManager linearLayoutManager;
    private ImageView backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentSearchFoodBinding fragmentSearchFoodBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_food, container, false);

        backButton = fragmentSearchFoodBinding.backButton;
        foodEdittext = fragmentSearchFoodBinding.foodEdittext;
        foodRv = fragmentSearchFoodBinding.foodRv;

        backButton.setOnClickListener(view -> {
            final InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            getActivity().getSupportFragmentManager().beginTransaction().remove(SearchFoodFragment.this).commit();
        });

        linearLayoutManager = new LinearLayoutManager(getContext());

        foodEdittext.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence query, int start,
                                      int before, int count) {

                getRestaurantNames(query.toString());

            }
        });

        return fragmentSearchFoodBinding.getRoot();
    }

    private void getRestaurantNames(String query) {

        Call<SearchModel> call = MainActivity.apiInterface.getSearchResult(MainActivity.preferenceConfig.readEntityId(), query);

        call.enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {

                TopRestaurantsRecyclerViewAdapter adapter = new TopRestaurantsRecyclerViewAdapter(response.body().getRestaurantArrayModelArrayList());
                foodRv.setLayoutManager(linearLayoutManager);
                foodRv.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

                System.out.println(t);
            }
        });
    }
}
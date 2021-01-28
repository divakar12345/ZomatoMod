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
import com.example.zomatomod.ViewModel.LocationListViewModel;
import com.example.zomatomod.databinding.FragmentLocationListBinding;
import com.example.zomatomod.view.adapters.LocationListRecyclerViewAdapter;

public class LocationListFragment extends Fragment {

    public LocationListFragment() {
        // Required empty public constructor
    }

    private EditText locationEdittext;
    private LocationListViewModel locationListViewModel;
    private RecyclerView locationRv;
    private LinearLayoutManager linearLayoutManager;
    private ImageView backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentLocationListBinding fragmentLocationListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_location_list, container, false);

        locationEdittext = fragmentLocationListBinding.locationEdittext;
        locationRv = fragmentLocationListBinding.locationRv;
        backButton = fragmentLocationListBinding.backButton;

        backButton.setOnClickListener(view -> {
            final InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            getActivity().getSupportFragmentManager().beginTransaction().remove(LocationListFragment.this).commit();
        });

        linearLayoutManager = new LinearLayoutManager(getContext());

        locationEdittext.addTextChangedListener(new TextWatcher() {

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

                getSuggestedLocations(query.toString());

            }
        });

        return fragmentLocationListBinding.getRoot();

    }

    private void getSuggestedLocations(String query) {

        locationListViewModel = new LocationListViewModel(getActivity().getApplication(), query);

        locationListViewModel.getSuggestedLocations().observe(getViewLifecycleOwner(), list -> {
            System.out.println("Observer Team Fragment called");
            System.out.println("List size " + list.size());

            LocationListRecyclerViewAdapter adapter = new LocationListRecyclerViewAdapter(list);
            locationRv.setLayoutManager(linearLayoutManager);
            locationRv.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        });
    }
}
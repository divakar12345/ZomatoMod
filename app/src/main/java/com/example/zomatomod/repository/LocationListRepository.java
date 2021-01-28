package com.example.zomatomod.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.zomatomod.model.LocationListModel;
import com.example.zomatomod.model.SuggestedLocationModel;
import com.example.zomatomod.view.activities.MainActivity;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationListRepository {

    private Application application;
    private MutableLiveData<ArrayList<SuggestedLocationModel>> suggestedLD;

    public LocationListRepository(Application application) {
        this.application = application;
        suggestedLD = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<SuggestedLocationModel>> getSuggestedLocations(String query) {

        Call<LocationListModel> call = MainActivity.apiInterface.getLocationList(query);

        call.enqueue(new Callback<LocationListModel>() {
            @Override
            public void onResponse(Call<LocationListModel> call, Response<LocationListModel> response) {

                suggestedLD.postValue(response.body().getSuggestedLocationModelArrayList());

            }

            @Override
            public void onFailure(Call<LocationListModel> call, Throwable t) {
                Log.d("OnFailure", Objects.requireNonNull(t.getMessage()));
            }
        });
        return suggestedLD;
    }
}

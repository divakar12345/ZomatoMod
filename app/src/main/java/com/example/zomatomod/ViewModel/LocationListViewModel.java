package com.example.zomatomod.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.zomatomod.model.SuggestedLocationModel;
import com.example.zomatomod.repository.LocationListRepository;

import java.util.ArrayList;

public class LocationListViewModel extends AndroidViewModel {
    private Application application;
    private LocationListRepository locationListRepository;
    private MutableLiveData<ArrayList<SuggestedLocationModel>> suggestedLD;
    private String query;

    public LocationListViewModel(@NonNull Application application, String query) {
        super(application);
        this.application = application;
        this.query = query;
        locationListRepository = new LocationListRepository(application);
        suggestedLD = locationListRepository.getSuggestedLocations(query);
    }

    public MutableLiveData<ArrayList<SuggestedLocationModel>> getSuggestedLocations() {
        return suggestedLD;
    }
}

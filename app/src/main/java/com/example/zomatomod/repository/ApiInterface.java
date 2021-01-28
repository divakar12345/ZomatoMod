package com.example.zomatomod.repository;

import com.example.zomatomod.model.DailyMenuArrayModel;
import com.example.zomatomod.model.LocationDetailsModel;
import com.example.zomatomod.model.LocationListModel;
import com.example.zomatomod.model.ReviewBodyModel;
import com.example.zomatomod.model.SearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers({
            "Accept: application/json",
            "user-key: d6fc9718004ec2b54e54cc26fda89e05"
    })
    @GET("search")
    Call<SearchModel> getSearchResult(@Query("entity_id") int entityId, @Query("q") String query);

    @Headers({
            "Accept: application/json",
            "user-key: d6fc9718004ec2b54e54cc26fda89e05"
    })
    @GET("location_details")
    Call<LocationDetailsModel> getLocationDetails(@Query("entity_id") int entityId,
                                                  @Query("entity_type") String entityType);

    @Headers({
            "Accept: application/json",
            "user-key: d6fc9718004ec2b54e54cc26fda89e05"
    })
    @GET("reviews")
    Call<ReviewBodyModel> getReviews(@Query("res_id") int resId);

    @Headers({
            "Accept: application/json",
            "user-key: d6fc9718004ec2b54e54cc26fda89e05"
    })
    @GET("dailymenu")
    Call<DailyMenuArrayModel> getMenuList(@Query("res_id") int resId);

    @Headers({
            "Accept: application/json",
            "user-key: d6fc9718004ec2b54e54cc26fda89e05"
    })
    @GET("cities")
    Call<LocationListModel> getLocationList(@Query("q") String query);


}

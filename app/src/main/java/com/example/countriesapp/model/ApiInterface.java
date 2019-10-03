package com.example.countriesapp.model;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiInterface {
    @GET(ApiInventory.COUNTRY_DATA)
    Single<List<CountryModel>> getCounrtris();

    @GET
    Single<Object> getObject(@Url String stringUrl);
}

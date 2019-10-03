package com.example.countriesapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {
    @GET(ApiInventory.COUNTRY_DATA)
    Single<List<CountryModel>> getCounrtris();

    @GET
    Single<Object> getObject(@Url String stringUrl);
}

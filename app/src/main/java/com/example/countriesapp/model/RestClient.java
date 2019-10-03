package com.example.countriesapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    public static final String BASE_URL = "https://raw.githubusercontent.com/";
    private static RestClient instance;

    private RestClient() {

    }

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;
    }

    private ApiInterface getApiInterface = new Retrofit.Builder().
            baseUrl(BASE_URL).
            addConverterFactory(GsonConverterFactory.create()).
            addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
            build().
            create(ApiInterface.class);

    public Single<List<CountryModel>>getCountries(){
        return getApiInterface.getCounrtris();
    }
}




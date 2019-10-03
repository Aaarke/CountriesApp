package com.example.countriesapp.model;

import com.example.countriesapp.di.DaggerApiComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class RestClient {
    public static final String BASE_URL = "https://raw.githubusercontent.com/";
    private static RestClient instance;

    private RestClient() {
        DaggerApiComponent.create().inject(this);
    }

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;
    }

    @Inject
     ApiInterface getApiInterface;

    public Single<List<CountryModel>> getCountries() {
        return getApiInterface.getCounrtris();
    }
}




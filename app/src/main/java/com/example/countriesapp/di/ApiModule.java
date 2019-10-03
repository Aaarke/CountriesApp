package com.example.countriesapp.di;

import com.example.countriesapp.model.ApiInterface;
import com.example.countriesapp.model.RestClient;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.countriesapp.model.RestClient.BASE_URL;

@Module
public class ApiModule {
    @Provides
    public ApiInterface provideCountryAPI() {
        return new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                build().
                create(ApiInterface.class);
    }

    @Provides
    public RestClient getInstance() {
        return RestClient.getInstance();
    }

}

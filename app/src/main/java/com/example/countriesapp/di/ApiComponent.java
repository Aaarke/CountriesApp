package com.example.countriesapp.di;

import com.example.countriesapp.model.ApiInterface;
import com.example.countriesapp.model.RestClient;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(RestClient restClient);
}

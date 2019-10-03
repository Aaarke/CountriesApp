package com.example.countriesapp.di;

import com.example.countriesapp.model.RestClient;
import com.example.countriesapp.viewmodel.ListViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(RestClient restClient);
    void inject(ListViewModel listViewModel);
}

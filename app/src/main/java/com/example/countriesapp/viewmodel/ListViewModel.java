package com.example.countriesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countriesapp.di.DaggerApiComponent;
import com.example.countriesapp.model.CountryModel;
import com.example.countriesapp.model.RestClient;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {
    public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    @Inject
    public RestClient restClient ;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void refresh() {
        fetchCountries();
    }
    public ListViewModel (){
        super();
        DaggerApiComponent.create().inject(this);
    }

    private void fetchCountries() {
        loading.setValue(true);
        compositeDisposable.add(
                restClient.getCountries()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {
                            @Override
                            public void onSuccess(List<CountryModel> countryModels) {
                                countries.setValue(countryModels);
                                countryLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                countryLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })


        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}

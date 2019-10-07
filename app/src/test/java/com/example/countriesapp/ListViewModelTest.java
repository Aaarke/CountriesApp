package com.example.countriesapp;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.countriesapp.model.CountryModel;
import com.example.countriesapp.model.RestClient;
import com.example.countriesapp.viewmodel.ListViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class ListViewModelTest {
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    @Mock
    RestClient restClient;

    @InjectMocks
    ListViewModel listViewModel=new ListViewModel();

    private Single<List<CountryModel>>testSingle;
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCountriesSuccess(){
        CountryModel countryModel=new CountryModel("India","Delhi","");
        ArrayList<CountryModel>countryModelArrayList=new ArrayList<>();
        countryModelArrayList.add(countryModel);
        testSingle=Single.just(countryModelArrayList);
        Mockito.when(restClient.getCountries()).thenReturn(testSingle);
        listViewModel.refresh();
        Assert.assertEquals(1,listViewModel.countries.getValue().size());
        Assert.assertEquals(false,listViewModel.countryLoadError.getValue());
        Assert.assertEquals(false,listViewModel.loading.getValue());
    }


    @Before
    public void setupRxSchedulers() {
        Scheduler imediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run, true);
            }
        };

        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> imediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> imediate);
    }
}

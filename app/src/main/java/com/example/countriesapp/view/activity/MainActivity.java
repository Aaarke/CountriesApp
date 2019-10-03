package com.example.countriesapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.countriesapp.R;
import com.example.countriesapp.model.CountryModel;
import com.example.countriesapp.view.adapter.CountryAdapter;
import com.example.countriesapp.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rvCountries)
    RecyclerView rvCountries;
    @BindView(R.id.tvError)
    TextView tvError;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private ListViewModel viewModel;
    private CountryAdapter countryAdapter=new CountryAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel= ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.refresh();
        setAdapter();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setAdapter(){
        rvCountries.setLayoutManager(new LinearLayoutManager(this));
        rvCountries.setAdapter(countryAdapter);
        observeViewModel();
    }

    private void observeViewModel(){
        viewModel.countries.observe(this, countryModels -> {
            if(countryModels!=null){
                rvCountries.setVisibility(View.VISIBLE);
                countryAdapter.updateAdapter(countryModels);
            }
        });

        viewModel.countryLoadError.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                if(isError!=null){
                    tvError.setVisibility(isError?View.VISIBLE:View.GONE);
                }
            }
        });

        viewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading!=null){
                    progressBar.setVisibility(isLoading?View.VISIBLE:View.GONE);
                    if(isLoading){
                        tvError.setVisibility(View.GONE);
                        rvCountries.setVisibility(View.GONE);

                    }
                }
            }
        });
    }
}

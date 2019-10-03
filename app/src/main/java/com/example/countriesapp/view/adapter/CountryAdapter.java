package com.example.countriesapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countriesapp.R;
import com.example.countriesapp.model.CountryModel;
import com.example.countriesapp.view.Utils.ImageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private List<CountryModel> countries;

    public CountryAdapter(List<CountryModel> countries) {
        this.countries = countries;
    }

    public void updateAdapter(List<CountryModel> countries) {
       this. countries.clear();
        this. countries.addAll(countries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind(countries.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivFlag)
        ImageView ivFlag;
        @BindView(R.id.tvCountry)
        TextView tvCountry;
        @BindView(R.id.tvCapital)
        TextView tvCapital;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        void bind(CountryModel countryModel) {
            tvCountry.setText(countryModel.getCountryName());
            tvCapital.setText(countryModel.getCountryCapital());
            ImageUtils.loadImage(ivFlag,countryModel.getFlag(),ImageUtils.getProgressDrawable(ivFlag.getContext()));

        }
    }
}

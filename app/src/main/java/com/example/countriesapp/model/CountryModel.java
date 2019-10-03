package com.example.countriesapp.model;

import com.google.gson.annotations.SerializedName;

public class CountryModel {
    @SerializedName("name")
    String countryName;
    @SerializedName("capital")
    String countryCapital;
    @SerializedName("flagPNG")
    String flag;

    public CountryModel(String countryName, String countryCapital, String flag) {
        this.countryName = countryName;
        this.countryCapital = countryCapital;
        this.flag = flag;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public String getFlag() {
        return flag;
    }
}

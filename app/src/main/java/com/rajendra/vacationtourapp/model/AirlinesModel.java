package com.rajendra.vacationtourapp.model;

public class AirlinesModel {
    private String imgUrl;
    private Integer Ahmedabad, Amsterdam, Delhi, Kampala, London, Nairobi;

    public AirlinesModel() {
    }

    public AirlinesModel(String imgUrl, Integer ahmedabad, Integer amsterdam, Integer delhi, Integer kampala, Integer london, Integer nairobi) {
        this.imgUrl = imgUrl;
        Ahmedabad = ahmedabad;
        Amsterdam = amsterdam;
        Delhi = delhi;
        Kampala = kampala;
        London = london;
        Nairobi = nairobi;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getAhmedabad() {
        return Ahmedabad;
    }

    public void setAhmedabad(Integer ahmedabad) {
        Ahmedabad = ahmedabad;
    }

    public Integer getAmsterdam() {
        return Amsterdam;
    }

    public void setAmsterdam(Integer amsterdam) {
        Amsterdam = amsterdam;
    }

    public Integer getDelhi() {
        return Delhi;
    }

    public void setDelhi(Integer delhi) {
        Delhi = delhi;
    }

    public Integer getKampala() {
        return Kampala;
    }

    public void setKampala(Integer kampala) {
        Kampala = kampala;
    }

    public Integer getLondon() {
        return London;
    }

    public void setLondon(Integer london) {
        London = london;
    }

    public Integer getNairobi() {
        return Nairobi;
    }

    public void setNairobi(Integer nairobi) {
        Nairobi = nairobi;
    }
}

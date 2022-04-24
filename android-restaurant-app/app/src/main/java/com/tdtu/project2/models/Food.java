package com.tdtu.project2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Food {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("foodName")
    @Expose
    private String foodName;

    @SerializedName("idType")
    @Expose
    private Long idType;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("priceSmall")
    @Expose
    private double priceSmall;

    @SerializedName("priceMedium")
    @Expose
    private double priceMedium;

    @SerializedName("priceLarge")
    @Expose
    private double priceLarge;

    @SerializedName("unitMeasure")
    @Expose
    private String unitMeasure;

    public Long getId() {
        return id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Long getIdType() {
        return idType;
    }

    public void setIdType(Long idType) {
        this.idType = idType;
    }

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public double getPriceSmall() {
        return priceSmall;
    }

    public void setPriceSmall(double priceSmall) {
        this.priceSmall = priceSmall;
    }

    public double getPriceMedium() {
        return priceMedium;
    }

    public void setPriceMedium(double priceMedium) {
        this.priceMedium = priceMedium;
    }

    public double getPriceLarge() {
        return priceLarge;
    }

    public void setPriceLarge(double priceLarge) {
        this.priceLarge = priceLarge;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

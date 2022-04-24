package com.tdtu.project2.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "food")
public class Food {

    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_name", length = 255)
    private String foodName;

    @Column(name = "id_type", length = 11)
    private Long idType;
    
    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "price_small")
    private double priceSmall;
    
    @Column(name = "price_medium")
    private double priceMedium;
    
    @Column(name = "price_large")
    private double priceLarge;

    @Column(name = "unit_measure", length = 255)
    private String unitMeasure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

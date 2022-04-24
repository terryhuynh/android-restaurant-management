package com.tdtu.project2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodType {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("typeName")
    @Expose
    private String name;

    @SerializedName("color")
    @Expose
    private String color;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

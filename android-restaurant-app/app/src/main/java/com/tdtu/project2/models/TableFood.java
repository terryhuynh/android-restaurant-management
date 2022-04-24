package com.tdtu.project2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TableFood {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("tableName")
    @Expose
    private String tableName;

    @SerializedName("status")
    @Expose
    private Long status;

    private boolean isSelected;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

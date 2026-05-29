package com.miaoxing.business.entity.view;

import com.miaoxing.business.entity.Adoption;

public class AdoptionView extends Adoption {

    private String catStatus;

    public String getCatStatus() {
        return catStatus;
    }

    public void setCatStatus(String catStatus) {
        this.catStatus = catStatus;
    }
}

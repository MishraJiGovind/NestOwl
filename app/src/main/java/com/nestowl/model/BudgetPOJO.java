package com.nestowl.model;

import java.io.Serializable;

public class BudgetPOJO implements Serializable {
    public String name;
    public Boolean isSelected;

    public BudgetPOJO(String name, Boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }
}

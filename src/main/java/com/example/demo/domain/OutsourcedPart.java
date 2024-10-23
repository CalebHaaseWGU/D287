package com.example.demo.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity class for OutsourcedPart
 */
@Entity
@DiscriminatorValue("2")
public class OutsourcedPart extends Part {
    String companyName;

    public OutsourcedPart() {
        // Set default values for min and max if needed
        this.min = 1; // Default minimum inventory
        this.max = 10; // Default maximum inventory
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public void setInv(int inv) {
        if (inv < min) {
            throw new IllegalArgumentException("Inventory cannot be less than the minimum value: " + min);
        } else if (inv > max) {
            throw new IllegalArgumentException("Inventory cannot be greater than the maximum value: " + max);
        } else {
            this.inv = inv;
        }
    }
}



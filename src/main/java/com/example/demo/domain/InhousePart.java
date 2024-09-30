package com.example.demo.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 *
 *
 *
 */
@Entity
@DiscriminatorValue("1")
public class InhousePart extends Part {
    int partId;

    // Temporary field to capture inventory input from the form
    @Transient
            int tempInv;

    public InhousePart() {
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getTempInv() {
        return tempInv;
    }

    public void setTempInv(int tempInv) {
        this.tempInv = tempInv;
    }
}


package com.example.demo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartTest {
    Part part;

    @BeforeEach
    void setUp() {
        part = new InhousePart(); // Assuming InhousePart extends Part
        part.setMin(5);
        part.setMax(10);
    }

    @Test
    void testInventoryNotBelowMinimum() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            part.setInv(4); // Setting inventory below the minimum
        });
        assertEquals("Inventory cannot be less than minimum value", exception.getMessage());
    }

    @Test
    void testInventoryNotAboveMaximum() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            part.setInv(11); // Setting inventory above the maximum
        });
        assertEquals("Inventory cannot be greater than maximum value", exception.getMessage());
    }

    @Test
    void testValidInventorySetting() {
        // Act
        part.setInv(7); // Setting inventory within the valid range

        // Assert
        assertEquals(7, part.getInv());
    }
}


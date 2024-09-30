### **C. Customize the HTML User Interface**

**Prompt**: Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.

- **Files Modified**:

    1. **mainscreen.html**
        - **Changes**:
            - Added the shop name to the header of the main screen. **Line 15**.
            - Displayed a list of available products with their names. **Line 46**.
            - Added a section that lists the parts associated with each product. **Line 79**.



### **D. Modify the Application to Handle Form Inputs for Creating or Updating an "InhousePart"**

**Prompt**: Modify the application to handle form inputs for creating or updating an "InhousePart".

- **Files Modified**:

    1. **InhousePartForm.html**
        - **Changes**:
            - Added fields for capturing `tempInv` (temporary inventory), `min` (minimum inventory), and `max` (maximum inventory) to validate user input. **Line 39**.
            - Updated error handling to display appropriate messages if inventory constraints are violated.
            - Applied Bootstrap for enhanced form styling and error message presentation.

    2. **AddInhousePartController.java**
        - **Changes**:
            - Introduced validation logic to check the `tempInv` field for inventory. This temporary inventory value is validated against the `min` and `max` inventory values. **Line 46**.
            - If validation fails, an error message is added to the model and displayed on the form.
            - Once validated, the `tempInv` value is set as the actual inventory (`inv`) if it falls within the specified `min` and `max` bounds.




### **E. Add Sample Inventory**

**Prompt**: "Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database."

- **File Name**: `SampleInventory.java`
- **Line Numbers**: Starting from line 33
- **Changes**:
    - Implemented the `addSampleInventory()` method to populate the application with five parts and five products.
    - Ensured that the inventory of parts and products is within the specified minimum and maximum values to avoid validation errors.
    - Added a check in the `run()` method to ensure the sample inventory is only added if the database is empty, preventing overwriting of existing data.
    - **Five parts added**:
        - Processor (InhousePart)
        - Graphics Card (OutsourcedPart)
        - RAM (InhousePart)
        - Hard Drive (OutsourcedPart)
        - Power Supply (InhousePart)
    - **Five products added**:
        - Gaming PC
        - Workstation PC
        - Laptop
        - Server
        - NAS Storage




### **F. Add Minimum and Maximum Inventory to Product Class**

**Prompt**: Implement minimum and maximum inventory levels for products and validate that the product inventory falls within these limits.

- **Files Modified**:

    1. **Product.java**
        - **Line Numbers**: Starting from line 36
        - **Changes**:
            - Added fields `min` and `max` to store the minimum and maximum inventory levels for products.
            - Updated constructors to include the new `min` and `max` fields.
            - Added getter and setter methods for `min` and `max` to ensure correct functionality.

    2. **PriceProductValidator.java**
        - **Line Numbers**: No major changes in logic.
        - **Changes**:
            - Ensures that the product's price is greater than or equal to the sum of its parts' prices, as before.





### Part G: Modify Parts to Track Maximum and Minimum Inventory

#### Changes made to the codebase:

##### 1. **Add additional fields for maximum and minimum inventory in the `Part` entity**

- **File:** `Part.java`
- **Changes:**
    - **Added fields:** `int min` and `int max`
    - **Added validation:** Enforced the condition that inventory (`inv`) should always be between `min` and `max`.
    - **File location:** `src/main/java/com/example/demo/domain/Part.java`
    - **Line number:** The changes start at **line 31**.

##### 2. **Modify the sample inventory to include the maximum and minimum fields**

- **File:** `SampleInventory.java`
- **Changes:**
    - Each part in the sample inventory now includes `min` and `max` values for inventory. This ensures that the sample parts are initialized with the correct minimum and maximum inventory constraints.
    - **File location:** `src/main/java/com/example/demo/SampleInventory.java`
    - **Line number:** Starting from **line 38**.

##### 3. **Add new text inputs for maximum and minimum values in `InhousePartForm`**

- **File:** `InhousePartForm.html`
- **Changes:**
    - Added new text fields for users to input the minimum (`min`) and maximum (`max`) inventory values in the form.
    - **File location:** `src/main/resources/templates/InhousePartForm.html`
    - **Line number:** Starting from **line 46**.

### Part 4: Modify the `application.properties`
**File:** `application.properties`  
**Location:** `src/main/resources`  
**Changes:**
- The persistent storage location for the database was changed from `spring.datasource.url=jdbc:h2:file:~/spring-boot-h2-db102` to `spring.datasource.url=jdbc:h2:mem:testdb`.  
  This change ensures that the database is in-memory for testing, which resets data on each restart, but can be useful for development environments.

##### 5. **Enforce inventory is between minimum and maximum values**

- The enforcement of inventory values between the `min` and `max` fields is handled in the `Part` class. If a user attempts to set an inventory value (`inv`) outside of this range, an `IllegalArgumentException` is thrown.




### **H. Add validation for between or at the maximum and minimum fields**

**1. Part.java**
- **File**: `Part.java`
- **Line Numbers**: Starting from line 31, modifications were made to add fields for `min` and `max` values and implement validation for inventory between these values.
- **Changes**:
    - Added `min` and `max` fields with appropriate annotations for validation.
    - Modified the `setInv()` method to include checks ensuring that the inventory is between the minimum and maximum values.
    - An error message is displayed if the inventory exceeds the specified range.

**2. Product.java**
- **File**: `Product.java`
- **Line Numbers**: Starting from line 36, the `min` and `max` fields were added.
- **Changes**:
    - Added fields `min` and `max` with validation annotations for positive values.
    - Included getters and setters for the new fields.

**3. InhousePartForm.html**
- **File**: `InhousePartForm.html`
- **Line Numbers**: Line 46, New fields were added for `min` and `max` inventory values.
- **Changes**:
    - Added input fields for `min` and `max` inventory, with validation checks for user input.
    - Displayed error messages if the inventory is outside the allowed range.

**4. OutsourcedPartForm.html**
- **File**: `OutsourcedPartForm.html`
- **Line Numbers**: Line 25, new fields were added for `min` and `max` inventory values.
- **Changes**:
    - Added input fields for `min` and `max` inventory, along with validation checks.
    - Displayed error messages if the entered inventory values violate the `min` or `max` range.

**5. application.properties**
- **File**: `application.properties`
- **Line Numbers**: Adjusted the database configurations.
- **Changes**:
    - Updated the database to use an in-memory H2 database for testing purposes.
    - Renamed the persistent storage file to match the new configurations for handling inventory values.

### **I. Price Validation for Products**

**1. PriceProductValidator.java**
- **File**: `PriceProductValidator.java`
- **Line Numbers**: Starting from line 41, code was added to fetch the sum of all the parts' prices associated with the product.
- **Changes**:
    - Fetched the sum of all the parts' prices related to the product.
    - Added logic to compare the total price of the product against the sum of the parts' prices.
    - If the product's price is less than the sum of the parts, an error message is returned to enforce price integrity.



### **J. Remove Unused Validators**

- **Summary**: After reviewing the project code, no unused validator classes were found. All existing validators are currently in use and essential to the application's functionality.
- **Action Taken**: No class files were removed, as there were no unused validators to clean up.

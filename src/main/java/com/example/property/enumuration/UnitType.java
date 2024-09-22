
package com.example.property.enumuration;

public enum UnitType {

    VILLA("Villa"),
    OFFICE("Office"),
    APARTMENT("Apartment"),
    COTTAGE("Cottage"),
    ;


    private String displayName;

    UnitType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


    public static UnitType fromDisplayName(String displayName) {
        for (UnitType status : values()) {
            if (status.displayName.equals(displayName)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant with display name: " + displayName);
    }

}

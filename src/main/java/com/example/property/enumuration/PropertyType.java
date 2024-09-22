package com.example.property.enumuration;

public enum PropertyType {

    COMMERCIAL("Commercial"),
    RESIDENTIAL("Residential");

    private String displayName;

    PropertyType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static PropertyType fromDisplayName(String displayName) {
        for (PropertyType status : values()) {
            if (status.displayName.equals(displayName)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant with display name: " + displayName);
    }

}

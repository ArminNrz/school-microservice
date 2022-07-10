package com.school.baseLayer.enumartion;

public enum KeyCloakClientId {
    ACADEMIC("academic-service"),
    FINANCE("finance-service");

    private String label;

    private KeyCloakClientId(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}

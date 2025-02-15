package com.rideshare.compliance.dto;

public class ComplianceResponse {
    private boolean compliant;
    private String message;

    // Getters and setters
    public boolean isCompliant() {
        return compliant;
    }
    public void setCompliant(boolean compliant) {
        this.compliant = compliant;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

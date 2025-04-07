package com.example.managersms.models;

public class BlacklistEntry {
    private String phoneNumber;

    public BlacklistEntry(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() { return phoneNumber; }
}
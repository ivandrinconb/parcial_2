package edu.rentcar.model;

public interface RentalMode {
    String getName();
    double calculateCost(double baseRate, int days);
    String getCoverage();
}
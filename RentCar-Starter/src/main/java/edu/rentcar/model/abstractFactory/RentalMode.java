package edu.rentcar.model.abstractFactory;

public interface RentalMode {
    String getName();
    double calculateCost(double baseRate, int days);
    String getCoverage();
}
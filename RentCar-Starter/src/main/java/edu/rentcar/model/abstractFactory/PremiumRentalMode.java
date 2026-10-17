package edu.rentcar.model.abstractFactory;

public class PremiumRentalMode implements RentalMode {
    @Override
    public String getName() { return "Premium"; }
    @Override
    public double calculateCost(double baseRate, int days) { return (baseRate * days) * 1.5; }
    @Override
    public String getCoverage() { return "Cobertura completa + asistencia 24/7"; }
}

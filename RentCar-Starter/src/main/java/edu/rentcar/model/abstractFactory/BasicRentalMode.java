package edu.rentcar.model.abstractFactory;

public class BasicRentalMode implements RentalMode {
    @Override
    public String getName() { return "Básico"; }
    @Override
    public double calculateCost(double baseRate, int days) { return baseRate * days; }
    @Override
    public String getCoverage() { return "Cobertura mínima"; }

}

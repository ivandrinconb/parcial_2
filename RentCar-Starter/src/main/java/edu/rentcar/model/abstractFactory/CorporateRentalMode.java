package edu.rentcar.model.abstractFactory;

public class CorporateRentalMode implements RentalMode {
    @Override
    public String getName() { return "Corporativo"; }
    @Override
    public double calculateCost(double baseRate, int days) { return (baseRate * days) * 0.9; }
    @Override
    public String getCoverage() { return "Cobertura empresarial + descuentos"; }
}

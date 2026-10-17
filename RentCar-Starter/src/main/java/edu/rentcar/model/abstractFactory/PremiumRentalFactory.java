package edu.rentcar.model.abstractFactory;

public class PremiumRentalFactory implements RentalModeFactory {
    @Override
    public RentalMode createRentalMode() {
        return new PremiumRentalMode();
    }
}

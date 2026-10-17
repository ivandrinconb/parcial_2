package edu.rentcar.model.abstractFactory;

public class CorporateRentalFactory implements RentalModeFactory {
    @Override
    public RentalMode createRentalMode() {
        return new CorporateRentalMode();
    }
}

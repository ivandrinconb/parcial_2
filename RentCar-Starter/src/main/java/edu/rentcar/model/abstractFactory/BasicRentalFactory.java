package edu.rentcar.model.abstractFactory;

public class BasicRentalFactory implements RentalModeFactory {
    @Override
    public RentalMode createRentalMode() { return new BasicRentalMode(); }

}

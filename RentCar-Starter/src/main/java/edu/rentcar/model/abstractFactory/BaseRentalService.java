package edu.rentcar.model.abstractFactory;

import edu.rentcar.model.decorator.RentalService;

public class BaseRentalService implements RentalService {
    @Override
    public String getDescription() {
        return "Servicio básico de alquiler";
    }

    @Override
    public double getCost() {
        return 0.0; // costo inicial sin extras
    }
}

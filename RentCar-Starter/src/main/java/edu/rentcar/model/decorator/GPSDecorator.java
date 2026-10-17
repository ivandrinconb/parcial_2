package edu.rentcar.model.decorator;

public class GPSDecorator extends ServiceDecorator {
    public GPSDecorator(RentalService service) {
        super(service);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", GPS incluido";
    }

    @Override
    public double getCost() {
        return super.getCost() + 15.0;
    }
}

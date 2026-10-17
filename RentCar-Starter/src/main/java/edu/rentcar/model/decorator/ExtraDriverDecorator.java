package edu.rentcar.model.decorator;

public class ExtraDriverDecorator extends ServiceDecorator {
    public ExtraDriverDecorator(RentalService service) {
        super(service);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Conductor adicional";
    }

    @Override
    public double getCost() {
        return super.getCost() + 20.0;
    }
}

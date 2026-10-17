package edu.rentcar.model.decorator;

public class ChildSeatDecorator extends ServiceDecorator {
    public ChildSeatDecorator(RentalService service) {
        super(service);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Silla infantil";
    }

    @Override
    public double getCost() {
        return super.getCost() + 10.0;
    }
}

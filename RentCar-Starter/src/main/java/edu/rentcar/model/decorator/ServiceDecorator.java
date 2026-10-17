package edu.rentcar.model.decorator;

public class ServiceDecorator implements RentalService {
    protected RentalService decoratedService;

    public ServiceDecorator(RentalService service) {
        this.decoratedService = service;
    }

    @Override
    public String getDescription() {
        return decoratedService.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedService.getCost();
    }
}

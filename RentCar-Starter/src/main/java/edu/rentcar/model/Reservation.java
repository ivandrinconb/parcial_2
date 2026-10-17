package edu.rentcar.model;

import edu.rentcar.model.abstractFactory.RentalMode;
import edu.rentcar.model.decorator.RentalService;

import java.time.LocalDate;
import java.util.List;

public class Reservation {
    private Customer customer;
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private RentalMode rentalMode;
    private List<RentalService> services;

    public Reservation(Customer customer, Vehicle vehicle, LocalDate startDate, LocalDate endDate,
                       RentalMode rentalMode, List<RentalService> services) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentalMode = rentalMode;
        this.services = services;
    }


    public Customer getCustomer() { return customer; }
    public Vehicle getVehicle() { return vehicle; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public RentalMode getRentalMode() { return rentalMode; }
    public List<RentalService> getServices() { return services; }


    public String getDescription() {
        StringBuilder desc = new StringBuilder("Reservación de " + customer.getFullName() +
                " con vehículo " + vehicle.getBrand() + " " + vehicle.getModel() +
                " desde " + startDate + " hasta " + endDate +
                " modalidad: " + rentalMode.getName());

        if (services != null && !services.isEmpty()) {
            desc.append(" | Servicios: ");
            for (RentalService service : services) {
                desc.append(service.getDescription()).append(", ");
            }
        }
        return desc.toString();
    }


    public double calculateTotalCost(double baseRate) {
        int days = (int) (endDate.toEpochDay() - startDate.toEpochDay());
        double cost = rentalMode.calculateCost(baseRate, days);

        if (services != null) {
            for (RentalService service : services) {
                cost += service.getCost();
            }
        }
        return cost;
    }
}
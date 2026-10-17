package edu.rentcar.model;

import edu.rentcar.model.abstractFactory.RentalMode;
import edu.rentcar.model.decorator.RentalService;

import java.time.LocalDate;
import java.util.List;

public class ReservationBuilder {
    private Customer customer;
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private RentalMode rentalMode;
    private List<RentalService> services;

    // Métodos de configuración (builder pattern)
    public ReservationBuilder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public ReservationBuilder withVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public ReservationBuilder withDates(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        return this;
    }

    public ReservationBuilder withRentalMode(RentalMode rentalMode) {
        this.rentalMode = rentalMode;
        return this;
    }

    public ReservationBuilder withServices(List<RentalService> services) {
        this.services = services;
        return this;
    }

    // Construye un objeto Reservation completo
    public Reservation build() {
        if (customer == null || vehicle == null || startDate == null || endDate == null || rentalMode == null) {
            throw new IllegalArgumentException("La reservación tiene información incompleta.");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        return new Reservation(customer, vehicle, startDate, endDate, rentalMode, services);
    }

    // Genera descripción de la reserva
    public String buildDescription() {
        if (customer == null || vehicle == null || startDate == null || endDate == null || rentalMode == null) {
            throw new IllegalArgumentException("La reservación tiene información incompleta.");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }

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

    // Calcula el costo total
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
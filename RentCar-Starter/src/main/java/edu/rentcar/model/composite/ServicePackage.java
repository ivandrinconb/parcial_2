package edu.rentcar.model.composite;

import edu.rentcar.model.decorator.RentalService;

import java.util.ArrayList;
import java.util.List;

public class ServicePackage implements RentalService {
    private String nombre;
    private double costoBase;
    private List<RentalService> servicios = new ArrayList<>();

    public ServicePackage(String nombre, double costoBase) {
        this.nombre = nombre;
        this.costoBase = costoBase;
    }

    public void addService(RentalService servicio) {
        servicios.add(servicio);
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder(nombre + " (costo base: $" + costoBase + ")");
        for (RentalService s : servicios) {
            sb.append("\n - ").append(s.getDescription());
        }
        return sb.toString();
    }

    @Override
    public double getCost() {
        double total = costoBase;
        for (RentalService s : servicios) {
            total += s.getCost();
        }
        return total;
    }
}
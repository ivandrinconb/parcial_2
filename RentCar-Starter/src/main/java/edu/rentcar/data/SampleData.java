package edu.rentcar.data;

import edu.rentcar.model.Customer;
import edu.rentcar.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public final class SampleData {


    private static final List<Customer> customers = new ArrayList<>(List.of(
            new Customer("CUS-1001", "Sofia Martinez", "sofia.martinez@example.com", true),
            new Customer("CUS-1002", "Daniel Rojas", "daniel.rojas@example.com", true),
            new Customer("CUS-1003", "Valentina Torres", "valentina.torres@example.com", false)
    ));


    private static final List<Vehicle> vehicles = new ArrayList<>();

    private SampleData() {

    }

    // Métodos de acceso
    public static List<Customer> customers() {
        return customers;
    }

    public static List<Vehicle> vehicles() {
        return vehicles;
    }


    public static void addCustomer(Customer c) {
        customers.add(c);
    }

    public static void addVehicle(Vehicle v) {
        vehicles.add(v);
    }
}
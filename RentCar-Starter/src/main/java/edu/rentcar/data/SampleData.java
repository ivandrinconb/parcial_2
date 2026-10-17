package edu.rentcar.data;

import edu.rentcar.model.Customer;
import edu.rentcar.model.Vehicle;
import edu.rentcar.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public final class SampleData {

    // Clientes quemados (datos iniciales)
    private static final List<Customer> customers = new ArrayList<>(List.of(
            new Customer("CUS-1001", "Sofia Martinez", "sofia.martinez@example.com", true),
            new Customer("CUS-1002", "Daniel Rojas", "daniel.rojas@example.com", true),
            new Customer("CUS-1003", "Valentina Torres", "valentina.torres@example.com", false)
    ));

    // Vehículos: lista vacía, se llenará solo con lo que registre el cliente
    private static final List<Vehicle> vehicles = new ArrayList<>();

    // Reservas: lista vacía, se llenará al confirmar reservas
    private static final List<Reservation> reservations = new ArrayList<>();

    private SampleData() {
        // Evita instanciación
    }

    // Métodos de acceso
    public static List<Customer> customers() {
        return customers;
    }

    public static List<Vehicle> vehicles() {
        return vehicles;
    }

    public static List<Reservation> reservations() {
        return reservations;
    }

    // Métodos para agregar nuevos
    public static void addCustomer(Customer c) {
        customers.add(c);
    }

    public static void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public static void addReservation(Reservation r) {
        reservations.add(r);
    }
}
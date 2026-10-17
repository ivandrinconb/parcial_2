package edu.rentcar.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import edu.rentcar.data.SampleData;
import edu.rentcar.model.Customer;
import edu.rentcar.model.ReservationBuilder;
import edu.rentcar.model.Vehicle;
import edu.rentcar.model.abstractFactory.*;
import edu.rentcar.model.composite.ServicePackage;
import edu.rentcar.model.decorator.ChildSeatDecorator;
import edu.rentcar.model.decorator.ExtraDriverDecorator;
import edu.rentcar.model.decorator.GPSDecorator;
import edu.rentcar.model.decorator.RentalService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

public class ReservationController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML private Button btnCancelar;
    @FXML private Button btnConfirmarReserva;
    @FXML private Button btnDisponibilidad;

    @FXML private ComboBox<String> cbCliente;
    @FXML private ComboBox<String> cbMetodoPago;
    @FXML private ComboBox<String> cbModalidad;
    @FXML private ComboBox<String> cbPaquetesServicio;
    @FXML private ComboBox<String> cbVehiculo;

    @FXML private CheckBox checkConductorAdicional;
    @FXML private CheckBox checkGPS;
    @FXML private CheckBox checkSillaInfantil;

    @FXML private DatePicker dateFechaFin;
    @FXML private DatePicker dateFechaInicio;

    @FXML private TextArea txtResumen;

    @FXML
    void OnActionCancelar(ActionEvent event) {
        cbCliente.setValue(null);
        cbVehiculo.setValue(null);
        cbModalidad.setValue(null);
        cbPaquetesServicio.setValue(null);
        cbMetodoPago.setValue(null);
        dateFechaInicio.setValue(null);
        dateFechaFin.setValue(null);
        checkGPS.setSelected(false);
        checkSillaInfantil.setSelected(false);
        checkConductorAdicional.setSelected(false);
        txtResumen.clear();
    }

    @FXML
    void OnActionConfirmarReserva(ActionEvent event) {
        try {
            RentalModeFactory factory;
            switch (cbModalidad.getValue()) {
                case "Básico": factory = new BasicRentalFactory(); break;
                case "Premium": factory = new PremiumRentalFactory(); break;
                case "Corporativo": factory = new CorporateRentalFactory(); break;
                default: throw new IllegalArgumentException("Seleccione una modalidad válida.");
            }
            RentalMode mode = factory.createRentalMode();

            RentalService service = new BaseRentalService();
            if (checkGPS.isSelected()) service = new GPSDecorator(service);
            if (checkSillaInfantil.isSelected()) service = new ChildSeatDecorator(service);
            if (checkConductorAdicional.isSelected()) service = new ExtraDriverDecorator(service);

            if (cbPaquetesServicio.getValue() != null) {
                switch (cbPaquetesServicio.getValue()) {
                    case "Paquete Familiar":
                        ServicePackage familiar = new ServicePackage("Paquete Familiar", 30000);
                        familiar.addService(new ChildSeatDecorator(new BaseRentalService()));
                        service = familiar;
                        break;
                    case "Paquete Ejecutivo":
                        ServicePackage ejecutivo = new ServicePackage("Paquete Ejecutivo", 50000);
                        ejecutivo.addService(new GPSDecorator(new BaseRentalService()));
                        ejecutivo.addService(new ExtraDriverDecorator(new BaseRentalService()));
                        service = ejecutivo;
                        break;
                    case "Paquete Completo":
                        ServicePackage completo = new ServicePackage("Paquete Completo", 80000);
                        completo.addService(new GPSDecorator(new BaseRentalService()));
                        completo.addService(new ChildSeatDecorator(new BaseRentalService()));
                        completo.addService(new ExtraDriverDecorator(new BaseRentalService()));
                        service = completo;
                        break;
                }
            }

            Customer selectedCustomer = SampleData.customers().stream()
                    .filter(c -> c.getFullName().equals(cbCliente.getValue()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

            Vehicle selectedVehicle = SampleData.vehicles().stream()
                    .filter(v -> (v.getBrand() + " " + v.getModel()).equals(cbVehiculo.getValue()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));

            ReservationBuilder builder = new ReservationBuilder()
                    .withCustomer(selectedCustomer)
                    .withVehicle(selectedVehicle)
                    .withDates(dateFechaInicio.getValue(), dateFechaFin.getValue())
                    .withRentalMode(mode)
                    .withServices(List.of(service));

            String descripcion = builder.buildDescription();
            double costo = builder.calculateTotalCost(50.0); // tarifa base

            txtResumen.setText(descripcion + "\nCosto total: $" + costo +
                    "\nMétodo de pago: " + cbMetodoPago.getValue());

        } catch (Exception e) {
            txtResumen.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    void OnActionDisponiblidad(ActionEvent event) {
        if (dateFechaInicio.getValue() == null || dateFechaFin.getValue() == null) {
            txtResumen.setText("Seleccione fechas para verificar disponibilidad.");
        } else {
            txtResumen.setText("Disponibilidad confirmada para las fechas seleccionadas.");
        }
    }

    @FXML
    void initialize() {
        refreshClientes();
        refreshVehiculos();

        cbModalidad.getItems().addAll("Básico", "Premium", "Corporativo");
        cbPaquetesServicio.getItems().addAll("Paquete Familiar", "Paquete Ejecutivo", "Paquete Completo");
        cbMetodoPago.getItems().addAll("Efectivo", "Tarjeta", "Transferencia");
    }


    public void refreshClientes() {
        cbCliente.getItems().clear();
        cbCliente.getItems().addAll(
                SampleData.customers().stream()
                        .map(Customer::getFullName)
                        .toList()
        );
    }

    public void refreshVehiculos() {
        cbVehiculo.getItems().clear();
        cbVehiculo.getItems().addAll(
                SampleData.vehicles().stream()
                        .map(v -> v.getBrand() + " " + v.getModel())
                        .toList()
        );
    }
}
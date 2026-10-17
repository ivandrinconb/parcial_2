package edu.rentcar.controller;

import edu.rentcar.service.CustomerAuthorizationProxy;
import edu.rentcar.service.PaymentFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ReservationController {

    private final PaymentFacade paymentFacade;

    @FXML private TextField txtReservationId;
    @FXML private TextField txtCustomerId;
    @FXML private TextField txtAmount;
    @FXML private TextField txtToken;

    public ReservationController() {
        // Ahora se instancia limpiamente sin errores de argumentos
        this.paymentFacade = new PaymentFacade(new CustomerAuthorizationProxy());
    }

    @FXML
    public void handleProcessPayment() {
        try {
            String reservationId = txtReservationId.getText();
            String customerId = txtCustomerId.getText();
            double amount = Double.parseDouble(txtAmount.getText());
            String token = txtToken.getText();

            boolean success = paymentFacade.processRentalPayment(
                    reservationId,
                    customerId,
                    amount,
                    token
            );

            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Pago Exitoso",
                        "El pago de la reserva " + reservationId + " fue procesado correctamente.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Pago Rechazado",
                        "No se pudo procesar el pago. Revisa el cliente o el token de la tarjeta.");
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Datos Inválidos",
                    "Por favor ingresa un monto numérico válido.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error de Conexión",
                    "Error al conectar con la API de pagos: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
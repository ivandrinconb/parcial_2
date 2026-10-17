package edu.rentcar.service;

import com.rentcar.paymentclient.PaymentApiClient;
import com.rentcar.paymentclient.PaymentApiRequest;
import com.rentcar.paymentclient.PaymentApiResponse;

import java.io.IOException;

public class PaymentFacade {

    private final CustomerAuthorizationProxy authorizationProxy;
    private final PaymentApiClient apiClient;

    public PaymentFacade(CustomerAuthorizationProxy authorizationProxy) {
        this.authorizationProxy = authorizationProxy;
        this.apiClient = new PaymentApiClient("http://localhost:8080");
    }

    /**
     * Patron Facade + Proxy integrado:
     * 1. Valida la autorizacion del cliente mediante el Proxy (RF-006).
     * 2. Si esta autorizado, procesa el pago usando el cliente de la API externa (RF-004).
     */
    public boolean processRentalPayment(String reservationId, String customerId, double amount, String token) {

        // Paso 1: Verificacion previa con el Proxy (Requisito RF-006)
        boolean isAuthorized = authorizationProxy.authorizeCustomer(customerId);
        if (!isAuthorized) {
            System.out.println("Pago rechazado por el Proxy: Cliente no autorizado.");
            return false;
        }

        // Paso 2: Procesar el pago comunicandose con la API externa (Requisito RF-004)
        try {
            PaymentApiRequest request = new PaymentApiRequest(reservationId, customerId, amount, token);
            PaymentApiResponse response = apiClient.pay(request);

            if (response.isApproved()) {
                System.out.println("¡Transaccion aprobada por la pasarela de pago!");
                return true;
            } else if (response.isDeclined()) {
                System.out.println("La pasarela declino la transaccion.");
                return false;
            } else {
                System.out.println("No se pudo completar el pago en la pasarela.");
                return false;
            }

        } catch (IOException e) {
            System.err.println("Error de comunicacion con la API de pagos: " + e.getMessage());
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("La solicitud de pago fue interrumpida.");
            return false;
        } catch (Exception e) {
            System.err.println("Error inesperado en el proceso de pago: " + e.getMessage());
            return false;
        }
    }
}
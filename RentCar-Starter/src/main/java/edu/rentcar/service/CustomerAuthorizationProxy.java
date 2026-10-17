package edu.rentcar.service;

public class CustomerAuthorizationProxy {

    private final CustomerVerificationService verificationService;

    // Constructor sin parámetros (soluciona la llamada en ReservationController)
    public CustomerAuthorizationProxy() {
        this.verificationService = null;
    }

    // Constructor con parámetro
    public CustomerAuthorizationProxy(CustomerVerificationService verificationService) {
        this.verificationService = verificationService;
    }

    /**
     * Patrón Proxy: Intercepta la solicitud para verificar si el cliente está autorizado.
     */
    public boolean authorizeCustomer(String customerId) {
        if (customerId == null || customerId.isBlank()) {
            return false;
        }

        if (verificationService == null) {
            // Si no hay un servicio inyectado, permite la autorización previa
            return true;
        }

        return verificationService.verifyCustomer(customerId);
    }
}
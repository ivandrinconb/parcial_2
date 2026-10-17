package edu.rentcar.service;

public class CustomerAuthorizationProxy {

    private final CustomerVerificationService verificationService;

    public CustomerAuthorizationProxy(CustomerVerificationService verificationService) {
        this.verificationService = verificationService;
    }

    /**
     * Patrón Proxy: Intercepta la solicitud para verificar si el cliente está autorizado
     * mediante el servicio real provisto, evitando invocar el pago si no cumple.
     */
    public boolean authorizeCustomer(String customerId) {
        if (customerId == null || customerId.isBlank()) {
            return false;
        }

        // Llamada directa al servicio real del profesor
        boolean isAuthorized = verificationService.verifyCustomer(customerId);

        if (!isAuthorized) {
            // Si no está autorizado, se frena aquí (Requisito RF-006)
            return false;
        }

        return true;
    }
}
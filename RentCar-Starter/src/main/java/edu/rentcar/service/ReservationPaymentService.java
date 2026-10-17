package edu.rentcar.service;

public class ReservationPaymentService {

    private final PaymentFacade paymentFacade;

    public ReservationPaymentService() {
        this.paymentFacade = new PaymentFacade(new CustomerAuthorizationProxy());
    }

    /**
     * Procesa el pago del alquiler utilizando la fachada.
     */
    public boolean processPayment(String reservationId, String customerId, double amount, String token) {
        return paymentFacade.processRentalPayment(reservationId, customerId, amount, token);
    }
}
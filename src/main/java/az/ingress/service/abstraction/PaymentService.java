package az.ingress.service.abstraction;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.criteria.PaymentCriteria;
import az.ingress.model.request.PaymentRequest;
import az.ingress.model.response.PageableResponse;
import az.ingress.model.response.PaymentResponse;

public interface PaymentService {

    PaymentResponse getPayment(Long id);

    PaymentResponse createPayment(PaymentRequest paymentRequest);

    void deletePayment(Long id);

    PaymentResponse updatePayment(Long id, PaymentRequest paymentRequest);

    PageableResponse<PaymentResponse> getPayments(PageCriteria pageCriteria, PaymentCriteria paymentCriteria);
}

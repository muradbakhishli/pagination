package az.ingress.controller;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.criteria.PaymentCriteria;
import az.ingress.model.request.PaymentRequest;
import az.ingress.model.response.PageableResponse;
import az.ingress.model.response.PaymentResponse;
import az.ingress.service.abstraction.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("{id}")
    public PaymentResponse getPayment(@PathVariable Long id) {
        return paymentService.getPayment(id);
    }

    @GetMapping
    public PageableResponse getPayments(PageCriteria pageCriteria, PaymentCriteria paymentCriteria) {
        return paymentService.getPayments(pageCriteria, paymentCriteria);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.createPayment(paymentRequest);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public PaymentResponse updatePayment(@PathVariable Long id, @RequestBody PaymentRequest paymentRequest) {
        return paymentService.updatePayment(id, paymentRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
}

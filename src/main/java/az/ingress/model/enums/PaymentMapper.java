package az.ingress.model.enums;

import az.ingress.dao.entity.PaymentEntity;
import az.ingress.model.request.PaymentRequest;
import az.ingress.model.response.PaymentResponse;

import static az.ingress.model.enums.PaymentStatus.CANCELLED;

public enum PaymentMapper {
    PAYMENT_MAPPER;

    public PaymentEntity toEntity(PaymentRequest paymentRequest) {
        return PaymentEntity.builder()
                .fromAccount(paymentRequest.getFromAccount())
                .toAccount(paymentRequest.getToAccount())
                .amount(paymentRequest.getAmount())
                .status(paymentRequest.getStatus())
                .transactionNumber(paymentRequest.getTransactionNumber())
                .build();
    }

    public PaymentResponse toResponse(PaymentEntity paymentEntity) {
        return PaymentResponse.builder()
                .id(paymentEntity.getId())
                .fromAccount(paymentEntity.getFromAccount())
                .toAccount(paymentEntity.getToAccount())
                .amount(paymentEntity.getAmount())
                .status(paymentEntity.getStatus())
                .paymentDate(paymentEntity.getPaymentDate())
                .transactionNumber(paymentEntity.getTransactionNumber())
                .build();
    }

    public PaymentResponse updatePayment(PaymentEntity paymentEntity, PaymentRequest paymentRequest) {
        paymentEntity.setFromAccount(paymentRequest.getFromAccount());
        paymentEntity.setToAccount(paymentRequest.getToAccount());
        paymentEntity.setAmount(paymentRequest.getAmount());
        paymentEntity.setStatus(paymentRequest.getStatus());
        paymentEntity.setTransactionNumber(paymentRequest.getTransactionNumber());
        return toResponse(paymentEntity);
    }

    public void deletePayment(PaymentEntity paymentEntity) {
        paymentEntity.setStatus(CANCELLED);
    }
}

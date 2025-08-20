package az.ingress.model.request;

import az.ingress.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class PaymentRequest {
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private PaymentStatus status;
    private String transactionNumber;
}

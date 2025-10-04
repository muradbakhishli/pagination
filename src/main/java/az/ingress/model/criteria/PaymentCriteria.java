package az.ingress.model.criteria;

import az.ingress.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCriteria {
    private LocalDate dataFrom;
    private LocalDate dataTo;
    private BigDecimal amountFrom;
    private BigDecimal amountTo;
    private String transactionNumber;
    private PaymentStatus status;
}

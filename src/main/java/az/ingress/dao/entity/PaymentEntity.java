package az.ingress.dao.entity;

import az.ingress.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "payments")
@Entity
@Builder
@FieldNameConstants
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    @Enumerated(STRING)
    private PaymentStatus status;
    @CreationTimestamp
    private LocalDateTime paymentDate;
    private String transactionNumber;
}

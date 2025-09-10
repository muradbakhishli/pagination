package az.ingress.service.specification;

import az.ingress.dao.entity.PaymentEntity;
import az.ingress.dao.entity.PaymentEntity.Fields;
import az.ingress.model.criteria.PaymentCriteria;
import az.ingress.model.enums.PaymentStatus;
import az.ingress.util.PredicateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static az.ingress.util.PredicateUtil.applyLikePattern;

@RequiredArgsConstructor
public class PaymentSpecification implements Specification<PaymentEntity> {

    private final PaymentCriteria paymentCriteria;

    @Override
    public Predicate toPredicate(Root<PaymentEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {
        var predicates = PredicateUtil.builder()
                .addNullSafety(
                        paymentCriteria.getTransactionNumber(),
                        transactionNumber -> cb.like(
                                root.get(Fields.transactionNumber),
                                applyLikePattern(transactionNumber))
                )
                .addNullSafety(
                        paymentCriteria.getAmountFrom(),
                        amountFrom -> cb.greaterThanOrEqualTo(
                                root.get(Fields.amount),
                                amountFrom
                        )
                )
                .addNullSafety(
                        paymentCriteria.getAmountTo(),
                        amountTo -> cb.lessThanOrEqualTo(
                                root.get(Fields.amount),
                                amountTo
                        )
                )
                .add(PaymentStatus.CANCELLED,
                        status -> cb.notEqual(
                                root.get(Fields.status),
                                status
                        )
                )
                .build();
        return cb.and(predicates);
    }
}

package az.ingress.model.enums;

import az.ingress.dao.entity.PaymentEntity;
import az.ingress.model.response.PageableResponse;

import java.util.Collections;
import java.util.List;

import static az.ingress.model.enums.PaymentMapper.PAYMENT_MAPPER;

public enum PageableMapper {

    PAGEABLE_MAPPER;

    public PageableResponse toPageableResponse(List<PaymentEntity> payments,
                                               boolean hasNextPage,
                                               int lastPageNumber,
                                               long totalElements ) {
        var paymentList = payments.stream().map(PAYMENT_MAPPER::toResponse).toList();
        return PageableResponse.builder()
                .content(Collections.singletonList(paymentList))
                .totalElements(totalElements)
                .hasNext(hasNextPage)
                .lastPageNumber(lastPageNumber)
                .build();
    }
}

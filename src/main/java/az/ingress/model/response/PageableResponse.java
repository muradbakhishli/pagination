package az.ingress.model.response;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.criteria.PaymentCriteria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableResponse {
    private List<PaymentResponse> payments;
    private int lastPageNumber;
    private long totalElements;
    private boolean hasNext;
}

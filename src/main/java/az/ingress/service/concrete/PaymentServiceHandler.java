package az.ingress.service.concrete;

import az.ingress.dao.entity.PaymentEntity;
import az.ingress.dao.entity.PaymentEntity.Fields;
import az.ingress.dao.repository.PaymentRepository;
import az.ingress.exception.NotFoundException;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.criteria.PaymentCriteria;
import az.ingress.model.enums.PageableMapper;
import az.ingress.model.request.PaymentRequest;
import az.ingress.model.response.PageableResponse;
import az.ingress.model.response.PaymentResponse;
import az.ingress.service.abstraction.PaymentService;
import az.ingress.service.specification.PaymentSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static az.ingress.model.enums.ExceptionConstraints.PAYMENT_NOT_FOUND;
import static az.ingress.model.enums.PageableMapper.PAGEABLE_MAPPER;
import static az.ingress.model.enums.PaymentMapper.PAYMENT_MAPPER;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceHandler implements PaymentService {

    private final PaymentRepository paymentRepository;


    @Override
    public PaymentResponse getPayment(Long id) {
        var payment = fetchPaymentIfExist(id);
        return PAYMENT_MAPPER.toResponse(payment);
    }


    @Override
    public PageableResponse getPayments(PageCriteria pageCriteria, PaymentCriteria paymentCriteria) {
        var pageRequest = PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by(Fields.id));
        var specification = new PaymentSpecification(paymentCriteria);
        var paymentsPage = paymentRepository.findAll(specification, pageRequest);
        return PAGEABLE_MAPPER.toPageableResponse(paymentsPage.getContent(),
                paymentsPage.hasNext(), paymentsPage.getTotalPages(), paymentsPage.getTotalElements());
    }

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        var payment = PAYMENT_MAPPER.toEntity(paymentRequest);
        paymentRepository.save(payment);
        return PAYMENT_MAPPER.toResponse(payment);
    }

    @Override
    public PaymentResponse updatePayment(Long id, PaymentRequest paymentRequest) {
        var payment = fetchPaymentIfExist(id);
        PAYMENT_MAPPER.updatePayment(payment, paymentRequest);
        paymentRepository.save(payment);
        return PAYMENT_MAPPER.toResponse(payment);
    }

    @Override
    public void deletePayment(Long id) {
        var payment = fetchPaymentIfExist(id);
        PAYMENT_MAPPER.deletePayment(payment);
    }

    private PaymentEntity fetchPaymentIfExist(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PAYMENT_NOT_FOUND.getCode(), PAYMENT_NOT_FOUND.getMessage()));
    }
}

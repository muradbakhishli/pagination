package az.ingress.service

import az.ingress.dao.entity.PaymentEntity
import az.ingress.dao.repository.PaymentRepository
import az.ingress.exception.NotFoundException
import az.ingress.service.abstraction.PaymentService
import az.ingress.service.concrete.PaymentServiceHandler
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class PaymentServiceTest extends Specification{

    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    PaymentRepository paymentRepository
    PaymentService paymentService

    def setup() {
        paymentRepository = Mock()
        paymentService = new PaymentServiceHandler(paymentRepository)
    }

    def "TestGetPayment success case"() {
        given:
        def id = random.nextObject(Long)
        def paymentEntity = random.nextObject(PaymentEntity)

        when:
        def actual = paymentService.getPayment(id)

        then:
        1 * paymentRepository.findById(id) >> Optional.of(paymentEntity)
        actual.id == paymentEntity.id
        actual.amount == paymentEntity.amount
        actual.fromAccount == paymentEntity.fromAccount
        actual.toAccount == paymentEntity.toAccount
        actual.status == paymentEntity.status
        actual.transactionNumber == paymentEntity.transactionNumber
        actual.paymentDate == paymentEntity.paymentDate
    }

    def "TestGetPayment not found case"() {
        given:
        def id = random.nextObject(Long)

        when:
        paymentService.getPayment(id)

        then:
        1 * paymentRepository.findById(id) >> Optional.empty()
        NotFoundException ex = thrown()
        ex.message == "Payment not found"
        ex.code == "PAYMENT_NOT_FOUND"
    }
}

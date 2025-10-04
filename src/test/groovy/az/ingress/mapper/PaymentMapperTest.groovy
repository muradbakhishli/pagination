package az.ingress.mapper

import az.ingress.dao.entity.PaymentEntity
import az.ingress.model.enums.PaymentMapper
import az.ingress.model.enums.PaymentStatus
import az.ingress.model.request.PaymentRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static az.ingress.model.enums.PaymentMapper.PAYMENT_MAPPER
import static az.ingress.model.enums.PaymentStatus.CANCELLED

class PaymentMapperTest extends Specification {

    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "toEntity"() {
        given:
        def paymentRequest = random.nextObject(PaymentRequest)

        when:
        def entity = PAYMENT_MAPPER.toEntity(paymentRequest)

        then:
        entity.amount == paymentRequest.amount
        entity.fromAccount == paymentRequest.fromAccount
        entity.toAccount == paymentRequest.toAccount
        entity.status == paymentRequest.status
        entity.transactionNumber == paymentRequest.transactionNumber
        entity.paymentDate == null
    }

    def "toResponse"() {
        given:
        def entity = random.nextObject(PaymentEntity)

        when:
        def response = PAYMENT_MAPPER.toResponse(entity)

        then:
        response.id == entity.id
        response.fromAccount == entity.fromAccount
        response.toAccount == entity.toAccount
        response.amount == entity.amount
        response.status == entity.status
        response.transactionNumber == entity.transactionNumber
        response.paymentDate == entity.paymentDate
    }

    def "updatePayment"() {
        given:
        def entity = random.nextObject(PaymentEntity)
        def request = random.nextObject(PaymentRequest)

        when:
        def response = PAYMENT_MAPPER.updatePayment(entity, request)

        then:
        response.fromAccount == request.fromAccount
        response.toAccount == request.toAccount
        response.amount == request.amount
        response.status == request.status
        response.transactionNumber == request.transactionNumber
        response.id == entity.id
        response.paymentDate == entity.paymentDate
    }

    def "deletePayment"() {
        given:
        def entity = random.nextObject(PaymentEntity)

        when:
        def deleteAction = PAYMENT_MAPPER.deletePayment(entity)

        then:
        entity.status == CANCELLED
    }
}

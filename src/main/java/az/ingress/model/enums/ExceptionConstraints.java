package az.ingress.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionConstraints {
    INTERNAL_SERVER_EXCEPTION("INTERNAL_SERVER_EXCEPTION", "Unexpected error occurred"),
    PAYMENT_NOT_FOUND("PAYMENT_NOT_FOUND", "Payment not found"),
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "Method not allowed");

    private final String code;
    private final String message;
}

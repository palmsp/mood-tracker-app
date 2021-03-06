package org.palms.mood.tracker.config;

import lombok.extern.slf4j.Slf4j;
import org.palms.mood.tracker.api.model.ApiResponse;
import org.palms.mood.tracker.api.model.MetaInfo;
import org.palms.mood.tracker.api.model.MetaStatus;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(status, ex);
    }

    /**
     * @param t {@link Throwable}
     * @return {@link ResponseEntity}
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Throwable.class)
    public ResponseEntity<Object> otherException(Throwable t) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, t);
    }

    /**
     * @param ex {@link AuthenticationException}
     * @return {@link ResponseEntity}
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<Object> authException(AuthenticationException ex) {
        return buildResponseEntity(HttpStatus.UNAUTHORIZED, ex);
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, Throwable t) {
        log.error("Handle exception", t);
        final MetaInfo metaInfo = new MetaInfo(MetaStatus.ERROR, status.value());
        metaInfo.setMessage(t.getMessage());
        final ApiResponse response = new ApiResponse(metaInfo);
        return new ResponseEntity<>(response, status);
    }
}

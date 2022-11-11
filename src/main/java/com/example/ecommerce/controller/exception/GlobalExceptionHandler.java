package com.example.ecommerce.controller.exception;

import com.example.ecommerce.model.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ProductNotFoundException.class, BrandNotFoundException.class, ProductNotAvailableForSaleException.class})
    public final ResponseEntity<?> handleException(Exception ex, WebRequest request){
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof ProductNotFoundException productNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return handleUProductNotFoundException(productNotFoundException, headers, status, request);
        } 
        else if (ex instanceof BrandNotFoundException brandNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return handleBrandNotFoundException(brandNotFoundException, headers, status, request);
        }
        else if (ex instanceof ProductNotAvailableForSaleException productNotAvailableForSaleException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return handleProductNotAvailableForSaleException(productNotAvailableForSaleException, headers, status, request);
        }
        else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    protected ResponseEntity<?> handleProductNotAvailableForSaleException(ProductNotAvailableForSaleException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
    }


    protected ResponseEntity<?> handleBrandNotFoundException(BrandNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
    }

    protected ResponseEntity<?> handleUProductNotFoundException(ProductNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
    }

    protected ResponseEntity<?> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}

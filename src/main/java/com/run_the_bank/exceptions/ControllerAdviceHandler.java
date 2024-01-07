package com.run_the_bank.exceptions;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

public class ControllerAdviceHandler {

    @ExceptionHandler(value = {SaqueException.class})
    public ResponseEntity<ErrorMessage> resourceNotFoundException(SaqueException ex, WebRequest request, HttpServletRequest servletRequest) {

        ErrorMessage message = new ErrorMessage(MensagensExceptionConstants.MSG_SALDO_INSUFICIENTE, ex);
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DepositoException.class})
    public ResponseEntity<ErrorMessage> resourceNotFoundException(DepositoException ex, WebRequest request, HttpServletRequest servletRequest) {

        ErrorMessage message = new ErrorMessage(MensagensExceptionConstants.MSG_DEPOSITO_NAO_REALIZADO, ex);
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ErroException.class})
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ErroException ex, WebRequest request, HttpServletRequest servletRequest) {

        ErrorMessage message = new ErrorMessage(MensagensExceptionConstants.MSG_ERRO_NAO_ENCONTRADO, ex);
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }
}


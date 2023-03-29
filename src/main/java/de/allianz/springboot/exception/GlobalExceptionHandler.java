package de.allianz.springboot.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Locale;
import java.util.ResourceBundle;



@RestControllerAdvice
@Log
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    Locale locale = new Locale("fr");
    ResourceBundle errorBundle = ResourceBundle.getBundle("ErrorResource", locale);
    public final MessageSource messageSource;
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(RuntimeException rex, Exception ex) {
        logger.warn("INTERNAL SERVER ERROR");
        logger.warn(errorBundle.getString("internalServerError"));
        return new ResponseEntity<>(ErrorResponse.create(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object>  entityNOtFoundExceptioin(RuntimeException exception, WebRequest request){
        log.warning("The id was not found in the database!");
        log.info("INFO: The id was not found in the database!");
        logger.warn(errorBundle.getString("entityNotFoundError"));
        String msg = getMessageSource().getMessage("entityNotFoundError", null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(exception,msg,new HttpHeaders(), HttpStatus.NOT_FOUND,request);
    }
}

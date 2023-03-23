package de.allianz.springboot.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import static de.allianz.springboot.config.Translator.toLocale;
import static de.allianz.springboot.util.TranslatorCode.GREETINGS;

@RestControllerAdvice
@Log
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object>  entityNOtFoundExceptioin(RuntimeException exception, WebRequest request){

        return handleExceptionInternal(exception,exception.getMessage(),new HttpHeaders(), HttpStatus.NOT_FOUND,request);
    }
}

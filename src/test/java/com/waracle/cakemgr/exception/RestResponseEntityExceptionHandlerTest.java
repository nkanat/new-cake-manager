package com.waracle.cakemgr.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestResponseEntityExceptionHandlerTest {
    
    @InjectMocks
    private RestResponseEntityExceptionHandler exceptionHandler;
    
    @Mock
    private RuntimeException ex;

    @Mock
    private WebRequest request;

    @Mock
    private MethodArgumentNotValidException exception;
    @Mock
    private BindingResult result;
    @Mock
    private FieldError fieldError;

    @Test
    void testHandleDuplicateCakeTitleException(){
        ResponseEntity<Object> entity = exceptionHandler.handleDuplicateCakeTitleException(ex, request);
        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE,entity.getStatusCode());
        Assertions.assertNotNull(entity.getBody());
    }

    @Test
    void testHandleMethodArgumentNotValid(){
        when(exception.getBindingResult()).thenReturn(result);
        when(result.getFieldErrors()).thenReturn(List.of(fieldError));
        when(fieldError.getDefaultMessage()).thenReturn("Error Message");
        ResponseEntity<Object> entity = exceptionHandler.handleMethodArgumentNotValid(exception,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,entity.getStatusCode());
        Assertions.assertNotNull(entity.getBody());
    }
}

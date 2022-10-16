package com.waracle.cakemgr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class DuplicateCakeTitleException extends RuntimeException {

    public DuplicateCakeTitleException(String message){
        super(message);
    }
}

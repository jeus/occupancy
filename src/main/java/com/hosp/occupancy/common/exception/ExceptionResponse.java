/*
 * @author jeus (suje@protonmail.com)
 * @since 2/28/20
 */
package com.hosp.occupancy.common.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ExceptionResponse {
    private Date date;
    private Long code;
    private String message;
    private String description;

    public ExceptionResponse(ExceptionDictionary exceptionDictionary) {
        code = exceptionDictionary.getId();
        message = exceptionDictionary.getCode();
        date = new Date();
    }

    public ExceptionResponse(ExceptionDictionary exceptionDictionary, String description) {
        code = exceptionDictionary.getId();
        message = exceptionDictionary.getCode();
        date = new Date();
        this.description = description;
    }

}
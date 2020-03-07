/*
 * @author jeus (suje@protonmail.com)
 * @since 2/28/20
 */
package com.hosp.occupancy.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PublicException extends RuntimeException {

    @Getter
    ExceptionDictionary exceptionDictionary;

    public PublicException(ExceptionDictionary exceptionDictionary, String description) {
        super(description);
        this.exceptionDictionary = exceptionDictionary;
    }
}
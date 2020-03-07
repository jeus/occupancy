/*
 * @author jeus (suje@protonmail.com)
 * @since 2/28/20
 */
package com.hosp.occupancy.common.exception;

import com.hosp.occupancy.common.enums.Parameter;
import com.hosp.occupancy.common.enums.ParameterGroup;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import static com.hosp.occupancy.common.utils.NumberUtils.isEqual;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.stream.Stream;

public enum ExceptionDictionary implements Parameter {

    UNDEFINEDERROR(Id.UNDEFINEDERROR, Message.UNDEFINEDERROR, HttpRequest.UNDEFINEDERROR),
    UNMATCHARGUMENT(Id.UNMATCHARGUMENT, Message.UNMATCHARGUMENT, HttpRequest.UNMATCHARGUMENT),
    PARAMETERNOTFOUND(Id.PARAMETERNOTFOUND, Message.PARAMETERNOTFOUND, HttpRequest.PARAMETERNOTFOUND),
    PARAMETERISNOTVALID(Id.PARAMETERISNOTVALID, Message.PARAMETERISNOTVALID, HttpRequest.PARAMETERISNOTVALID),
    IDISNOTUNIQUE(Id.IDISNOTUNIQUE, Message.IDISNOTUNIQUE, HttpRequest.IDISNOTUNIQUE),
    CONTENTNOTFOUND(Id.CONTENTNOTFOUND, Message.CONTENTNOTFOUND, HttpRequest.CONTENTNOTFOUND),
    NUMBERISNOTVALID(Id.NUMBERISNOTVALID, Message.NUMBERISNOTVALID, HttpRequest.NUMBERISNOTVALID),
    UNSUPPORTEDCOIN(Id.UNSUPPORTEDCOIN, Message.UNSUPPORTEDCOIN, HttpRequest.UNSUPPORTEDCOIN),
    ARGUMENTTOOLONG(Id.ARGUMENTTOOLONG, Message.ARGUMENTTOOLONG, HttpRequest.ARGUMENTTOOLONG),
    UNAUTHORIZED(Id.UNAUTHORIZED, Message.UNAUTHORIZED, HttpRequest.UNAUTHORIZED),
    FREQUENTLYREQUEST(Id.FREQUENTLYREQUEST, Message.FREQUENTLYREQUEST, HttpRequest.FREQUENTLYREQUEST),
    LIMITATION(Id.LIMITATION, Message.LIMITATION, HttpRequest.LIMITATION);

    private final Long id;
    private final String message;
    private final HttpStatus httpStatus;

    ExceptionDictionary(Long id, String message, HttpStatus type) {
        this.id = id;
        this.message = message;
        this.httpStatus = type;
    }

    public static ExceptionDictionary getById(Long id) {
        return Optional.ofNullable(id)
                .map(excId -> Stream.of(values()).parallel()
                        .filter(coin -> isEqual(coin.id, excId))
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format
                                ("This Exception not found {0}", excId))))
                .orElse(null);
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getCode() {
        return message;
    }

    @Override
    public ParameterGroup getGroup() {
        return ParameterGroup.EXCEPTION;
    }



    public static class Id {
        public static final Long UNDEFINEDERROR = 1000L;
        public static final Long UNMATCHARGUMENT = 1001L;
        public static final Long PARAMETERNOTFOUND = 1002L;
        public static final Long PARAMETERISNOTVALID = 1003L;
        public static final Long IDISNOTUNIQUE = 1004L;
        public static final Long CONTENTNOTFOUND = 1005L;
        public static final Long NUMBERISNOTVALID = 1006L;
        public static final Long UNSUPPORTEDCOIN = 1007L;
        public static final Long ARGUMENTTOOLONG = 1008L;
        public static final Long UNAUTHORIZED = 1009L;
        public static final Long FREQUENTLYREQUEST = 1010L;
        public static final Long LIMITATION = 1011L;
    }

    public static class Message {
        public static final String UNDEFINEDERROR = "THIS ERROR UNDEFINED";
        public static final String UNMATCHARGUMENT = "INPUT ARGUMENT IS NOT MATCH";
        public static final String PARAMETERNOTFOUND = "PARAMETER NOT FOUND";
        public static final String PARAMETERISNOTVALID = "PARAMETER IS NOT VALID";
        public static final String IDISNOTUNIQUE = "ID IS NOT UNIQUE";
        public static final String CONTENTNOTFOUND = "CONTENT NOT FOUND";
        public static final String NUMBERISNOTVALID = "NUMBER IS NOT VALID";
        public static final String UNSUPPORTEDCOIN = "THIS COIN IS NOT SUPPORT";
        public static final String ARGUMENTTOOLONG = "ARGUMENT IS TOO LONG";
        public static final String UNAUTHORIZED = "USER DON'T HAVE PERMISSION";
        public static final String FREQUENTLYREQUEST = "FREQUNTLY REQUEST";
        public static final String LIMITATION = "LIMIT TO CREATE";
    }

    public static class HttpRequest {
        public static final HttpStatus UNDEFINEDERROR = HttpStatus.BAD_REQUEST;
        public static final HttpStatus UNMATCHARGUMENT = HttpStatus.BAD_REQUEST;
        public static final HttpStatus PARAMETERNOTFOUND = HttpStatus.BAD_REQUEST;
        public static final HttpStatus PARAMETERISNOTVALID = HttpStatus.BAD_REQUEST;
        public static final HttpStatus IDISNOTUNIQUE = HttpStatus.BAD_REQUEST;
        public static final HttpStatus CONTENTNOTFOUND = HttpStatus.NO_CONTENT;
        public static final HttpStatus NUMBERISNOTVALID = HttpStatus.BAD_REQUEST;
        public static final HttpStatus UNSUPPORTEDCOIN = HttpStatus.BAD_REQUEST;
        public static final HttpStatus ARGUMENTTOOLONG = HttpStatus.BAD_REQUEST;
        public static final HttpStatus UNAUTHORIZED = HttpStatus.UNAUTHORIZED;
        public static final HttpStatus FREQUENTLYREQUEST = HttpStatus.UNAUTHORIZED;
        public static final HttpStatus LIMITATION = HttpStatus.UNAUTHORIZED;
    }

}
/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */

package com.hosp.occupancy.common.helper;

import com.hosp.occupancy.common.exception.PublicException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;


public interface AssertHelper {

    /**
     * @param params contain reference of object name.
     *               If the object is not null, it will be thrown AssertingException and
     *               this argument will be used in exception's message.
     */
    void isNull(Object object, Map<String, Object> params) throws PublicException;


    /**
     * @param object contain reference of object name.
     *               If the object is null it will be thrown AssertingException and
     *               this argument will be used in exception's message.
     */
    void notNull(Object object) throws PublicException;


    /**
     * Assert if two arguments are not equal, otherwise throws AssertingException with default message.<Br>
     * <B>Note : </B>This method is null safe.
     */
    <T> void isNotEqual(T first, T second) throws PublicException;

    /**
     * Assert if two arguments are equal, otherwise throws PublicException with default message.<Br>
     * <B>Note : </B>This method is null safe.
     */
    <T> void isEqual(T first, T second) throws PublicException;



}


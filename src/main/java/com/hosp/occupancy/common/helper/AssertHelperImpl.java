/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */

package com.hosp.occupancy.common.helper;


import com.hosp.occupancy.common.exception.ExceptionDictionary;
import com.hosp.occupancy.common.exception.PublicException;
import org.springframework.stereotype.Component;

import java.util.Map;



@Component
public class AssertHelperImpl implements AssertHelper {
    @Override
    public void isNull(Object object, Map<String, Object> params)  throws PublicException {
        assert (params != null) : "params can not be null!";
        if (object != null) {
            throw new PublicException(ExceptionDictionary.UNMATCHARGUMENT,"assertion.is-null"+params.toString());
        }
    }

    @Override
    public void notNull(Object object) throws PublicException {
        if (object == null) {
            throw new PublicException(ExceptionDictionary.NUMBERISNOTVALID,"assertion.not-null");
        }
    }

    @Override
    public <T> void isNotEqual(T first, T second) throws PublicException {
        if (first == second) {
            throw new PublicException(ExceptionDictionary.IDISNOTUNIQUE,"common.assertion.is-not-equal");
        }
        if ((first != null) && first.equals(second)) {
            throw new PublicException(ExceptionDictionary.IDISNOTUNIQUE,"common.assertion.is-not-equal");
        }
    }


    @Override
    public <T> void isEqual(T first, T second)  throws PublicException {
        if (first == second) return;
        if ((first == null) || (!first.equals(second))) {
            throw new PublicException(ExceptionDictionary.UNMATCHARGUMENT,"assertion.isEqual");
        }
    }


}

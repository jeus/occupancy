/*
 * @author jeus (suje@protonmail.com)
 * @since 2/28/20
 */

package com.hosp.occupancy.common.enums;

public interface Parameter {

    Long getId();

    String getCode();

    ParameterGroup getGroup();

    default Long getUniqueId() {
        return getId();
    }
}

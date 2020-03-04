/*
 * @author jeus (suje@protonmail.com)
 * @since 2/28/20
 */
package com.hosp.occupancy.common.enums;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.stream.Stream;

import static com.hosp.occupancy.common.utils.NumberUtils.isEqual;

public enum ParameterGroup implements Restorable<Long> {

    EXCEPTION(Id.EXCEPTION, Code.EXCEPTION),
    ROOMTYPE(Id.ROOMTYPE, Code.ROOMTYPE);

    private final Long id;
    private final String code;

    ParameterGroup(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public static ParameterGroup getById(Long id) {
        return Optional.ofNullable(id)
                .map(groupId -> Stream.of(values()).parallel()
                        .filter(group -> isEqual(group.id, groupId))
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("ParameterGroup not found for id {0}", groupId))))
                .orElse(null);
    }

    public static ParameterGroup getByCode(String code) {
        return Optional.ofNullable(code)
                .map(groupCode -> Stream.of(values()).parallel()
                        .filter(group -> isEqual(group.code, groupCode))
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("ParameterGroup not found for code {0}", groupCode))))
                .orElse(null);
    }

    @Override
    public Long getUniqueId() {
        return id;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public static class Id {
        static final Long EXCEPTION = 1L;
        static final Long ROOMTYPE = 2L;
    }

    public static class Code {
        static final String EXCEPTION = "EXCEPTION";
        static final String ROOMTYPE = "ROOMTYPE";
    }
}
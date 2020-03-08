/*
 * @author jeus (suje@protonmail.com)
 * @since 3/7/20
 */
package com.hosp.occupancy.pojo.dto.room;

import com.hosp.occupancy.common.enums.RoomType;
import lombok.Data;

@Data
public class RoomDto {
    private RoomType roomType = null;
    private int floor = 0;
    private int number = 0;
    private String description = null;
    private boolean free = true;
}

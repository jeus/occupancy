/*
 * @author jeus (suje@protonmail.com)
 * @since 3/7/20
 */
package com.hosp.occupancy.pojo.dto.room;

import com.hosp.occupancy.common.enums.RoomType;
import lombok.Data;

@Data
public class RoomDto {
    RoomType roomType = null;
    int floor = 0;
    int number = 0;
    String description = null;
    boolean free = true;
}

/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */

package com.hosp.occupancy.pojo.dto.room;

import com.hosp.occupancy.common.enums.RoomType;
import lombok.Data;

@Data
public class RoomInsertDto {
    RoomType roomType;
    int floor;
    int number;
    String description;
}

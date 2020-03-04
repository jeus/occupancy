/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */

package com.hosp.occupancy.model.room;

import com.hosp.occupancy.common.enums.RoomType;
import lombok.Data;


public interface Room {

    void setFree();
    void setReserve();
}

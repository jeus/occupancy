/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */

package com.hosp.occupancy.model.room;

import com.hosp.occupancy.common.enums.RoomType;
import lombok.Data;

@Data
public abstract class RoomAbstract implements Room {

    RoomType roomType = null;
    int floor = 0;
    int number = 0;
    String description = null;
    boolean free = true;

    @Override
    public void setFree() {
        this.free = true;
    }

    @Override
    public void setReserve() {
        this.free = false;
    }

    abstract int getMaxPrice();
}

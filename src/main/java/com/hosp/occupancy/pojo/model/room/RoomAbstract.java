/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */

package com.hosp.occupancy.pojo.model.room;

import com.hosp.occupancy.common.enums.RoomType;
import lombok.Data;

@Data
public abstract class RoomAbstract {

    RoomType roomType = null;
    int floor = 0;
    int number = 0;
    String description = null;
    boolean free = true;

    public void free() {
        this.free = true;
    }

    public void book() {
        this.free = false;
    }

    public int getMinPrice(){
        return roomType.getMinPrice();
    }
}

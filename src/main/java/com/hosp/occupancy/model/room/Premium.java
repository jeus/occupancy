/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */

package com.hosp.occupancy.model.room;

import com.hosp.occupancy.common.enums.RoomType;

public class Premium extends RoomAbstract {
    public Premium(){setRoomType(RoomType.PREMIUM);}

    @Override
    int getMaxPrice() {
        return 400;
    }
}

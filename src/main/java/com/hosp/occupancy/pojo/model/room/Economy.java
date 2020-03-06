/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */

package com.hosp.occupancy.pojo.model.room;

import com.hosp.occupancy.common.enums.RoomType;


public class Economy extends RoomAbstract implements Room {
    public Economy(){setRoomType(RoomType.ECONOMY);}

    @Override
    int getMaxPrice() {
        return 100;
    }
}

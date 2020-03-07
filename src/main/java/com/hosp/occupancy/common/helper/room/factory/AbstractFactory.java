/*
 * @author jeus (suje@protonmail.com)
 * @since 3/6/20
 */
package com.hosp.occupancy.common.helper.room.factory;

import com.hosp.occupancy.pojo.dto.room.RoomInsertDto;
import com.hosp.occupancy.pojo.model.room.Room;

public abstract class AbstractFactory {
    abstract Room getRoom(RoomInsertDto roomInsertDto) ;
}
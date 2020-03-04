/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */
package com.hosp.occupancy.model.room.factory;

import com.hosp.occupancy.model.dto.RoomInsertDto;
import com.hosp.occupancy.model.room.RoomAbstract;

public abstract class AbstractFactory {
    abstract RoomAbstract getRoom(RoomInsertDto roomInsertDto) ;
}

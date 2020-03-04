/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */

package com.hosp.occupancy.model.room.factory;

import com.hosp.occupancy.common.enums.RoomType;
import com.hosp.occupancy.model.dto.RoomInsertDto;
import com.hosp.occupancy.model.room.Economy;
import com.hosp.occupancy.model.room.Premium;
import com.hosp.occupancy.model.room.RoomAbstract;

public class RoomFactory extends AbstractFactory {

    @Override
    public RoomAbstract getRoom(RoomInsertDto roomInsertDto) {
        if (roomInsertDto.getRoomType() == RoomType.ECONOMY) {
            Economy economy = new Economy();
            economy.setFloor(roomInsertDto.getFloor());
            economy.setNumber(roomInsertDto.getNumber());
            economy.setDescription(roomInsertDto.getDescription());
            economy.setFree();
            return economy;
        } else if (roomInsertDto.getRoomType() == RoomType.PREMIUM) {
            Premium premium = new Premium();
            premium.setFloor(roomInsertDto.getFloor());
            premium.setNumber(roomInsertDto.getNumber());
            premium.setDescription(roomInsertDto.getDescription());
            premium.setFree();
            return premium;
        } else
            return null;

    }
}

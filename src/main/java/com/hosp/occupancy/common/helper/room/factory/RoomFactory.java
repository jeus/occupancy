/*
 * @author jeus (suje@protonmail.com)
 * @since 3/6/20
 */
package com.hosp.occupancy.common.helper.room.factory;

import com.hosp.occupancy.common.enums.RoomType;
import com.hosp.occupancy.pojo.dto.room.RoomInsertDto;
import com.hosp.occupancy.pojo.model.room.Economy;
import com.hosp.occupancy.pojo.model.room.Premium;
import com.hosp.occupancy.pojo.model.room.RoomAbstract;

public class RoomFactory extends AbstractFactory {

    @Override
    public RoomAbstract getRoom(RoomInsertDto roomInsertDto) {
        if (roomInsertDto.getRoomType() == RoomType.ECONOMY) {
            Economy economy = new Economy();
            economy.setFloor(roomInsertDto.getFloor());
            economy.setNumber(roomInsertDto.getNumber());
            economy.setDescription(roomInsertDto.getDescription());
            economy.free();
            return economy;
        } else if (roomInsertDto.getRoomType() == RoomType.PREMIUM) {
            Premium premium = new Premium();
            premium.setFloor(roomInsertDto.getFloor());
            premium.setNumber(roomInsertDto.getNumber());
            premium.setDescription(roomInsertDto.getDescription());
            premium.free();
            return premium;
        } else
            return null;

    }
}
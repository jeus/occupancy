/*
 * @author jeus (suje@protonmail.com)
 * @since 3/5/20
 */
package com.hosp.occupancy.common.helper.room;

import com.hosp.occupancy.common.enums.RoomType;
import com.hosp.occupancy.common.helper.room.factory.RoomFactory;
import com.hosp.occupancy.pojo.dto.room.RoomDto;
import com.hosp.occupancy.pojo.dto.room.RoomInsertDto;
import com.hosp.occupancy.pojo.model.room.RoomAbstract;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RoomHelper {

    public RoomInsertDto createRandom(RoomType roomType) {
        final var base = 100;
        final var countFloor = 100;
        final var countRoomsFloor = 99;
        Random rand = new Random();
        int floor = rand.nextInt(countFloor) + 1;
        int number = floor * base + (rand.nextInt(countRoomsFloor) + 1);
        var roomInsertDto = new RoomInsertDto();
        roomInsertDto.setRoomType(roomType);
        roomInsertDto.setNumber(number);
        roomInsertDto.setFloor(floor);
        roomInsertDto.setDescription(roomType.getCode() + " HOTEL TRANSYLVANIA ROOM. number:" + number + " floor:" + floor);
        return roomInsertDto;
    }



    public RoomAbstract roomFactory(RoomInsertDto roomInsertDto){
        RoomFactory roomFactory = new RoomFactory();
        return roomFactory.getRoom(roomInsertDto);
    }

}

/*
 * @author jeus (suje@protonmail.com)
 * @since 3/5/20
 */
package com.hosp.occupancy.common.helper.room;

import com.hosp.occupancy.common.enums.RoomType;
import com.hosp.occupancy.common.helper.room.factory.RoomFactory;
import com.hosp.occupancy.model.dto.RoomDto;
import com.hosp.occupancy.model.dto.RoomInsertDto;
import com.hosp.occupancy.model.room.RoomAbstract;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RoomHelper {

    public RoomInsertDto createRandom(RoomType roomType) {
        final var base = 100;
        final var countFloor = 10;
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

    public RoomDto roomMapper( List<RoomAbstract> rooms){
        var roomDto = new RoomDto();
        roomDto.setCountEconomy((int)rooms.stream().filter(r -> r.getRoomType() == RoomType.ECONOMY).count());
        roomDto.setCountPremium((int)rooms.stream().filter(r -> r.getRoomType() == RoomType.PREMIUM).count());
        roomDto.setCountFreeEconomy((int)rooms.stream().filter(r -> r.isFree() && r.getRoomType() == RoomType.ECONOMY).count());
        roomDto.setCountFreePremium((int)rooms.stream().filter(r -> r.isFree() && r.getRoomType() == RoomType.PREMIUM).count());
        roomDto.setRooms(rooms);
        return roomDto;
    }

    public RoomAbstract roomFactory(RoomInsertDto roomInsertDto){
        RoomFactory roomFactory = new RoomFactory();
        return roomFactory.getRoom(roomInsertDto);
    }

}

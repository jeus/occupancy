/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */

package com.hosp.occupancy.rest;

import com.hosp.occupancy.common.enums.RoomType;
import com.hosp.occupancy.model.room.RoomAbstract;
import com.hosp.occupancy.model.dto.RoomInsertDto;
import com.hosp.occupancy.model.dto.RoomDto;
import com.hosp.occupancy.model.room.factory.RoomFactory;
import com.hosp.occupancy.rest.manager.InsertRoomValidator;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/room")
public class OccupancyController {

    private final InsertRoomValidator insertRoomValidator;
    List<RoomAbstract> rooms = new ArrayList<>();

    public OccupancyController(InsertRoomValidator insertRoomValidator) {
        this.insertRoomValidator = insertRoomValidator;
    }


    @GetMapping
    public RoomDto getRooms() {
        return roomMapper();
    }

    @PostMapping
    public RoomDto addRoom(RoomInsertDto roomInsertDto) {
        var roomFactory = new RoomFactory();
        var roomAbstract = roomFactory.getRoom(roomInsertDto);
        insertRoomValidator.validateRoomForInsert(roomAbstract,rooms);
        rooms.add(roomAbstract);
        return roomMapper();
    }

    private RoomDto roomMapper(){
        var roomDto = new RoomDto();
        roomDto.setCountEconomy(rooms.stream().filter(r -> r.getRoomType() == RoomType.ECONOMY).count());
        roomDto.setCountPremium(rooms.stream().filter(r -> r.getRoomType() == RoomType.PREMIUM).count());
        roomDto.setCountFreeEconomy(rooms.stream().filter(r -> r.isFree() && r.getRoomType() == RoomType.ECONOMY).count());
        roomDto.setCountFreePremium(rooms.stream().filter(r -> r.isFree() && r.getRoomType() == RoomType.PREMIUM).count());
        roomDto.setRooms(rooms);
        return roomDto;
    }

}

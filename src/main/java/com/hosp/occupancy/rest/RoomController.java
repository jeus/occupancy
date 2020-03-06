/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */
package com.hosp.occupancy.rest;

import com.hosp.occupancy.common.enums.RoomType;
import com.hosp.occupancy.common.helper.room.RoomHelper;
import com.hosp.occupancy.common.helper.room.factory.RoomFactory;
import com.hosp.occupancy.common.translator.RoomMapper;
import com.hosp.occupancy.pojo.dto.room.FreeRoomDto;
import com.hosp.occupancy.pojo.model.room.RoomAbstract;
import com.hosp.occupancy.pojo.dto.room.RoomInsertDto;
import com.hosp.occupancy.pojo.dto.room.RoomDto;
import com.hosp.occupancy.rest.manager.InsertRoomValidator;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hotel/room")
public class RoomController {

    final RoomMapper roomMapper;
    final RoomHelper roomHelper;

    private final InsertRoomValidator insertRoomValidator;
    List<RoomAbstract> rooms = new ArrayList<>();

    public RoomController(InsertRoomValidator insertRoomValidator, RoomHelper roomHelper, RoomMapper roomMapper) {
        this.insertRoomValidator = insertRoomValidator;
        this.roomHelper = roomHelper;
        this.roomMapper = roomMapper;
    }


    @GetMapping
    public RoomDto getRooms() {
        return roomMapper.toRoomDto(rooms);
    }

    @PostMapping
    public RoomDto addRoom(RoomInsertDto roomInsertDto) {
        var roomFactory = new RoomFactory();
        var roomAbstract = roomFactory.getRoom(roomInsertDto);
        insertRoomValidator.validateRoomForInsert(roomAbstract, rooms);
        rooms.add(roomAbstract);
        return roomMapper.toRoomDto(rooms);
    }

    public void clearRooms() {
        rooms.clear();
    }

    @PostMapping("/wizard")
    public RoomDto addRooms(FreeRoomDto freeRoomDto) {
        clearRooms();
        for (var i = 0; i < freeRoomDto.getCountEconomy(); i++)
            addRoom(roomHelper.createRandom(RoomType.ECONOMY));
        for (var i = 0; i < freeRoomDto.getCountPremium(); i++)
            addRoom(roomHelper.createRandom(RoomType.PREMIUM));
        return roomMapper.toRoomDto(rooms);
    }


}

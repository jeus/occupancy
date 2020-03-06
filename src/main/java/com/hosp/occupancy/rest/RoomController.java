/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */

package com.hosp.occupancy.rest;

import com.hosp.occupancy.common.helper.room.RoomHelper;
import com.hosp.occupancy.common.helper.room.factory.RoomFactory;
import com.hosp.occupancy.model.room.RoomAbstract;
import com.hosp.occupancy.model.dto.RoomInsertDto;
import com.hosp.occupancy.model.dto.RoomDto;
import com.hosp.occupancy.rest.manager.InsertRoomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    final RoomHelper roomHelper;
    private final InsertRoomValidator insertRoomValidator;
    List<RoomAbstract> rooms = new ArrayList<>();

    public RoomController(InsertRoomValidator insertRoomValidator, RoomHelper roomHelper) {
        this.insertRoomValidator = insertRoomValidator;
        this.roomHelper = roomHelper;
    }


    @GetMapping
    public RoomDto getRooms() {
        return roomHelper.roomMapper(rooms);
    }

    @PostMapping
    public RoomDto addRoom(RoomInsertDto roomInsertDto) {
        var roomFactory = new RoomFactory();
        var roomAbstract = roomFactory.getRoom(roomInsertDto);
        insertRoomValidator.validateRoomForInsert(roomAbstract,rooms);
        rooms.add(roomAbstract);
        return roomHelper.roomMapper(rooms);
    }

    public void clearRooms(){
        rooms.clear();
    }



}

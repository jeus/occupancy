/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */
package com.hosp.occupancy.rest;

import com.hosp.occupancy.common.enums.RoomType;
import com.hosp.occupancy.common.helper.room.RoomHelper;
import com.hosp.occupancy.common.helper.room.factory.RoomFactory;
import com.hosp.occupancy.common.translator.HotelMapper;
import com.hosp.occupancy.common.translator.RoomMapper;
import com.hosp.occupancy.pojo.dto.room.FreeRoomDto;
import com.hosp.occupancy.pojo.model.room.Room;
import com.hosp.occupancy.pojo.dto.room.RoomInsertDto;
import com.hosp.occupancy.pojo.dto.room.HotelDto;
import com.hosp.occupancy.rest.manager.InsertRoomValidator;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel/room")
public class RoomController {

    final RoomMapper roomMapper;
    final HotelMapper hotelMapper;
    final RoomHelper roomHelper;

    private final InsertRoomValidator insertRoomValidator;
    List<Room> rooms = new ArrayList<>();

    public RoomController(InsertRoomValidator insertRoomValidator, RoomHelper roomHelper, RoomMapper roomMapper, HotelMapper hotelMapper) {
        this.insertRoomValidator = insertRoomValidator;
        this.roomHelper = roomHelper;
        this.roomMapper = roomMapper;
        this.hotelMapper = hotelMapper;
    }


    @GetMapping
    public HotelDto getRooms() {
        return hotelMapper.toHotelDto(getCountEconomy(), getCountPremium(), getCountFreeEconomy(), getCountFreePremium(),
                roomMapper.toRoomDtos(rooms));
    }

    @PostMapping
    public HotelDto addRoom(RoomInsertDto roomInsertDto) {
        var roomFactory = new RoomFactory();
        var roomAbstract = roomFactory.getRoom(roomInsertDto);
        insertRoomValidator.validateRoomForInsert(roomAbstract, rooms);
        rooms.add(roomAbstract);
        return hotelMapper.toHotelDto(getCountEconomy(), getCountPremium(), getCountFreeEconomy(), getCountFreePremium(),
                roomMapper.toRoomDtos(rooms));
    }

    public void clearRooms() {
        rooms.clear();
    }

    @PostMapping("/wizard")
    public HotelDto addRooms(FreeRoomDto freeRoomDto) {
        clearRooms();
        for (var i = 0; i < freeRoomDto.getCountEconomy(); i++)
            addRoom(roomHelper.createRandom(RoomType.ECONOMY));
        for (var i = 0; i < freeRoomDto.getCountPremium(); i++)
            addRoom(roomHelper.createRandom(RoomType.PREMIUM));
        return hotelMapper.toHotelDto(getCountEconomy(), getCountPremium(), getCountFreeEconomy(), getCountFreePremium(),
                roomMapper.toRoomDtos(rooms));
    }

    public int getCountEconomy() {
        return (int) rooms.stream().filter(r -> r.getRoomType() == RoomType.ECONOMY).count();
    }

    public int getCountPremium() {
        return (int) rooms.stream().filter(r -> r.getRoomType() == RoomType.PREMIUM).count();
    }

    public int getCountFreeEconomy() {
        return (int) rooms.stream().filter(r -> r.isFree() && r.getRoomType() == RoomType.ECONOMY).count();
    }

    public int getCountFreePremium() {
        return (int) rooms.stream().filter(r -> r.isFree() && r.getRoomType() == RoomType.PREMIUM).count();
    }

    public Room bookEconomy() {
        Optional<Room> roomAbstract = rooms.stream().filter(r -> r.isFree() && r.getRoomType() == RoomType.ECONOMY && r.isFree()).findFirst();
        roomAbstract.ifPresent(room -> room.setFree(false));
        return roomAbstract.orElse(null);
    }

    public Room bookPremium() {
        Optional<Room> roomAbstract = rooms.stream().filter(r -> r.isFree() && r.getRoomType() == RoomType.PREMIUM && r.isFree()).findFirst();
        roomAbstract.ifPresent(room -> room.setFree(false));
        return roomAbstract.orElse(null);
    }


}

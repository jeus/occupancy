/*
 * @author jeus (suje@protonmail.com)
 * @since 3/5/20
 */
package com.hosp.occupancy.rest;

import com.hosp.occupancy.common.enums.RoomType;
import com.hosp.occupancy.common.helper.room.RoomHelper;
import com.hosp.occupancy.core.Occupancy;
import com.hosp.occupancy.model.dto.FreeRoomDto;
import com.hosp.occupancy.model.dto.HotelStateDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wizard")
public class WizardController {

    final RoomController roomController;
    final Occupancy occupancy;
    final RoomHelper roomHelper;

    public WizardController(RoomController roomController, Occupancy occupancy, RoomHelper roomHelper) {
        this.roomController = roomController;
        this.occupancy = occupancy;
        this.roomHelper = roomHelper;
    }

    @PostMapping("/room")
    public HotelStateDto addRooms(FreeRoomDto freeRoomDto) {
        roomController.clearRooms();
        for (var i = 0; i < freeRoomDto.getCountEconomy(); i++)
            roomController.addRoom(roomHelper.createRandom(RoomType.ECONOMY));
        for (var i = 0; i < freeRoomDto.getCountPremium(); i++)
            roomController.addRoom(roomHelper.createRandom(RoomType.PREMIUM));

        return  occupancy.calculateFromScrach();
    }



}

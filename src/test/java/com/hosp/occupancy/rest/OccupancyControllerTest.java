package com.hosp.occupancy.rest;

import com.hosp.occupancy.common.enums.RoomType;
import com.hosp.occupancy.common.exception.PublicException;
import com.hosp.occupancy.model.dto.RoomInsertDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OccupancyControllerTest {

    @Autowired
    OccupancyController occupancyController;

    @BeforeEach
    void setUp() {
        occupancyController.rooms = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    public void addNewRoom() {
        var roomInsertDto = new RoomInsertDto();
        roomInsertDto.setFloor(1);
        roomInsertDto.setNumber(11);
        roomInsertDto.setRoomType(RoomType.ECONOMY);
        roomInsertDto.setDescription("new Room in hotel");
        var roomDto = occupancyController.addRoom(roomInsertDto);

        assertEquals(1, roomDto.getCountEconomy());
        assertEquals(1, roomDto.getCountFreeEconomy());
        assertEquals(0, roomDto.getCountPremium());
        assertEquals(0, roomDto.getCountFreePremium());
        assertEquals(roomInsertDto.getFloor(), roomDto.getRooms().get(0).getFloor());
        assertEquals(roomInsertDto.getNumber(), roomDto.getRooms().get(0).getNumber());
        assertEquals(roomInsertDto.getRoomType(), roomDto.getRooms().get(0).getRoomType());
        assertEquals(roomInsertDto.getDescription(), roomDto.getRooms().get(0).getDescription());
    }

    @Test
    public void addNewRoom1() {
        var roomInsertDto = createNewInserRoomDto(1, 11, RoomType.ECONOMY, "new Room in hotel");
        var roomInsertDto1 = createNewInserRoomDto(1, 12, RoomType.ECONOMY, "new Room in hotel");

        occupancyController.addRoom(roomInsertDto);
        var roomDto = occupancyController.addRoom(roomInsertDto1);

        assertEquals(2, roomDto.getCountEconomy());
        assertEquals(2, roomDto.getCountFreeEconomy());
        assertEquals(0, roomDto.getCountPremium());
        assertEquals(0, roomDto.getCountFreePremium());
        assertEquals(2, roomDto.getRooms().size());
    }

    @Test
    public void getException() {
        var roomInsertDto = createNewInserRoomDto(1, 11, RoomType.ECONOMY, "new Room in hotel");
        var roomInsertDto1 = createNewInserRoomDto(1, 11, RoomType.ECONOMY, "new Room in hotel");

        occupancyController.addRoom(roomInsertDto);
        assertThrows(PublicException.class, () -> occupancyController.addRoom(roomInsertDto1));
    }


    private RoomInsertDto createNewInserRoomDto(int floor, int number, RoomType roomType, String description) {
        var roomInsertDto = new RoomInsertDto();
        roomInsertDto.setFloor(floor);
        roomInsertDto.setNumber(number);
        roomInsertDto.setRoomType(roomType);
        roomInsertDto.setDescription(description);
        return roomInsertDto;
    }
}
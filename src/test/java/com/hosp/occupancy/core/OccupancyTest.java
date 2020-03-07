package com.hosp.occupancy.core;

import com.hosp.occupancy.common.helper.room.RoomHelper;
import com.hosp.occupancy.pojo.dto.hotel.HotelStateDto;
import com.hosp.occupancy.pojo.dto.room.RoomDto;
import com.hosp.occupancy.pojo.model.hotel.HotelState;
import com.hosp.occupancy.pojo.model.room.Economy;
import com.hosp.occupancy.pojo.model.room.Premium;
import com.hosp.occupancy.pojo.model.room.RoomAbstract;
import com.hosp.occupancy.rest.CustomerController;
import com.hosp.occupancy.rest.RoomController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class OccupancyTest {

    @Autowired
    Occupancy occupancy;
    @MockBean
    CustomerController customerController;
    @MockBean
    RoomController roomController;
    @Autowired
    RoomHelper roomHelper;


    @Test
    public void calculateFromScrach() {
        final long expCountEconomy=1;
        final long expCountPremium=1;
        final long expCountFreeEconomy=0;
        final long expCountFreePremium=0;
        final long expEconomyIncome=99;
        final long expPremiumIncome=100;

        Mockito.when(customerController.getPotential()).thenReturn(defaultPotential());
        Mockito.when(roomController.getRooms()).thenReturn(defaultRoomDto());
        var hotelState = occupancy.bookFromScrach();

        Assert.assertEquals(expCountEconomy,hotelState.getCountEconomy());
        Assert.assertEquals(expCountFreeEconomy,hotelState.getCountFreeEconomy());
        Assert.assertEquals(expEconomyIncome,hotelState.getEconomyIncome());
        Assert.assertEquals(expCountPremium,hotelState.getCountPremium());
        Assert.assertEquals(expCountFreePremium,hotelState.getCountFreePremium());
        Assert.assertEquals(expPremiumIncome,hotelState.getPremiumIncome());
        
        System.out.println(hotelState);
    }

    private List<Integer> defaultPotential() {
        Integer[] arr = {100, 99};
        return Arrays.asList(arr);
    }

    private RoomDto defaultRoomDto() {
        RoomDto roomDto = new RoomDto();
        roomDto.setCountEconomy(1);
        roomDto.setCountFreeEconomy(1);
        roomDto.setCountPremium(1);
        roomDto.setCountFreePremium(1);

        var economy = new Economy();
        economy.setDescription("Economy Mock room");
        economy.setFloor(1);
        economy.setNumber(101);

        var premium = new Premium();
        premium.setDescription("Premium Mock room");
        premium.setFloor(1);
        premium.setNumber(102);

        List<RoomAbstract> rooms = new ArrayList<>();
        rooms.add(economy);
        rooms.add(premium);
        roomDto.setRooms(rooms);
        return roomDto;
    }
}
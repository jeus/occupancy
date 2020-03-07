package com.hosp.occupancy.core;

import com.hosp.occupancy.common.helper.room.RoomHelper;
import com.hosp.occupancy.common.translator.RoomMapper;
import com.hosp.occupancy.pojo.dto.room.FreeRoomDto;
import com.hosp.occupancy.pojo.dto.room.HotelDto;
import com.hosp.occupancy.pojo.dto.room.RoomDto;
import com.hosp.occupancy.pojo.dto.room.RoomInsertDto;
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
    @Autowired
    RoomController roomController;
    @Autowired
    RoomHelper roomHelper;
    @Autowired
    RoomMapper roomMapper;


    @Test
    public void calculateFromScrach() {
        final long expCountEconomy=1;
        final long expCountPremium=1;
        final long expCountFreeEconomy=0;
        final long expCountFreePremium=0;
        final long expEconomyIncome=99;
        final long expPremiumIncome=100;

        Mockito.when(customerController.getPotential()).thenReturn(defaultPotential());
        var freeRoomDto = new FreeRoomDto(1,1);
        roomController.addRooms(freeRoomDto);

        var hotelState = occupancy.bookFromScratch();

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
}
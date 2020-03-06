/*
 * @author jeus (suje@protonmail.com)
 * @since 3/5/20
 */
package com.hosp.occupancy.core;

import com.hosp.occupancy.model.dto.HotelStateDto;
import com.hosp.occupancy.model.dto.RoomDto;
import com.hosp.occupancy.rest.CustomerController;
import com.hosp.occupancy.rest.RoomController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Occupancy {

    @Autowired
    CustomerController customerController;
    @Autowired
    RoomController roomController;

    /**
     * call after add rooms
     */
    public HotelStateDto calculateFromScrach() {
        var potentials = customerController.getPotential();
        var roomDto = roomController.getRooms();
        var premiumIncome = 0;
        var economyIncome = 0;
        if (potentials.isEmpty() || (roomDto.getCountPremium() == 0 && roomDto.getCountEconomy() == 0))
            return defaultHotelState();
        potentials.sort(Collections.reverseOrder());
        List<Integer> premiumPotential = potentials.stream().filter(c -> c  >= 100).collect(Collectors.toList());
        List<Integer> economyPotential = potentials.stream().filter(c -> c  < 100).collect(Collectors.toList());
        int premiumEfficiency = premiumPotential.size() - roomDto.getCountPremium();
        int economyEfficiency = economyPotential.size() - roomDto.getCountEconomy();

        var premiIterator = premiumPotential.iterator();
        while (premiIterator.hasNext() &&  roomDto.getCountFreePremium() != 0 ){
            premiumIncome += premiIterator.next();
            roomDto.setCountFreePremium(roomDto.getCountFreePremium() - 1);
            premiIterator.remove();
        }
        var econIterator = economyPotential.iterator();
        while (econIterator.hasNext())
        {
            if(economyEfficiency > 0 && roomDto.getCountFreePremium() > 0){
                premiumIncome += econIterator.next();
                roomDto.setCountFreePremium(roomDto.getCountFreePremium() - 1);
                econIterator.remove();
            }else
            {
                economyIncome += econIterator.next();
                roomDto.setCountFreeEconomy(roomDto.getCountFreeEconomy() - 1);
                econIterator.remove();
                if(roomDto.getCountFreeEconomy() == 0)
                    break;
            }
        }
        return creteHotelState(premiumIncome, economyIncome, roomDto);
    }

    /**
     * if we want calculate without clear history after add new potential
     */
    public void calculateByNewPotential() {
        //TODO: if we want calculate without clear history after add new potential
    }

    /**
     * if we want calculate without clear history after add new room
     */
    public void calculateByNewRoom() {
        //TODO: if we want handle by saving history after add new room
    }


    private HotelStateDto defaultHotelState() {
        var hotelStateDto = new HotelStateDto();
        hotelStateDto.setPremiumIncome(0);
        hotelStateDto.setEconomyIncome(0);
        hotelStateDto.setCountEconomy(roomController.getRooms().getCountEconomy());
        hotelStateDto.setCountPremium(roomController.getRooms().getCountPremium());
        hotelStateDto.setCountFreeEconomy(roomController.getRooms().getCountEconomy());
        hotelStateDto.setCountFreePremium(roomController.getRooms().getCountPremium());
        return hotelStateDto;
    }

    private HotelStateDto creteHotelState(int premiumIncode, int economyIncome, RoomDto roomDto) {
        var hotelStateDto = new HotelStateDto();
        hotelStateDto.setPremiumIncome(premiumIncode);
        hotelStateDto.setEconomyIncome(economyIncome);
        hotelStateDto.setCountEconomy(roomDto.getCountEconomy());
        hotelStateDto.setCountPremium(roomDto.getCountPremium());
        hotelStateDto.setCountFreeEconomy(roomDto.getCountFreeEconomy());
        hotelStateDto.setCountFreePremium(roomDto.getCountFreePremium());
        return hotelStateDto;
    }

}

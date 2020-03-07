/*
 * @author jeus (suje@protonmail.com)
 * @since 3/5/20
 */
package com.hosp.occupancy.core;

import com.hosp.occupancy.common.enums.RoomType;
import com.hosp.occupancy.pojo.dto.room.HotelDto;
import com.hosp.occupancy.pojo.model.hotel.Efficiency;
import com.hosp.occupancy.pojo.model.hotel.HotelState;
import com.hosp.occupancy.rest.CustomerController;
import com.hosp.occupancy.rest.RoomController;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Occupancy {

    final CustomerController customerController;
    final RoomController roomController;

    public Occupancy(CustomerController customerController, RoomController roomController) {
        this.customerController = customerController;
        this.roomController = roomController;
    }


    /**
     * call after add rooms
     */
    public HotelState bookFromScratch() {
        var potentials = customerController.getPotential();
        var premiumIncome = 0;
        var economyIncome = 0;
        if (potentials.isEmpty() || (roomController.getCountPremium() == 0 && roomController.getCountEconomy() == 0))
            return defaultHotelState();
        potentials.sort(Collections.reverseOrder());
        List<Integer> premiumPotential = potentials.stream().filter(c -> c >= RoomType.PREMIUM.getMinPrice()).collect(Collectors.toList());
        List<Integer> economyPotential = potentials.stream().filter(c -> c < RoomType.PREMIUM.getMinPrice()).collect(Collectors.toList());
        var efficiency = new Efficiency();
        efficiency.setPremiumEfficiency(premiumPotential.size() - roomController.getCountPremium());
        efficiency.setEconomyEfficiency(economyPotential.size() - roomController.getCountEconomy());

        var premiIterator = premiumPotential.iterator();
        while (premiIterator.hasNext() && roomController.getCountFreePremium() != 0) {
            premiumIncome += premiIterator.next();
            roomController.bookPremium();
            premiIterator.remove();
        }
        var econIterator = economyPotential.iterator();
        while (econIterator.hasNext()) {
            if (efficiency.getEconomyEfficiency() > 0 && roomController.getCountFreePremium() > 0) {
                premiumIncome += econIterator.next();
                roomController.bookPremium();
                econIterator.remove();
            } else {
                economyIncome += econIterator.next();
                roomController.bookEconomy();
                econIterator.remove();
                if (roomController.getCountFreeEconomy() == 0)
                    break;
            }
        }
        return creteHotelState(roomController.getRooms(), efficiency, premiumIncome, economyIncome);
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


    private HotelState defaultHotelState() {
        var hotelState = new HotelState();
        hotelState.setPremiumIncome(0);
        hotelState.setEconomyIncome(0);
        hotelState.setCountEconomy(roomController.getRooms().getCountEconomy());
        hotelState.setCountPremium(roomController.getRooms().getCountPremium());
        hotelState.setCountFreeEconomy(roomController.getRooms().getCountEconomy());
        hotelState.setCountFreePremium(roomController.getRooms().getCountPremium());
        return hotelState;
    }

    private HotelState creteHotelState(HotelDto hotelDto, Efficiency efficiency, int premiumIncode, int economyIncome) {
        var hotelState = new HotelState();
        hotelState.setPremiumIncome(premiumIncode);
        hotelState.setEconomyIncome(economyIncome);
        hotelState.setCountEconomy(hotelDto.getCountEconomy());
        hotelState.setCountPremium(hotelDto.getCountPremium());
        hotelState.setCountFreeEconomy(hotelDto.getCountFreeEconomy());
        hotelState.setCountFreePremium(hotelDto.getCountFreePremium());
        hotelState.setEfficiency(efficiency);
        return hotelState;
    }

}

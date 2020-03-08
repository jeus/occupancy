/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */

package com.hosp.occupancy.pojo.dto.room;

import lombok.Data;

import java.util.List;

@Data
public class HotelDto {

    private int countEconomy;
    private int countPremium;
    private int countFreeEconomy;
    private int countFreePremium;
    private List<RoomDto> rooms;
}

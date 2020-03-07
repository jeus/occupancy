/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */

package com.hosp.occupancy.pojo.dto.room;

import lombok.Data;

import java.util.List;

@Data
public class HotelDto {

    int countEconomy;
    int countPremium;
    int countFreeEconomy;
    int countFreePremium;
    List<RoomDto> rooms;
}

/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */

package com.hosp.occupancy.model.dto;

import com.hosp.occupancy.model.room.Room;
import com.hosp.occupancy.model.room.RoomAbstract;
import lombok.Data;

import java.util.List;

@Data
public class RoomDto {

    long countEconomy;
    long countPremium;
    long countFreeEconomy;
    long countFreePremium;
    List<RoomAbstract> rooms;
}

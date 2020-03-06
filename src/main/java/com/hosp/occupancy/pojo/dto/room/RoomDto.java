/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */

package com.hosp.occupancy.pojo.dto.room;

import com.hosp.occupancy.pojo.model.room.RoomAbstract;
import lombok.Data;

import java.util.List;

@Data
public class RoomDto {

    int countEconomy;
    int countPremium;
    int countFreeEconomy;
    int countFreePremium;
    List<RoomAbstract> rooms;
}

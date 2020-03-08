/*
 * @author jeus (suje@protonmail.com)
 * @since 3/4/20
 */

package com.hosp.occupancy.pojo.dto.hotel;

import com.hosp.occupancy.pojo.model.hotel.Efficiency;
import lombok.Data;

@Data
public class HotelStateDto {
    private long countEconomy;
    private long countPremium;
    private long countFreeEconomy;
    private long countFreePremium;
    private long economyIncome;
    private long premiumIncome;
    private String description;
    private EfficiencyDto efficiency;

}

/*
 * @author jeus (suje@protonmail.com)
 * @since 3/4/20
 */

package com.hosp.occupancy.pojo.dto.hotel;

import com.hosp.occupancy.pojo.model.hotel.Efficiency;
import lombok.Data;

@Data
public class HotelStateDto {
    long countEconomy;
    long countPremium;
    long countFreeEconomy;
    long countFreePremium;
    long economyIncome;
    long premiumIncome;
    Efficiency efficiency;
}

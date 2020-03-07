/*
 * @author jeus (suje@protonmail.com)
 * @since 3/4/20
 */

package com.hosp.occupancy.pojo.model.hotel;

import lombok.Data;

@Data
public class HotelState {
    long countEconomy;
    long countPremium;
    long countFreeEconomy;
    long countFreePremium;
    long economyIncome;
    long premiumIncome;
    Efficiency efficiency;
}

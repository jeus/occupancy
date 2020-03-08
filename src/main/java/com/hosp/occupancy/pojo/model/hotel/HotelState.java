/*
 * @author jeus (suje@protonmail.com)
 * @since 3/4/20
 */

package com.hosp.occupancy.pojo.model.hotel;

import lombok.Data;

@Data
public class HotelState {
    private long countEconomy;
    private long countPremium;
    private long countFreeEconomy;
    private long countFreePremium;
    private long economyIncome;
    private long premiumIncome;
    private String description;
    private Efficiency efficiency;
}

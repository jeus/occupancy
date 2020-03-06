/*
 * @author jeus (suje@protonmail.com)
 * @since 3/4/20
 */
package com.hosp.occupancy.acceptancetest.step;

import com.hosp.occupancy.common.exception.ExceptionDictionary;
import com.hosp.occupancy.common.exception.PublicException;
import com.hosp.occupancy.core.Occupancy;
import com.hosp.occupancy.model.dto.CustomerPotentialDto;
import com.hosp.occupancy.model.dto.FreeRoomDto;
import com.hosp.occupancy.model.dto.HotelStateDto;
import com.hosp.occupancy.rest.CustomerController;
import com.hosp.occupancy.rest.RoomController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Map;

@SpringBootTest
public class PotentialCustomer {

    @Autowired
    CustomerController customerController;

    @Autowired
    RoomController roomController;

    @Autowired
    Occupancy occupancy;


    @Given("add potential customers {string}")
    public void addPotentialCustomers(String potential) {
        System.out.println("[POT]-{" + potential + "}");
        var customerPotentialDto = customerPotentialMapper(potential);
        customerController.addPotential(customerPotentialDto);
    }

    @When("add free room {int} {int}")
    public void addFreeRoomCountEconomyCountPremium(int CountEconomy, int CountPremium) {
        System.out.println("[ECO]-{" + CountEconomy + "}---{" + CountPremium + "}-[PRM]");
        var freeRoomDto = freeRoomMapper(CountEconomy, CountPremium);
        roomController.addRooms(freeRoomDto);
    }

    @Then("calculate {long} {long} {long} {long}")
    public void calculateCountFreeEconomyCountFreePremiumEconomyIncomePremiumIncome(long countFreeEconomy, long countFreePremium, long economyIncome, long premiumIncome) {

        HotelStateDto hotelStateDto = occupancy.calculateFromScrach();
        Assert.assertEquals("countFreeEconomy:",countFreeEconomy,hotelStateDto.getCountFreeEconomy());
        Assert.assertEquals("economyIncome:",economyIncome,hotelStateDto.getEconomyIncome());

        Assert.assertEquals("countFreePremium:",countFreePremium,hotelStateDto.getCountFreePremium());
        Assert.assertEquals("premiumIncome:",premiumIncome,hotelStateDto.getPremiumIncome());

    }


    public FreeRoomDto freeRoomMapper(int countEconomy, int countPremium) {
        var row = new FreeRoomDto();
        row.setCountEconomy(countEconomy);
        row.setCountPremium(countPremium);
        return row;
    }

    public HotelStateDto otelStateMapper(Map<String, String> entry) {
        HotelStateDto row = new HotelStateDto();

        row.setCountEconomy(Long.parseLong(entry.get("countEconomy")));
        row.setCountFreeEconomy(Long.parseLong(entry.get("countFreeEconomy")));
        row.setEconomyIncome(Long.parseLong(entry.get("economyIncome")));

        row.setCountPremium(Long.parseLong(entry.get("countPremium")));
        row.setCountFreePremium(Long.parseLong(entry.get("countFreePremium")));
        row.setPremiumIncome(Long.parseLong(entry.get("premiumIncome")));
        return row;
    }

    public CustomerPotentialDto customerPotentialMapper(String potential) {
        if (potential.isEmpty())
            return null;
        CustomerPotentialDto row = new CustomerPotentialDto();
        String[] items = potential.replaceAll("\\[", "")
                .replaceAll("\\s", "").split(",");

        Integer[] results = new Integer[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {
                throw new PublicException(ExceptionDictionary.NUMBERISNOTVALID, "transform is fail");
            }
        }
        row.setPotential(Arrays.asList(results));
        return row;
    }

}

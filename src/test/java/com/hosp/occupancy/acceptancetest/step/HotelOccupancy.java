/*
 * @author jeus (suje@protonmail.com)
 * @since 3/4/20
 */
package com.hosp.occupancy.acceptancetest.step;

import com.hosp.occupancy.common.exception.ExceptionDictionary;
import com.hosp.occupancy.common.exception.PublicException;
import com.hosp.occupancy.core.Occupancy;
import com.hosp.occupancy.pojo.dto.customer.CustomerPotentialDto;
import com.hosp.occupancy.pojo.dto.room.FreeRoomDto;
import com.hosp.occupancy.rest.CustomerController;
import com.hosp.occupancy.rest.RoomController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class HotelOccupancy {

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

        var hotelState = occupancy.bookFromScratch();
        Assert.assertEquals("countFreeEconomy:",countFreeEconomy,hotelState.getCountFreeEconomy());
        Assert.assertEquals("countFreeEconomy:",countFreeEconomy,roomController.getCountFreeEconomy());
        Assert.assertEquals("economyIncome:",economyIncome,hotelState.getEconomyIncome());
        Assert.assertEquals("countFreePremium:",countFreePremium,hotelState.getCountFreePremium());
        Assert.assertEquals("countFreePremium:",countFreePremium,roomController.getCountFreePremium());
        Assert.assertEquals("premiumIncome:",premiumIncome,hotelState.getPremiumIncome());

    }


    public FreeRoomDto freeRoomMapper(int countEconomy, int countPremium) {
        return new FreeRoomDto(countEconomy,countPremium);
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

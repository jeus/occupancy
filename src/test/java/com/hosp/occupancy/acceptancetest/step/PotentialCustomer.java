/*
 * @author jeus (suje@protonmail.com)
 * @since 3/4/20
 */
package com.hosp.occupancy.acceptancetest.step;

import com.hosp.occupancy.common.exception.ExceptionDictionary;
import com.hosp.occupancy.common.exception.PublicException;
import com.hosp.occupancy.model.dto.CustomerPotentialDto;
import com.hosp.occupancy.model.dto.FreeRoomDto;
import com.hosp.occupancy.model.dto.HotelStateDto;
import com.hosp.occupancy.rest.CustomerController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;


public class PotentialCustomer {

    @Autowired
    CustomerController customerController;


    @Given("add potential customers {string}")
    public void addPotentialCustomers(String potentials) {
        System.out.println(potentials);
        var customerPotentialDto = customerPotentialMapper(potentials);
//        customerController.addPotential(customerPotentialDto);
    }

    @When("add free room {int} {int}")
    public void addFreeRoomCountEconomyCountPremium(int CountEconomy, int CountPremium) {
        var freeRoomDto = freeRoomMapper(CountEconomy,CountPremium);
    }

    @Then("calculate {long} {long} {long} {long}")
    public void calculateCountFreeEconomyCountFreePremiumEconomyIncomePremiumIncome(long CountFreeEconomy, long CountFreePremium, long EconomyIncome, long PremiumIncome) {

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

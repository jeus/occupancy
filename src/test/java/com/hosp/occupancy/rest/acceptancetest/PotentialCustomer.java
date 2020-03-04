/*
 * @author jeus (suje@protonmail.com)
 * @since 3/4/20
 */
package com.hosp.occupancy.rest.acceptancetest;

import com.hosp.occupancy.model.dto.CustomerPotentialDto;
import com.hosp.occupancy.model.dto.FreeRoomDto;
import com.hosp.occupancy.model.dto.HotelStateDto;
import com.hosp.occupancy.rest.CustomerController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;


public class PotentialCustomer {

    @Autowired
    CustomerController customerController;

    @Given("add potential customers list {int}")
    public void addPotentialCustomersList(CustomerPotentialDto potentials) {
        System.out.println(potentials.toString());
        customerController.addPotential(potentials);
    }

    @When("add free room {FreeRoomDto}")
    public void addFreeRoom(FreeRoomDto freeRoom) {
        System.out.println(freeRoom.toString());
    }

    @Then("calculate {int} {int} {int} {int}")
    public void calculate(HotelStateDto hotelStateDto) {
        System.out.println(hotelStateDto.toString());
    }


    @When("add free room <countEconomy> <countPremium>")
    public void addFreeRoomCountEconomyCountPremium() {
    }
}

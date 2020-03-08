/*
 * @author jeus (suje@protonmail.com)
 * @since 3/3/20
 */

package com.hosp.occupancy.rest;

import com.hosp.occupancy.pojo.dto.customer.CustomerPotentialDto;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/hotel/customer")
@Data
public class CustomerController {
    private List<Integer> potential = new ArrayList<>();
    private Date lastUpdate;

    @PostMapping
    public List<Integer> addPotential(CustomerPotentialDto customerPotentialDto) {
        if (customerPotentialDto == null)
            return null;
        potential = customerPotentialDto.getPotential();
        lastUpdate = new Date();
        return potential;
    }

    @GetMapping
    public List<Integer> potentials() {
        return potential;
    }


    public Date lastUpdate() {
        return lastUpdate;
    }

}

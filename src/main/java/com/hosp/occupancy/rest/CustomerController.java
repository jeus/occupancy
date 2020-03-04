/*
 * @author jeus (suje@protonmail.com)
 * @since 3/3/20
 */

package com.hosp.occupancy.rest;

import com.hosp.occupancy.model.dto.CustomerPotentialDto;
import com.hosp.occupancy.model.dto.RoomDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {
List<Integer> potential = new ArrayList<>();

    @PostMapping
    public List<Integer> addPotential(CustomerPotentialDto customerPotentialDto) {
        potential.clear();
        potential = customerPotentialDto.getPotential();
        return potential;
    }


}

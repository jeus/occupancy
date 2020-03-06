/*
 * @author jeus (suje@protonmail.com)
 * @since 3/3/20
 */

package com.hosp.occupancy.rest;

import com.hosp.occupancy.model.dto.CustomerPotentialDto;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/customer")
@Data
public class CustomerController {
    List<Integer> potential = new ArrayList<>();

    @PostMapping
    public List<Integer> addPotential(CustomerPotentialDto customerPotentialDto) {
        if (customerPotentialDto == null)
            return null;
        potential = customerPotentialDto.getPotential();
        return potential;
    }


}

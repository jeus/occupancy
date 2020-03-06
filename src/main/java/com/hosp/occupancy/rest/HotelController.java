/*
 * @author jeus (suje@protonmail.com)
 * @since 3/6/20
 */

package com.hosp.occupancy.rest;

import com.hosp.occupancy.core.Occupancy;
import com.hosp.occupancy.model.dto.HotelStateDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    HotelStateDto hotelStateDto = new HotelStateDto(); //TODO: have to change to model and use from mapper.

    final Occupancy occupancy;

    public HotelController(Occupancy occupancy) {
        this.occupancy = occupancy;
    }

    @GetMapping("/status")
    public HotelStateDto getHotelState() {
        return hotelStateDto;
    }

    @GetMapping("/calculate")
    public HotelStateDto calculateFromScratch() {
        return occupancy.calculateFromScrach();
    }

}

/*
 * @author jeus (suje@protonmail.com)
 * @since 3/6/20
 */
package com.hosp.occupancy.rest;

import com.hosp.occupancy.common.translator.HotelMapper;
import com.hosp.occupancy.core.Occupancy;
import com.hosp.occupancy.pojo.dto.hotel.HotelStateDto;
import com.hosp.occupancy.pojo.model.hotel.HotelState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    HotelState hotelState = new HotelState();

    final Occupancy occupancy;
    final HotelMapper hotelMapper;

    public HotelController(Occupancy occupancy,HotelMapper hotelMapper) {
        this.occupancy = occupancy;
        this.hotelMapper = hotelMapper;
    }

    @GetMapping("/status")
    public HotelStateDto getHotelState() {
        return hotelMapper.toHotelStateDto(hotelState);
    }

    @GetMapping("/book")
    public HotelStateDto book() {
        return hotelMapper.toHotelStateDto(occupancy.bookFromScrach());
    }

}

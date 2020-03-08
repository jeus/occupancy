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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    HotelState hotelState = new HotelState();
    private Date lastUpdate;

    final Occupancy occupancy;
    final HotelMapper hotelMapper;
    final CustomerController customerController;
    final RoomController roomController;

    public HotelController(Occupancy occupancy, HotelMapper hotelMapper, CustomerController customerController, RoomController roomController) {
        this.occupancy = occupancy;
        this.hotelMapper = hotelMapper;
        this.customerController = customerController;
        this.roomController = roomController;
    }

    @GetMapping("/status")
    public HotelStateDto getHotelState() {
        hotelState.setDescription(lastUpdateCheck());
        return hotelMapper.toHotelStateDto(hotelState);
    }

    @PostMapping("/book")
    public HotelStateDto book() {
        hotelState = occupancy.bookFromScratch();
        hotelState.setDescription("last update");
        lastUpdate = new Date();
        return hotelMapper.toHotelStateDto(hotelState);
    }

    private String lastUpdateCheck() {
        if(lastUpdate == null)
            return "Please book";
        var resource = "";
        if (customerController.getLastUpdate().after(lastUpdate))
            resource = "[Potential]";
        if (roomController.getLastUpdate().after(lastUpdate))
            resource = resource + "[Room]";
        if (!resource.isEmpty())
            return "Hotel Resources changed " + resource;
        else
            return "Last update";
    }
}

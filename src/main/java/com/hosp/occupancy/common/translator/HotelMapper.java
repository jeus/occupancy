/*
 * @author jeus (suje@protonmail.com)
 * @since 3/6/20
 */


package com.hosp.occupancy.common.translator;

import com.hosp.occupancy.pojo.dto.hotel.HotelStateDto;
import com.hosp.occupancy.pojo.model.hotel.HotelState;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    
    HotelStateDto toHotelStateDto(HotelState hotelState);

}

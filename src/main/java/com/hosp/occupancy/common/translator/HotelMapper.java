/*
 * @author jeus (suje@protonmail.com)
 * @since 3/6/20
 */


package com.hosp.occupancy.common.translator;

import com.hosp.occupancy.pojo.dto.hotel.EfficiencyDto;
import com.hosp.occupancy.pojo.dto.hotel.HotelStateDto;
import com.hosp.occupancy.pojo.dto.room.HotelDto;
import com.hosp.occupancy.pojo.dto.room.RoomDto;
import com.hosp.occupancy.pojo.model.hotel.Efficiency;
import com.hosp.occupancy.pojo.model.hotel.HotelState;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    
    HotelStateDto toHotelStateDto(HotelState hotelState);

    EfficiencyDto toEfficiencyDto (Efficiency efficiency);

    Efficiency toEfficiencyD (EfficiencyDto efficiency);

    @Named("toRoomDto")
    default HotelDto toHotelDto(int countEconomy, int countPremium, int countFreeEconomy, int countFreePremium,
                                List<RoomDto> roomDtos) {
        var hotelDto = new HotelDto();
        hotelDto.setCountEconomy(countEconomy);
        hotelDto.setCountPremium(countPremium);
        hotelDto.setCountFreeEconomy(countFreeEconomy);
        hotelDto.setCountFreePremium(countFreePremium);
        hotelDto.setRooms(roomDtos);
        return hotelDto;
    }
}

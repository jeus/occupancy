/*
 * @author jeus (suje@protonmail.com)
 * @since 3/6/20
 */
package com.hosp.occupancy.common.translator;

import com.hosp.occupancy.common.enums.RoomType;
import com.hosp.occupancy.pojo.dto.room.RoomDto;
import com.hosp.occupancy.pojo.model.room.RoomAbstract;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {


    @Named("toRoomDto")
    default RoomDto toRoomDto(List<RoomAbstract> rooms) {
        var roomDto = new RoomDto();
        roomDto.setCountEconomy((int) rooms.stream().filter(r -> r.getRoomType() == RoomType.ECONOMY).count());
        roomDto.setCountPremium((int) rooms.stream().filter(r -> r.getRoomType() == RoomType.PREMIUM).count());
        roomDto.setCountFreeEconomy((int) rooms.stream().filter(r -> r.isFree() && r.getRoomType() == RoomType.ECONOMY).count());
        roomDto.setCountFreePremium((int) rooms.stream().filter(r -> r.isFree() && r.getRoomType() == RoomType.PREMIUM).count());
        roomDto.setRooms(rooms);
        return roomDto;
    }
}

/*
 * @author jeus (suje@protonmail.com)
 * @since 3/6/20
 */
package com.hosp.occupancy.common.translator;

import com.hosp.occupancy.pojo.dto.room.RoomDto;
import com.hosp.occupancy.pojo.model.room.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Named("toRoom")
    Room toRoom(RoomDto roomDto);

    @Named("toRooms")
    List<Room> toRooms(List<RoomDto> roomDto);

    @Named("toRoonDto")
    RoomDto toRoomDto(Room room);


    @Named("toRoomDtos")
    List<RoomDto> toRoomDtos(List<Room> rooms);
}

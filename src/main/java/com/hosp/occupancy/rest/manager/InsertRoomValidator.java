/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */
package com.hosp.occupancy.rest.manager;

import com.hosp.occupancy.common.helper.AssertHelper;
import com.hosp.occupancy.pojo.model.room.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsertRoomValidator {

    private final AssertHelper assertHelper;

    public InsertRoomValidator(AssertHelper assertHelper) {
        this.assertHelper = assertHelper;
    }

    public void validateRoomForInsert(Room newRoom, List<Room> rooms) {
        assertHelper.notNull(newRoom.getNumber());
        for (Room room : rooms)
            assertHelper.isNotEqual(newRoom.getNumber(), room.getNumber());
    }
}

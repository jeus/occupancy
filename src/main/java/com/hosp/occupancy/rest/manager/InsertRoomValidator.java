/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */
package com.hosp.occupancy.rest.manager;

import com.hosp.occupancy.common.helper.AssertHelper;
import com.hosp.occupancy.model.room.RoomAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsertRoomValidator {

    private final AssertHelper assertHelper;

    public InsertRoomValidator(AssertHelper assertHelper) {
        this.assertHelper = assertHelper;
    }

    public void validateRoomForInsert(RoomAbstract newRoom, List<RoomAbstract> rooms) {
        assertHelper.notNull(newRoom.getNumber());
        for (RoomAbstract roomAbstract : rooms)
            assertHelper.isNotEqual(newRoom.getNumber(), roomAbstract.getNumber());
    }
}

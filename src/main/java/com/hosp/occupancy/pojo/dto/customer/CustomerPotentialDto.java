/*
 * @author jeus (suje@protonmail.com)
 * @since 3/3/20
 */

package com.hosp.occupancy.pojo.dto.customer;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerPotentialDto {
    private List<Integer> potential = new ArrayList<>();
}

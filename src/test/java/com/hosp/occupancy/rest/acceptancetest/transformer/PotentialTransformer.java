/*
 * @author jeus (suje@protonmail.com)
 * @since 3/4/20
 */
package com.hosp.occupancy.rest.acceptancetest.transformer;

import com.hosp.occupancy.common.exception.ExceptionDictionary;
import com.hosp.occupancy.common.exception.PublicException;
import com.hosp.occupancy.model.dto.CustomerPotentialDto;
import io.cucumber.datatable.TableEntryTransformer;

import java.util.Arrays;
import java.util.Map;

public class PotentialTransformer implements TableEntryTransformer<CustomerPotentialDto> {
    @Override
    public CustomerPotentialDto transform(Map<String, String> entry) {
        CustomerPotentialDto row = new CustomerPotentialDto();
        String[] items = entry.get("potentials").replaceAll("\\[", "")
                .replaceAll("\\]", "").replaceAll("\\s", "").split(",");

        Integer[] results = new Integer[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {
              throw new PublicException(ExceptionDictionary.NUMBERISNOTVALID,"transform is fail");
            }
        }
        row.setPotential(Arrays.asList(results));
        return row;
    }
}

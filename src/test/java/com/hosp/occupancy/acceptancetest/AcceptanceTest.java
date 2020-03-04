/*
 * @author jeus (suje@protonmail.com)
 * @since 3/4/20
 */

package com.hosp.occupancy.acceptancetest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty"}
)
public class AcceptanceTest {
}
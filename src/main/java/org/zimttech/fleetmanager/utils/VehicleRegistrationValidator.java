package org.zimttech.fleetmanager.utils;

import java.util.regex.Pattern;

public class VehicleRegistrationValidator {

    public static boolean isVehicleRegistrationValid(String registrationNumber){
        return Pattern.matches("[A-Z]{3}-[0-9]{4}", registrationNumber);
    }
}

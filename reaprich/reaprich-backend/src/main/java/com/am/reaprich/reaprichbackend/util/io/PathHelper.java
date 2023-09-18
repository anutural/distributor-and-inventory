package com.am.reaprich.reaprichbackend.util.io;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathHelper {
    private static final String dataLocation = "Data";
    private static final String kycDataExtLocation = "KYC";

    public static Path GetKYCDataLocation() {
        return Paths.get(dataLocation, kycDataExtLocation);
    }
    public static Path GetKYCDataLocationForActor(String actorID) {
        return PathHelper.GetKYCDataLocation().resolve(actorID);
    }
}

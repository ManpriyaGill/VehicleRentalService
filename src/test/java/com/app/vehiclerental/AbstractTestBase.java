package com.app.vehiclerental;

import org.junit.jupiter.api.AfterEach;

public abstract class AbstractTestBase {
    @AfterEach
    void cleanUp() {
        VehicleRentalDriver.resetSingleTons();
    }
}

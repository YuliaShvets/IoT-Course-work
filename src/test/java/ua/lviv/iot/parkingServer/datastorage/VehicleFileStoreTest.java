package ua.lviv.iot.parkingServer.datastorage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.parkingServer.model.Vehicle;
import ua.lviv.iot.parkingServer.model.enums.VehicleType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class VehicleFileStoreTest {

    @BeforeEach
    void setUp() throws IOException {
        VehicleFileStore vehicleFileStore = new VehicleFileStore();
        List<Vehicle> vehicle = new ArrayList<>();
        Vehicle vehicle1 = new Vehicle(1L, "KC8547KN", VehicleType.CAR, 6.0, true);
        Vehicle vehicle2 = new Vehicle(1L, "AK7856MH", VehicleType.MOTORCYCLE, 4.0, false);
        vehicle.add(vehicle1);
        vehicle.add(vehicle2);
        vehicleFileStore.saveRecords(vehicle);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveRecords() throws IOException {
        File generatedData = new File(("src/test/resources/vehicle-test.csv"));
        File expectedData = new File("src/main/resources/vehicle-2022-06-16.csv");
        try (
                BufferedReader expectedReader = new BufferedReader(new FileReader(generatedData));
                BufferedReader actualReader = new BufferedReader(new FileReader(expectedData))) {

            List<String> expected = new ArrayList<>();
            List<String> result = new ArrayList<>();
            String expectedLine = null;
            String actualLine = null;
            while ((expectedLine = expectedReader.readLine()) != null) {
                expected.add(expectedLine);
            }
            while ((actualLine = actualReader.readLine()) != null) {
                result.add(actualLine);
            }
            Assertions.assertLinesMatch(expected, result);
        }
    }
}
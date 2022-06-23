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
    void saveVehicleData() throws IOException {
        File file1 = new File(("src/test/resources/vehicle-test.csv"));
        File file2 = new File("src/main/resources/vehicle-2022-06-16.csv");
        BufferedReader br1 = new BufferedReader(new FileReader(file1));
        BufferedReader br2 = new BufferedReader(new FileReader(file2));
        List<String> data1 = new ArrayList<>();
        List<String> data2 = new ArrayList<>();
        String line1 = null;
        String line2 = null;
        while ((line1 = br1.readLine()) != null) {
            data1.add(line1);
        }
        while ((line2 = br2.readLine()) != null) {
            data2.add(line2);
        }
        Assertions.assertLinesMatch(data1, data2);
    }
}
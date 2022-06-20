package ua.lviv.iot.parkingServer.datastorage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.parkingServer.model.Vehicle;
import ua.lviv.iot.parkingServer.model.enums.VehicleType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        vehicleFileStore.saveVehicleData(vehicle);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveVehicleData() throws IOException {
        Path expected = Paths.get("src/test/resources/vehicle-test.csv");
        Path actual = Paths.get("src/main/resources/vehicle-2022-06-16.csv");
        byte[] file1 = Files.readAllBytes(expected);
        byte[] file2 = Files.readAllBytes(actual);
        Assertions.assertArrayEquals(file1, file2);
    }
}
package ua.lviv.iot.parkingServer.datastorage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.parkingServer.model.Vehicle;
import ua.lviv.iot.parkingServer.model.enums.VehicleType;

import java.io.BufferedReader;
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
        Vehicle vehicle2 = new Vehicle(1L,"AK7856MH", VehicleType.MOTORCYCLE, 4.0, false );
        vehicle.add(vehicle1);
        vehicle.add(vehicle2);
        vehicleFileStore.saveVehicleData(vehicle);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveVehicleData() throws IOException{
        try (FileReader expectedReader = new FileReader("src/test/resources/vehicle-test.csv");
             BufferedReader expectedBR = new BufferedReader(expectedReader);
             FileReader actualReader = new FileReader("src/main/java/ua/lviv/iot/ParkingServer/files/vehicle-2022-06-16.csv");
             BufferedReader actualBR = new BufferedReader(actualReader)) {
            String line1 = expectedBR.readLine();
            String line2 = actualBR.readLine();
            Assertions.assertEquals(line1, line2);

        }
    }
}
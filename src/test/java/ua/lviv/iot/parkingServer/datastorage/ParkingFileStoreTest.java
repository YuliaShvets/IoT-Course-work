package ua.lviv.iot.parkingServer.datastorage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.parkingServer.model.Parking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ParkingFileStoreTest {

    @BeforeEach
    void setUp() throws IOException {
        ParkingFileStore parkingFileStore = new ParkingFileStore();
        List<Parking> parking = new ArrayList<>();
        Parking parking1 = new Parking(1L, "Ternopil", "Atrium", 120);
        Parking parking2 = new Parking(2L, "Kyiv", "Retrovile", 260);
        parking.add(parking1);
        parking.add(parking2);
        parkingFileStore.saveParkingData(parking);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveParkingData() throws IOException {
        try (FileReader expectedReader = new FileReader("src/test/resources/parking-test.csv");
             BufferedReader expectedBR = new BufferedReader(expectedReader);
             FileReader actualReader = new FileReader("src/main/java/ua/lviv/iot/ParkingServer/files/parking-2022-06-16.csv");
             BufferedReader actualBR = new BufferedReader(actualReader)) {
            String line1 = expectedBR.readLine();
            String line2 = actualBR.readLine();
            Assertions.assertEquals(line1, line2);

        }
    }
}
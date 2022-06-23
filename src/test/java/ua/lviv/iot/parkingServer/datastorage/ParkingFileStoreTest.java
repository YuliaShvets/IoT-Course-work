package ua.lviv.iot.parkingServer.datastorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.parkingServer.model.Parking;

import java.io.BufferedReader;
import java.io.File;
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
        parkingFileStore.saveRecords(parking);

    }

    @Test
    void saveParkingData() throws IOException {
        File generatedData = new File(("src/test/resources/parking-test.csv"));
        File expectedData = new File("src/main/resources/parking-2022-06-16.csv");
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
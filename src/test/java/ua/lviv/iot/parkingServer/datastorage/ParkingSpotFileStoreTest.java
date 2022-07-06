package ua.lviv.iot.parkingServer.datastorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.parkingServer.model.ParkingSpot;
import ua.lviv.iot.parkingServer.model.enums.ParkingSpotSize;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ParkingSpotFileStoreTest {

    @BeforeEach
    void setUp() throws IOException {
        ParkingSpotFileStore parkingSpotFileStore = new ParkingSpotFileStore();
        List<ParkingSpot> parkingSpotList = new ArrayList<>();
        ParkingSpot parkingSpot = new ParkingSpot(1L, true, ParkingSpotSize.MOTORBIKE);
        parkingSpotList.add(parkingSpot);
        parkingSpotFileStore.saveRecords(parkingSpotList);
    }


    @Test
    void saveRecords() throws IOException {
        File generatedData = new File(("src/test/resources/parkingSpot-test.csv"));
        File expectedData = new File("src/main/resources/parkingSpot-2022-06-16.csv");
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
package ua.lviv.iot.parkingServer.datastorage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.parkingServer.model.ParkingSpot;
import ua.lviv.iot.parkingServer.model.enums.ParkingSpotSize;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ParkingSpotFileStoreTest {

    @BeforeEach
    void setUp() throws IOException {
        ParkingSpotFileStore parkingSpotFileStore = new ParkingSpotFileStore();
        List<ParkingSpot> parkingSpotList = new ArrayList<>();
        ParkingSpot parkingSpot = new ParkingSpot(1L, true, ParkingSpotSize.MOTORBIKE );
        parkingSpotList.add(parkingSpot);
        parkingSpotFileStore.saveParkingSpotData(parkingSpotList);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveParkingSpotData() throws IOException{
        try (FileReader expectedReader = new FileReader("src/test/resources/parkingSpot-test.csv");
             BufferedReader expectedBR = new BufferedReader(expectedReader);
             FileReader actualReader = new FileReader("src/main/resources/parkingSpot-2022-06-16.csv");
             BufferedReader actualBR = new BufferedReader(actualReader)) {
            String line1 = expectedBR.readLine();
            String line2 = actualBR.readLine();
            Assertions.assertEquals(line1, line2);

        }
    }
}
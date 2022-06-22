package ua.lviv.iot.parkingServer.datastorage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.parkingServer.model.ParkingSpot;
import ua.lviv.iot.parkingServer.model.enums.ParkingSpotSize;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class ParkingSpotFileStoreTest {

    @BeforeEach
    void setUp() throws IOException {
        ParkingSpotFileStore parkingSpotFileStore = new ParkingSpotFileStore();
        List<ParkingSpot> parkingSpotList = new ArrayList<>();
        ParkingSpot parkingSpot = new ParkingSpot(1L, true, ParkingSpotSize.MOTORBIKE);
        parkingSpotList.add(parkingSpot);
        parkingSpotFileStore.saveParkingSpotData(parkingSpotList);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveParkingSpotData() throws IOException {
        File file1 = new File(("src/test/resources/parkingSpot-test.csv"));
        File file2 = new File("src/main/resources/parkingSpot-2022-06-16.csv");
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
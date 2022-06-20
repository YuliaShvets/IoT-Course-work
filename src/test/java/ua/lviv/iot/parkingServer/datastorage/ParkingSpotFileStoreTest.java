package ua.lviv.iot.parkingServer.datastorage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.parkingServer.model.ParkingSpot;
import ua.lviv.iot.parkingServer.model.enums.ParkingSpotSize;

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
        Path expected = Paths.get("src/test/resources/parkingSpot-test.csv");
        Path actual = Paths.get("src/main/resources/parkingSpot-2022-06-16.csv");
        byte[] file1 = Files.readAllBytes(expected);
        byte[] file2 = Files.readAllBytes(actual);
        Assertions.assertArrayEquals(file1, file2);
    }
}
package ua.lviv.iot.parkingServer.datastorage;

import org.junit.jupiter.api.AfterEach;
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
        parkingFileStore.saveParkingData(parking);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveParkingData() throws IOException {
        File file1 = new File(("src/test/resources/parking-test.csv"));
        File file2 = new File("src/main/resources/parking-2022-06-16.csv");
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
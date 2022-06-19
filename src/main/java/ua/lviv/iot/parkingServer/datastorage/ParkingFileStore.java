package ua.lviv.iot.parkingServer.datastorage;

import org.springframework.stereotype.Component;
import ua.lviv.iot.parkingServer.Date;
import ua.lviv.iot.parkingServer.model.Parking;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Component
public class ParkingFileStore {
    public List<Parking> pushParking() throws IOException {
        List<Parking> output = new LinkedList<>();
        File CSV;
        String year = Integer.toString(LocalDate.now().getYear());
        String month;

        if (LocalDate.now().getMonthValue() < 10) {
            month = "0" + LocalDate.now().getMonthValue();
        } else {
            month = Integer.toString(LocalDate.now().getMonthValue());
        }

        for (int i = 1; i <= LocalDate.now().getDayOfMonth(); i++) {
            if (i < 10) {
                if (Files.exists(Paths.get("src/main/java/ua/lviv/iot/ParkingServer/files/parking-" + year + "-" + month + "-0" + i + ".csv"))) {
                    CSV = new File("src/main/java/ua/lviv/iot/ParkingServer/files/parking-" + year + "-" + month + "-0" + i + ".csv");
                    output.addAll(checkParking(CSV));
                }
            } else {
                if (Files.exists(Paths.get("src/main/java/ua/lviv/iot/ParkingServer/files/parking-" + year + "-" + month + "-" + i + ".csv"))) {
                    CSV = new File("src/main/java/ua/lviv/iot/ParkingServer/files/parking-" + year + "-" + month + "-" + i + ".csv");
                    output.addAll(checkParking(CSV));
                }
            }
        }
        return output;
    }

    private List<Parking> checkParking(File CSV) throws IOException {
        List<Parking> result = new LinkedList<>();
        boolean isFirst = true;
        Scanner scanner = new Scanner(CSV, StandardCharsets.UTF_8);
        while (scanner.hasNextLine()) {
            if (!isFirst) {
                List<String> values = Arrays.stream(scanner.nextLine().split(", ")).toList();
                Parking parking = new Parking();
                int index = 0;
                for (String value : values) {
                    switch (index) {
                        case 0:
                            parking.setParkingId(Long.parseLong(value));
                            break;
                        case 1:
                            parking.setLocation(value);
                            break;
                        case 2:
                            parking.setTradeNetwork(value);
                            break;
                        case 3:
                            parking.setCountOfParkingSpot(Integer.parseInt(value));
                            break;
                    }
                    index++;
                }
                result.add(parking);
            } else {
                scanner.nextLine();
                isFirst = false;
            }
        }
        return result;
    }


    public void saveParkingData(List<Parking> parkings) throws IOException {
        String date = Date.getTimeNow();

        File file = new File("src/main/java/ua/lviv/iot/ParkingServer/files/parking-" + date + ".csv");
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
            writer.write(parkings.get(0).getHeaders() + "\n");
            for (Parking parking : parkings) {
                writer.write(parking.toCSV() + "\n");
            }


        }

    }

}




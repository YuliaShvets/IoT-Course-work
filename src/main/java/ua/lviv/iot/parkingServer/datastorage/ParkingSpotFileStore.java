package ua.lviv.iot.parkingServer.datastorage;

import org.springframework.stereotype.Component;
import ua.lviv.iot.parkingServer.utils.DateToday;
import ua.lviv.iot.parkingServer.model.ParkingSpot;
import ua.lviv.iot.parkingServer.model.enums.ParkingSpotSize;

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
public class ParkingSpotFileStore {

    public List<ParkingSpot> pushParkingSpot() throws IOException {
        List<ParkingSpot> output = new LinkedList<>();
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
                if (Files.exists(Paths.get("src/main/resources/parkingSpot-" + year + "-" + month + "-0" + i + ".csv"))) {
                    CSV = new File("src/main/resources/parkingSpot-" + year + "-" + month + "-0" + i + ".csv");
                    output.addAll(checkParkingSpot(CSV));
                }
            } else {
                if (Files.exists(Paths.get("src/main/resources/parkingSpot-" + year + "-" + month + "-" + i + ".csv"))) {
                    CSV = new File("src/main/resources/parkingSpot-" + year + "-" + month + "-" + i + ".csv");
                    output.addAll(checkParkingSpot(CSV));
                }
            }
        }
        return output;
    }

    private List<ParkingSpot> checkParkingSpot(File CSV) throws IOException {
        List<ParkingSpot> result = new LinkedList<>();
        boolean isFirst = true;
        Scanner scanner = new Scanner(CSV, StandardCharsets.UTF_8);
        while (scanner.hasNextLine()) {
            if (!isFirst) {
                List<String> values = Arrays.stream(scanner.nextLine().split(", ")).toList();
                ParkingSpot parkingSpot = new ParkingSpot();
                int index = 0;
                for (String value : values) {
                    if (index == 0) {
                        parkingSpot.setParkingSpotId(Long.parseLong(value));
                    } else if (index == 1) {
                        parkingSpot.setAvailable(Boolean.parseBoolean(value));
                    } else if (index == 2) {
                        parkingSpot.setSize(ParkingSpotSize.valueOf(value));
                    }
                    index++;
                }
                result.add(parkingSpot);
            } else {
                scanner.nextLine();
                isFirst = false;
            }
        }
        return result;
    }


    public void saveParkingSpotData(List<ParkingSpot> parkingSpots) throws IOException {
        String date = DateToday.getDateToday();

        File file = new File("src/main/resources/parkingSpot-" + date + ".csv");
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");) {
            writer.write(parkingSpots.get(0).getHeaders() + "\n");
            for (ParkingSpot parkingSpot : parkingSpots) {
                writer.write(parkingSpot.toCSV() + "\n");
            }


        }

    }

}


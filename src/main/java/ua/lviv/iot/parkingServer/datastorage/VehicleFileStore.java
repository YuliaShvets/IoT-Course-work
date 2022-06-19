package ua.lviv.iot.parkingServer.datastorage;

import org.springframework.stereotype.Component;
import ua.lviv.iot.parkingServer.Date;
import ua.lviv.iot.parkingServer.model.Vehicle;
import ua.lviv.iot.parkingServer.model.enums.VehicleType;

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
public class VehicleFileStore {
    public List<Vehicle> pushVehicle() throws IOException {
        List<Vehicle> output = new LinkedList<>();
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
                if (Files.exists(Paths.get("src/main/java/ua/lviv/iot/ParkingServer/files/vehicle-" + year + "-" + month + "-0" + i + ".csv"))) {
                    CSV = new File("src/main/java/ua/lviv/iot/ParkingServer/files/vehicle-" + year + "-" + month + "-0" + i + ".csv");
                    output.addAll(checkVehicle(CSV));
                }
            } else {
                if (Files.exists(Paths.get("src/main/java/ua/lviv/iot/ParkingServer/files/vehicle-" + year + "-" + month + "-" + i + ".csv"))) {
                    CSV = new File("src/main/java/ua/lviv/iot/ParkingServer/files/vehicle-" + year + "-" + month + "-" + i + ".csv");
                    output.addAll(checkVehicle(CSV));
                }
            }
        }
        return output;
    }

    private List<Vehicle> checkVehicle(File CSV) throws IOException {
        List<Vehicle> result = new LinkedList<>();
        boolean isFirst = true;
        Scanner scanner = new Scanner(CSV, StandardCharsets.UTF_8);
        while (scanner.hasNextLine()) {
            if (!isFirst) {
                List<String> values = Arrays.stream(scanner.nextLine().split(", ")).toList();
                Vehicle vehicle = new Vehicle();
                int index = 0;
                for (String value : values) {
                    switch (index) {
                        case 0:
                            vehicle.setVehicleId(Long.parseLong(value));
                            break;
                        case 1:
                            vehicle.setNumber(value);
                            break;
                        case 2:
                            vehicle.setTypeOfVehicle(VehicleType.valueOf(value));
                            break;
                        case 3:
                            vehicle.setDurationOfUseOfParkingSpot(Double.parseDouble(value));
                            break;
                    }
                    index++;
                }
                result.add(vehicle);
            } else {
                scanner.nextLine();
                isFirst = false;
            }
        }
        return result;
    }


    public void saveVehicleData(List<Vehicle> vehicles) throws IOException {
        String date = Date.getTimeNow();

        File file = new File("src/main/java/ua/lviv/iot/ParkingServer/files/vehicle-" + date + ".csv");
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");) {
            writer.write(vehicles.get(0).getHeaders() + "\n");
            for (Vehicle vehicle : vehicles) {
                writer.write(vehicle.toCSV() + "\n");
            }

        }

    }


}

package ua.lviv.iot.parkingServer.datastorage;

import org.springframework.stereotype.Component;
import ua.lviv.iot.parkingServer.model.Parking;

@Component
public class ParkingFileStore extends AbstractFIleStore<Parking> {


    @Override
    protected String getRecordName() {
        return "parking";
    }

    @Override
    protected Parking convert(String[] values) {
        return new Parking(Long.parseLong(values[0]), values[1], values[2], Integer.parseInt(values[3]));
    }
}
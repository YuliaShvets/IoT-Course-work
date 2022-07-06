package ua.lviv.iot.parkingServer.datastorage;

import org.springframework.stereotype.Component;
import ua.lviv.iot.parkingServer.model.ParkingSpot;
import ua.lviv.iot.parkingServer.model.enums.ParkingSpotSize;

@Component
public class ParkingSpotFileStore extends AbstractFIleStore<ParkingSpot> {


    @Override
    protected String getRecordName() {
        return "parkingSpot";
    }

    @Override
    protected ParkingSpot convert(String[] values) {
        return new ParkingSpot(Long.parseLong(values[0]), Boolean.parseBoolean(values[1]), ParkingSpotSize.valueOf(values[2]));
    }
}


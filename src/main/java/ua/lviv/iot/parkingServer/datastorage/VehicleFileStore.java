package ua.lviv.iot.parkingServer.datastorage;

import org.springframework.stereotype.Component;
import ua.lviv.iot.parkingServer.model.Vehicle;
import ua.lviv.iot.parkingServer.model.enums.VehicleType;

@Component
public class VehicleFileStore extends AbstractFIleStore<Vehicle> {

    @Override
    protected String getRecordName() {
        return "vehicle";
    }

    @Override
    protected Vehicle convert(String[] values) {
        return new Vehicle(Long.parseLong(values[0]), values[1], VehicleType.valueOf(values[2]), Double.parseDouble(values[3]), Boolean.parseBoolean(values[4]));
    }
}

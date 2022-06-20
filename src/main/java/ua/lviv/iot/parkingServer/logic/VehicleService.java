package ua.lviv.iot.parkingServer.logic;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.parkingServer.datastorage.VehicleFileStore;
import ua.lviv.iot.parkingServer.model.Vehicle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Service
public class VehicleService {
    private Map<Long, Vehicle> vehicles = new HashMap<>();
    private Long index = 0L;

    @Autowired
    private VehicleFileStore vehicleFileStore;


    public List<Vehicle> findAllVehicles() {
        return new ArrayList<>(vehicles.values());
    }

    public Vehicle findVehicleById(Long vehicleId) {
        return vehicles.get(vehicleId);
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        index += 1;
        vehicle.setVehicleId(index);
        vehicles.put(index, vehicle);
        return vehicle;
    }

    public Vehicle updateVehicle(Long vehicleId, Vehicle vehicle) {
        vehicle.setVehicleId(index);
        vehicles.put(vehicleId, vehicle);
        return vehicle;
    }

    public Vehicle deleteVehicle(Long vehicleId) {
        return vehicles.remove(vehicleId);
    }


    @PreDestroy
    private void saveVehicleData() throws IOException {
        List<Vehicle> list = vehicles.values().stream().toList();
        vehicleFileStore.saveVehicleData(list);
    }

    @PostConstruct
    private void pushVehicle() throws IOException {
        if (vehicleFileStore.pushVehicle() != null) {
            List<Vehicle> list = vehicleFileStore.pushVehicle();
            for (Vehicle vehicle : list) {
                index += 1;
                if (vehicle.getVehicleId() > index) {
                    index = vehicle.getVehicleId();
                }
                vehicles.put(vehicle.getVehicleId(), vehicle);
            }
        }
    }

}

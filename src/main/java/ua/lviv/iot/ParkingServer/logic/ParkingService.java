package ua.lviv.iot.ParkingServer.logic;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.ParkingServer.datastorage.ParkingFileStore;
import ua.lviv.iot.ParkingServer.model.Parking;

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
public class ParkingService {
    private Map<Long, Parking> parking = new HashMap();
    Long index = 0L;

    @Autowired
    ParkingFileStore parkingFileStore;

    public List<Parking> findAllParking() {
        return new ArrayList<>(parking.values());
    }

    public Parking findParkingById(Long parkingId) {
        return parking.get(parkingId);
    }


    public Parking addParking(Parking place) {
        index += 1;
        place.setParkingId(index);
        parking.put(index, place);
        return place;
    }

    public Parking updateParking(Long parkingId, Parking place) {
        place.setParkingId(parkingId);
        parking.put(parkingId, place);
        return place;
    }

    public Parking deleteParking(Long parkingId) {
        return parking.remove(parkingId);
    }


    @PreDestroy
    private void saveParkingData() throws IOException {
        List<Parking> list = parking.values().stream().toList();
        parkingFileStore.saveParkingData(list);
    }

    @PostConstruct
    private void pushParking() throws IOException {
        if (parkingFileStore.pushParking() != null) {
            List<Parking> list = parkingFileStore.pushParking();
            for (Parking parking1 : list) {
                this.parking.put(parking1.getParkingId(), parking1);
            }
        }
    }
}

package ua.lviv.iot.parkingServer.logic;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.parkingServer.datastorage.ParkingFileStore;
import ua.lviv.iot.parkingServer.model.Parking;

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
    private Long index = 0L;

    @Autowired
    private ParkingFileStore parkingFileStore;

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
                index += 1;
                if (parking1.getParkingId() > index) {
                    index = parking1.getParkingId();
                }
                parking.put(parking1.getParkingId(), parking1);
            }
        }
    }
}
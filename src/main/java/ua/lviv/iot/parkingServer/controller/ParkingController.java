package ua.lviv.iot.parkingServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.parkingServer.logic.ParkingService;
import ua.lviv.iot.parkingServer.model.Parking;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;


    @PostMapping
    public Parking addParking(@RequestBody Parking place) {
        return parkingService.addParking(place);
    }

    @GetMapping
    public List<Parking> getAllParking() {
        return parkingService.findAllParking();
    }

    @GetMapping("/{parkingId}")
    public Parking getParkingById(@PathVariable Long parkingId) {
        return parkingService.findParkingById(parkingId);
    }


    @PutMapping("/{parkingId}")
    public Parking updateParking(@PathVariable Long parkingId, @RequestBody Parking place) {
        return parkingService.updateParking(parkingId, place);
    }

    @DeleteMapping("/{parkingId}")
    public Parking deleteParking(@PathVariable Long parkingId) {
        return parkingService.deleteParking(parkingId);
    }


}


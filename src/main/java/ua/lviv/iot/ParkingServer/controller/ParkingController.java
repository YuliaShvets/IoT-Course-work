package ua.lviv.iot.ParkingServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.ParkingServer.logic.ParkingService;
import ua.lviv.iot.ParkingServer.model.Parking;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    ParkingService parkingService;


    @PostMapping("/addParking")
    public Parking addParking(@RequestBody Parking place) {
        return parkingService.addParking(place);
    }

    @GetMapping("/allParking")
    public List<Parking> getAllParking() {
        return parkingService.findAllParking();
    }

    @GetMapping("/{parkingId}")
    public Parking getParkingById(@PathVariable Long parkingId) {
        return parkingService.findParkingById(parkingId);
    }


    @PutMapping("/updateParking/{parkingId}")
    public Parking updateParking(@PathVariable Long parkingId, @RequestBody Parking place) {
        return parkingService.updateParking(parkingId, place);
    }

    @DeleteMapping("/deleteParking/{parkingId}")
    public Parking deleteParking(@PathVariable Long parkingId) {
        return parkingService.deleteParking(parkingId);
    }


}


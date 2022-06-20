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
import ua.lviv.iot.parkingServer.logic.ParkingSpotService;
import ua.lviv.iot.parkingServer.model.ParkingSpot;

import java.util.List;

@RestController
@RequestMapping("/parkingSpot")
public class ParkingSpotController {
    @Autowired
    private ParkingSpotService parkingSpotService;

    @PostMapping
    public ParkingSpot addParkingSpot(@RequestBody ParkingSpot parkingSpot) {
        return parkingSpotService.addParkingSpot(parkingSpot);
    }


    @GetMapping
    public List<ParkingSpot> getAllParkingSpot() {
        return parkingSpotService.findAllParkingSpots();
    }

    @GetMapping("/{parkingSpotId}")
    public ParkingSpot getParkingSpotById(@PathVariable Long parkingSpotId) {
        return parkingSpotService.findParkingSpotById(parkingSpotId);
    }


    @PutMapping("/{parkingSpotId}")
    public ParkingSpot updateParkingSpot(@PathVariable Long parkingSpotId, @RequestBody ParkingSpot parkingSpot) {
        return parkingSpotService.updateParkingSpot(parkingSpotId, parkingSpot);
    }

    @DeleteMapping("/{parkingSpotId}")
    public ParkingSpot deleteParkingSpot(@PathVariable Long parkingSpotId) {
        return parkingSpotService.deleteParkingSpot(parkingSpotId);
    }

}

package ua.lviv.iot.ParkingServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.ParkingServer.logic.VehicleService;
import ua.lviv.iot.ParkingServer.model.Vehicle;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping("/addVehicle")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @GetMapping("/allVehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.findAllVehicles();
    }

    @GetMapping("/{vehicleId}")
    public Vehicle getVehicleById(@PathVariable Long vehicleId) {
        return vehicleService.findVehicleById(vehicleId);
    }


    @PutMapping("/updateVehicle/{vehicleId}")
    public Vehicle updateVehicle(@PathVariable Long vehicleId, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(vehicleId, vehicle);
    }

    @DeleteMapping("/parkVehicle/{parkingSpotId}")
    public void parkVehicle(@RequestBody Vehicle vehicle, @PathVariable Long parkingSpotId) {
        vehicleService.parkVehicle(vehicle, parkingSpotId);
    }


    @DeleteMapping("/deleteVehicle/{vehicleId}")
    public Vehicle deleteVehicle(@PathVariable Long vehicleId) {
        return vehicleService.deleteVehicle(vehicleId);
    }

}

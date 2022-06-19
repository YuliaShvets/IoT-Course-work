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
import ua.lviv.iot.parkingServer.logic.VehicleService;
import ua.lviv.iot.parkingServer.model.Vehicle;

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


    @DeleteMapping("/deleteVehicle/{vehicleId}")
    public Vehicle deleteVehicle(@PathVariable Long vehicleId) {
        return vehicleService.deleteVehicle(vehicleId);
    }

}

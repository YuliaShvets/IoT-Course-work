package ua.lviv.iot.parkingServer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.lviv.iot.parkingServer.model.enums.VehicleType;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Vehicle {
    private Long vehicleId;
    private String number;
    private VehicleType typeOfVehicle;
    private double durationOfUseOfParkingSpot; // in hours
    private boolean isTicketReceived;

    public String getHeaders() {
        return "Vehicle id, Number, Vehicle Type, Duration of use parking spot, Is ticket received";
    }

    public String toCSV() {
        return this.getVehicleId() + ", " + this.getNumber() + ", " + this.getTypeOfVehicle() + ", " + this.getDurationOfUseOfParkingSpot() + ", " + this.isTicketReceived();
    }
}
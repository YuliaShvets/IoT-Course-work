package ua.lviv.iot.parkingServer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.lviv.iot.parkingServer.model.enums.ParkingSpotSize;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParkingSpot extends Record {
    private Long parkingSpotId;
    private boolean isAvailable;
    private ParkingSpotSize size;

    @Override
    public String getHeaders() {
        return "Parking spot id, Is available, Size";
    }

    @Override
    public String toCSV() {
        return this.getParkingSpotId() + ", " + this.isAvailable() + ", " + this.getSize();
    }

}

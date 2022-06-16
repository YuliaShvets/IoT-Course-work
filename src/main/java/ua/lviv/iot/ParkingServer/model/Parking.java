package ua.lviv.iot.ParkingServer.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString


public class Parking {
    private Long parkingId;
    private String location;
    private String tradeNetwork;
    private int countOfParkingSpot;

    public String getHeaders() {
        return "Parking id, Location, Trade Network, Count of parking spots";
    }

    public String toCSV() {
        return this.getParkingId() + ", " + this.getLocation() + ", " + this.getTradeNetwork() + ", " + this.countOfParkingSpot;
    }


}
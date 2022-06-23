package ua.lviv.iot.parkingServer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class Parking extends Record {
    private Long parkingId;
    private String location;
    private String tradeNetwork;
    private int countOfParkingSpot;

    @Override
    public String getHeaders() {
        return "Parking id, Location, Trade Network, Count of parking spots";
    }

    @Override
    public String toCSV() {
        return this.getParkingId() + ", " + this.getLocation() + ", " + this.getTradeNetwork() + ", " + this.getCountOfParkingSpot();
    }


}
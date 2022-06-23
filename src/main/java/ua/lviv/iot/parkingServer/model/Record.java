package ua.lviv.iot.parkingServer.model;

public abstract class Record {
    public abstract String getHeaders();

    public abstract String toCSV();
}

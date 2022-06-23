package ua.lviv.iot.parkingServer.datastorage;

import ua.lviv.iot.parkingServer.model.Record;
import ua.lviv.iot.parkingServer.utils.DateToday;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractFIleStore<T extends Record> {

    public static final String RESULT_FOLDER = "src/main/resources";

    public List<T> readRecords() throws IOException {
        String fileName = RESULT_FOLDER + "/" + getRecordName() + "-" + DateToday.getDateToday();
        if (Files.exists(Paths.get(fileName))) {
            return readRecordsFrom(new File(fileName));
        }
        return new LinkedList<>();
    }

    protected List<T> readRecordsFrom(File CSV) throws IOException {
        List<T> result = new LinkedList<>();
        Scanner scanner = new Scanner(CSV, StandardCharsets.UTF_8);
        //skip headers from file
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        } else {
            return result;
        }
        while (scanner.hasNextLine()) {

            String[] values = scanner.nextLine().split(", ");
            result.add(convert(values));
        }
        return result;
    }

    public void saveRecords(List<T> records) throws IOException {
        String date = DateToday.getDateToday();

        File file = new File(RESULT_FOLDER + "/" + getRecordName() + "-" + date + ".csv");
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            writer.write(records.get(0).getHeaders() + "\n");
            for (T record : records) {
                writer.write(record.toCSV() + "\n");
            }


        }

    }

    protected abstract String getRecordName();

    protected abstract T convert(String[] values);


}

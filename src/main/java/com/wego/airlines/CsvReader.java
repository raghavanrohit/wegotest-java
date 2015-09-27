package com.wego.airlines;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class CsvReader {

    public List<Airline> getAllAirlineOperatorsFromFile(String csvFile) {
        InputStream inputStream = null;

        List<Airline> airlines = new ArrayList<Airline>();

        try {
            inputStream = this.getClass().getResourceAsStream(csvFile);

            List<String> lines = IOUtils.readLines(inputStream);

            if (lines != null && lines.size() > 0) {
                for (String line : lines) {
                    Airline airline = populateAirline(line);

                    airlines.add(airline);
                }
            }
        } catch (IOException e) {

        } finally {
            IOUtils.closeQuietly(inputStream);
        }

        return airlines;
    }

    private Airline populateAirline(String line) {
        Airline airline = new Airline();
        if (line != null) {
            try {
                String splitLines[] = line.split(",");

                airline.setFrom(StringUtils.trim(splitLines[0]));
                airline.setTo(StringUtils.trim(splitLines[1]));
                airline.setOperator(StringUtils.trim(splitLines[2]));
                airline.setAirlines(StringUtils.trim(splitLines[2]));
            } catch (Exception e) {
            }
        }

        return airline;
    }
}

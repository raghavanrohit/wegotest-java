package com.wego.airlines;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang.StringUtils;

@Path("/findAirlines")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class AirlineResource {

    private final String AIRLINE_OPERATORS_CSV_FILE_LOCATION = "/airline_operators.csv";

    @POST
    public Response findAirlines(AirlineDto airlineDto) {
        if (airlineDto == null || StringUtils.isBlank(airlineDto.getFrom())
                || StringUtils.isBlank(airlineDto.getTo())) {
            ErrorDto errorDto = new ErrorDto();
            errorDto.setError("Some input parameters were missing!");

            return Response.status(Response.Status.BAD_REQUEST).entity(errorDto).build();
        }

        CsvReader csvReader = new CsvReader();

        List<Airline> airlines
                = csvReader.getAllAirlineOperatorsFromFile(AIRLINE_OPERATORS_CSV_FILE_LOCATION);

        AirlineDto operatingAirlines
                = getOperatingAirlinesBetweenRoute(
                        airlines, airlineDto.getFrom(), airlineDto.getTo());

        if (operatingAirlines == null || operatingAirlines.getAirlines() == null
                || operatingAirlines.getAirlines().isEmpty()) {
            ErrorDto errorDto = new ErrorDto();
            errorDto.setError("No airline operating between desired locations");

            return Response.status(Response.Status.NOT_FOUND).entity(errorDto).build();
        }

        return Response.status(Response.Status.OK).entity(operatingAirlines).build();
    }

    private AirlineDto getOperatingAirlinesBetweenRoute(
            List<Airline> airlines, String from, String to) {
        AirlineDto operatingAirlines = new AirlineDto();

        if (airlines != null && airlines.size() > 0) {
            for (Airline airline : airlines) {
                if (operatingAirlines.getAirlines() == null) {
                    operatingAirlines.setAirlines(new ArrayList<String>());
                }

                if (airline.getFrom().equalsIgnoreCase(from)
                        || airline.getTo().equalsIgnoreCase(from)) {
                    operatingAirlines.getAirlines().add(airline.getAirlines());
                } else if (airline.getFrom().equalsIgnoreCase(to)
                        || airline.getTo().equalsIgnoreCase(to)) {
                    operatingAirlines.getAirlines().add(airline.getAirlines());
                }
            }
        }

        return operatingAirlines;
    }
}

package com.wego.airlines;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(value = JUnit4.class)
public class AirlineResourceTest {

    private final String URI = "https://wegotest-java.herokuapp.com/findAirlines";
    //private final String URI = "http://localhost:8080/findAirlines";

    @Test
    public void testFindAirlinesWithSuccessfulResponse() {
        RestTemplate restTemplate = new RestTemplate();

        AirlineDto airlineDto = new AirlineDto();
        airlineDto.setTo("Singapore");
        airlineDto.setFrom("Mumbai");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_XML_VALUE);

        HttpEntity<AirlineDto> request = new HttpEntity<AirlineDto>(airlineDto, headers);

        ResponseEntity<AirlineDto> response
                = restTemplate.exchange(
                        URI, HttpMethod.POST, request, AirlineDto.class);

        org.junit.Assert.assertNotNull(response);
        org.junit.Assert.assertTrue(
                response.getStatusCode() == HttpStatus.OK);

        airlineDto = (AirlineDto) response.getBody();

        org.junit.Assert.assertNotNull(airlineDto);
        org.junit.Assert.assertTrue(airlineDto.getAirlines().size() == 4);
    }

    @Test
    public void testFindAirlinesWithNoDataFoundResponse() {
        RestTemplate restTemplate = new RestTemplate();

        AirlineDto airlineDto = new AirlineDto();
        airlineDto.setTo("Kuala Lumpur");
        airlineDto.setFrom("Mumbai");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_XML_VALUE);

        HttpEntity<AirlineDto> request = new HttpEntity<AirlineDto>(airlineDto, headers);
        HttpStatus httpStatus = null;

        String errorString = null;

        try {
            restTemplate.exchange(URI, HttpMethod.POST, request, ErrorDto.class);
        } catch (HttpClientErrorException e) {
            errorString = e.getResponseBodyAsString();
            httpStatus = e.getStatusCode();
        }

        org.junit.Assert.assertTrue(httpStatus == HttpStatus.NOT_FOUND);
        org.junit.Assert.assertTrue(
                errorString.contains("No airline operating between desired locations"));
    }

    @Test
    public void testFindAirlinesThrowBadRequestResponseForEmptyToParam() {
        RestTemplate restTemplate = new RestTemplate();

        AirlineDto airlineDto = new AirlineDto();
        airlineDto.setTo("");
        airlineDto.setFrom("Mumbai");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_XML_VALUE);

        HttpEntity<AirlineDto> request = new HttpEntity<AirlineDto>(airlineDto, headers);
        HttpStatus httpStatus = null;

        String errorString = null;

        try {
            restTemplate.exchange(URI, HttpMethod.POST, request, ErrorDto.class);
        } catch (HttpClientErrorException e) {
            errorString = e.getResponseBodyAsString();
            httpStatus = e.getStatusCode();
        }

        org.junit.Assert.assertTrue(httpStatus == HttpStatus.BAD_REQUEST);
        org.junit.Assert.assertTrue(
                errorString.contains("Some input parameters were missing!"));
    }

    @Test
    public void testFindAirlinesThrowBadRequestResponseForEmptyFromParam() {
        RestTemplate restTemplate = new RestTemplate();

        AirlineDto airlineDto = new AirlineDto();
        airlineDto.setTo("Beijing");
        airlineDto.setFrom("");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_XML_VALUE);

        HttpEntity<AirlineDto> request = new HttpEntity<AirlineDto>(airlineDto, headers);
        HttpStatus httpStatus = null;

        String errorString = null;

        try {
            restTemplate.exchange(URI, HttpMethod.POST, request, ErrorDto.class);
        } catch (HttpClientErrorException e) {
            errorString = e.getResponseBodyAsString();
            httpStatus = e.getStatusCode();
        }

        org.junit.Assert.assertTrue(httpStatus == HttpStatus.BAD_REQUEST);
        org.junit.Assert.assertTrue(
                errorString.contains("Some input parameters were missing!"));
    }

    @Test
    public void testFindAirlinesThrowBadRequestResponseForEmptyRequest() {
        RestTemplate restTemplate = new RestTemplate();

        AirlineDto airlineDto = new AirlineDto();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_XML_VALUE);

        HttpEntity<AirlineDto> request = new HttpEntity<AirlineDto>(airlineDto, headers);
        HttpStatus httpStatus = null;

        String errorString = null;

        try {
            restTemplate.exchange(URI, HttpMethod.POST, request, ErrorDto.class);
        } catch (HttpClientErrorException e) {
            errorString = e.getResponseBodyAsString();
            httpStatus = e.getStatusCode();
        }

        org.junit.Assert.assertTrue(httpStatus == HttpStatus.BAD_REQUEST);
        org.junit.Assert.assertTrue(
                errorString.contains("Some input parameters were missing!"));
    }
}

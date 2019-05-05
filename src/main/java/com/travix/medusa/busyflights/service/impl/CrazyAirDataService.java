package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.service.ISupplierDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CrazyAirDataService implements ISupplierDataService<CrazyAirRequest,CrazyAirResponse> {

    private static final Logger LOG = Logger.getLogger(CrazyAirDataService.class.getName());

    @Autowired
    public RestTemplate restTemplate;

    @Override
    public List<BusyFlightsResponse> getFlightDetails(BusyFlightsRequest flightsRequest) {
        LOG.info("Get flight details for CrazyAir");
        List<CrazyAirResponse> list=null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CrazyAirRequest> entity = new HttpEntity<>(convertFromBusinessFlightReq(flightsRequest),headers);
        try {
            ResponseEntity<List<CrazyAirResponse>> response = restTemplate.exchange("http://localhost:8080/rest/api/v1/crazyair", HttpMethod.POST, entity, new ParameterizedTypeReference<List<CrazyAirResponse>>(){});
            list=response.getBody();
        }catch (Exception ex){
            LOG.info("Exception while calling Crazy Air Service ");
            ex.printStackTrace();
        }
       return list.stream().map(x->convertToBusinessFlightRes(x)).collect(Collectors.toList());
    }

    @Override
    public BusyFlightsResponse convertToBusinessFlightRes(CrazyAirResponse supplierResponse) {
        return new BusyFlightsResponse(supplierResponse.getAirline(),"Crazy Air",
                supplierResponse.getPrice(),supplierResponse.getDepartureAirportCode(),
                supplierResponse.getDestinationAirportCode(),supplierResponse.getDepartureDate(),
                supplierResponse.getArrivalDate());
    }

    @Override
    public CrazyAirRequest convertFromBusinessFlightReq(BusyFlightsRequest flightsRequest) {
        return new CrazyAirRequest(flightsRequest.getOrigin(),flightsRequest.getDestination(),
                flightsRequest.getDepartureDate(),flightsRequest.getReturnDate(),flightsRequest.getNumberOfPassengers());
    }


}

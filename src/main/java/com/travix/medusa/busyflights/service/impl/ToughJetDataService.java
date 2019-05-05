package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
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
public class ToughJetDataService implements ISupplierDataService<ToughJetRequest,ToughJetResponse> {

    private static final Logger LOG = Logger.getLogger(ToughJetDataService.class.getName());


    @Autowired
    public RestTemplate restTemplate;

    @Override
    public List<BusyFlightsResponse> getFlightDetails(BusyFlightsRequest flightsRequest) {
        List<ToughJetResponse> list=null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ToughJetRequest> entity = new HttpEntity<>(convertFromBusinessFlightReq(flightsRequest),headers);
        try {
            ResponseEntity<List<ToughJetResponse>> response = restTemplate.exchange("http://localhost:8080/rest/api/v1/toughjet", HttpMethod.POST, entity,new ParameterizedTypeReference<List<ToughJetResponse>>(){});
            list=response.getBody();
        }catch (Exception ex){
            LOG.info("Exception while calling Tough Jet Service ");
        }
        return list.stream().map(x->convertToBusinessFlightRes(x)).collect(Collectors.toList());
    }

    @Override
    public BusyFlightsResponse convertToBusinessFlightRes(ToughJetResponse supplierResponse) {
        return new BusyFlightsResponse(supplierResponse.getCarrier(),"Crazy Air",
                supplierResponse.getBasePrice(),supplierResponse.getDepartureAirportName(),
                supplierResponse.getArrivalAirportName(),supplierResponse.getOutboundDateTime(),
                supplierResponse.getInboundDateTime());
    }

    @Override
    public ToughJetRequest convertFromBusinessFlightReq(BusyFlightsRequest flightsRequest) {
        return new ToughJetRequest(flightsRequest.getOrigin(),flightsRequest.getDestination(),
                flightsRequest.getDepartureDate(),flightsRequest.getReturnDate(),flightsRequest.getNumberOfPassengers());
    }
}

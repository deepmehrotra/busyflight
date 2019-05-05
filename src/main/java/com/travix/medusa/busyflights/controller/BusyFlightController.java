package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.impl.SupplierDataProcessingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/api/v1/flight")
public class BusyFlightController {

    private final SupplierDataProcessingServiceImpl supplierDataService;

    @Autowired
    public BusyFlightController(SupplierDataProcessingServiceImpl supplierDataService) {
        this.supplierDataService = supplierDataService;
    }

    @RequestMapping(path = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BusyFlightsResponse>> search(@RequestBody BusyFlightsRequest flightRequest) throws Exception {
        List<BusyFlightsResponse> flights = supplierDataService.search(flightRequest);

        return ResponseEntity.ok(flights);
    }
}

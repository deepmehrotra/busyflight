package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.util.SupplierDataUtility;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/api/v1")
public class SupplierDataController {


    @RequestMapping(path = "/crazyair", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CrazyAirResponse>> getCrazyAirDetails(@RequestBody CrazyAirRequest flightRequest) {
        List<CrazyAirResponse> flights = SupplierDataUtility.getCrazyAirFlightDetails();
        return ResponseEntity.ok(flights);
    }

    @RequestMapping(path = "/toughjet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ToughJetResponse>> getToughJetDetails(@RequestBody ToughJetRequest flightRequest) {
        List<ToughJetResponse> flights = SupplierDataUtility.getToughJetFlightDetails();
        return ResponseEntity.ok(flights);
    }
}

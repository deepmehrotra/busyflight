package com.travix.medusa.busyflights;


import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.impl.CrazyAirDataService;
import com.travix.medusa.busyflights.service.impl.ToughJetDataService;
import com.travix.medusa.busyflights.util.SupplierDataUtility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SupplierServiceTest {

    @Autowired
    private CrazyAirDataService crazyAirDataService;

    @Autowired
    private ToughJetDataService toughJetDataService;


    @Test
    public void testConvertToBusinessFlightRes(){
     BusyFlightsResponse bfs=crazyAirDataService.convertToBusinessFlightRes(SupplierDataUtility.getCrazyAirFlightDetails().get(0));
        Assert.assertNotNull(bfs);
    }

    @Test
    public void testConvertToBusinessFlightResTJ(){
        BusyFlightsResponse bfs=toughJetDataService.convertToBusinessFlightRes(SupplierDataUtility.getToughJetFlightDetails().get(0));
        Assert.assertNotNull(bfs);
    }
}

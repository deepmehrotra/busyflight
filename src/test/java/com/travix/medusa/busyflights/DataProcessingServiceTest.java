package com.travix.medusa.busyflights;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.impl.CrazyAirDataService;
import com.travix.medusa.busyflights.service.impl.SupplierDataProcessingServiceImpl;
import com.travix.medusa.busyflights.service.impl.ToughJetDataService;
import com.travix.medusa.busyflights.util.SupplierDataUtility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@ActiveProfiles("FlightServiceTest")
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataProcessingServiceTest {
    @Mock
    private CrazyAirDataService crazyAirService;

    @Mock
    private ToughJetDataService toughJetService;

    @Autowired
    private SupplierDataProcessingServiceImpl dataProcessingService;

    @Test
    public void testSearch() throws Exception {
        Mockito.when(crazyAirService.getFlightDetails(Mockito.any(BusyFlightsRequest.class))).thenReturn(SupplierDataUtility.getBusyFlightDetailsForCA());
        Mockito.when(toughJetService.getFlightDetails(Mockito.any(BusyFlightsRequest.class))).thenReturn(SupplierDataUtility.getBusyFlightDetailsForTJ());

        List<BusyFlightsResponse> flights = dataProcessingService.search(new BusyFlightsRequest());

        Assert.assertEquals(4,flights.size());
    }
}

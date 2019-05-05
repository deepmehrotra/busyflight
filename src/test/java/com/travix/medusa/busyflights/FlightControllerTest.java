package com.travix.medusa.busyflights;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.controller.BusyFlightController;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.service.impl.CrazyAirDataService;
import com.travix.medusa.busyflights.service.impl.SupplierDataProcessingServiceImpl;
import com.travix.medusa.busyflights.service.impl.ToughJetDataService;
import com.travix.medusa.busyflights.util.SupplierDataUtility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BusyFlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CrazyAirDataService crazyAirDataService;

    @MockBean
    private ToughJetDataService toughJetDataService;

    @MockBean
    private SupplierDataProcessingServiceImpl supplierDataService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testFlightSerch() throws Exception {
       Mockito.when(supplierDataService.search(Mockito.any(BusyFlightsRequest.class))).thenReturn(SupplierDataUtility.getBusyFlightDetailsForTJ());
       mvc.perform(
                post("/rest/api/v1/flight/search")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new BusyFlightsRequest())))
                .andExpect(status().isOk())
                .andDo(resHandler -> {
                    String resJson = resHandler.getResponse().getContentAsString();
                    BusyFlightsResponse[] res = mapper.readValue(resJson, BusyFlightsResponse[].class);

                    Assert.assertEquals(2, res.length);
                });

    }
}

package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.SupplierRequestParent;
import com.travix.medusa.busyflights.domain.SupplierResponseParent;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.List;
import java.util.stream.Stream;

public interface ISupplierDataService<T extends SupplierRequestParent,R extends SupplierResponseParent> {
    List<BusyFlightsResponse> getFlightDetails(BusyFlightsRequest flightsRequest);
    BusyFlightsResponse convertToBusinessFlightRes(R supplierResponse);
    T convertFromBusinessFlightReq(BusyFlightsRequest flightsRequest);

}

package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.SupplierRequestParent;
import com.travix.medusa.busyflights.domain.SupplierResponseParent;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.ISupplierDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@Service
public class SupplierDataProcessingServiceImpl {

    private static final Logger LOG = Logger.getLogger(SupplierDataProcessingServiceImpl.class.getName());

    private List<? extends ISupplierDataService<? extends SupplierRequestParent,? extends SupplierResponseParent>> supplierList;

    @Value("${busyflight.executor.threadcount}")
    private Integer threadCount;

    @Autowired
    public SupplierDataProcessingServiceImpl(List<? extends ISupplierDataService<? extends SupplierRequestParent,? extends SupplierResponseParent>> supplierList){
        this.supplierList=supplierList;
    }

    public List<BusyFlightsResponse> search(BusyFlightsRequest flightRequest) throws Exception {
        LOG.info("Search flight request");
        Assert.notNull(flightRequest, "Request data not found");
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<CompletableFuture<List<BusyFlightsResponse>>> finalList=new ArrayList<>();
       for(ISupplierDataService supplier:supplierList) {
          CompletableFuture futureList=CompletableFuture.supplyAsync(() -> supplier.getFlightDetails(flightRequest), executorService);
          finalList.add(futureList);
        }

        List<BusyFlightsResponse> result=new ArrayList<>();
        finalList.stream()
                .map(CompletableFuture::join)
                .forEach(x -> {
                    if(x!=null)
                        result.addAll(x);
                });
        Collections.sort(result,(o1,o2)->o1.getFare()>o2.getFare()?-1:1);

        return result;
    }

}

package com.travix.medusa.busyflights.util;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

import java.util.ArrayList;
import java.util.List;

public class SupplierDataUtility {

    static final String arrivalTime="2014-11-01T22:07:24.329";
    static final String departureTime="2014-11-01T20:07:24.329";

    public static List<CrazyAirResponse> getCrazyAirFlightDetails(){
        List<CrazyAirResponse> flightDetails=new ArrayList<>();
        flightDetails.add(new CrazyAirResponse(Constants.CA_AIRLINE_NAME_STR,5235,"B","LHR",
                "AMS", departureTime,arrivalTime));
        flightDetails.add(new CrazyAirResponse(Constants.CA_AIRLINE_NAME_STR,2235,"E","LHR",
                "AMS", departureTime,arrivalTime));
        return flightDetails;
    }

    public static List<ToughJetResponse> getToughJetFlightDetails(){
        List<ToughJetResponse> flightDetails=new ArrayList<>();
        flightDetails.add(new ToughJetResponse(Constants.TJ_AIRLINE_NAME_STR,6235,1234,500,"LHR",
                "AMS", departureTime,arrivalTime));
        flightDetails.add(new ToughJetResponse(Constants.TJ_AIRLINE_NAME_STR,1235,532,100,"LHR",
                "AMS", departureTime,arrivalTime));
        return flightDetails;
    }

    public static List<BusyFlightsResponse> getBusyFlightDetailsForTJ(){
        List<BusyFlightsResponse> flightDetails=new ArrayList<>();
        flightDetails.add(new BusyFlightsResponse(Constants.TJ_AIRLINE_NAME_STR,"ToughJet",6235,"LHR",
                "AMS", departureTime,arrivalTime));
        flightDetails.add(new BusyFlightsResponse(Constants.TJ_AIRLINE_NAME_STR,"ToughJet",1235,"LHR",
                "AMS", departureTime,arrivalTime));
        return flightDetails;
    }

    public static List<BusyFlightsResponse> getBusyFlightDetailsForCA(){
        List<BusyFlightsResponse> flightDetails=new ArrayList<>();
        flightDetails.add(new BusyFlightsResponse(Constants.CA_AIRLINE_NAME_STR,"CrazyAir",6235,"LHR",
                "AMS", departureTime,arrivalTime));
        flightDetails.add(new BusyFlightsResponse(Constants.CA_AIRLINE_NAME_STR,"CrazyAir",1235,"LHR",
                "AMS", departureTime,arrivalTime));
        return flightDetails;
    }

    public static BusyFlightsRequest getBusyFlightRequestObject(){
       return new BusyFlightsRequest("LHR","AMS","2014-11-01","2014-11-01",1);
    }
}
